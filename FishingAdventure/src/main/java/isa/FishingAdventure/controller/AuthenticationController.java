package isa.FishingAdventure.controller;

import isa.FishingAdventure.dto.JwtAuthenticationRequest;
import isa.FishingAdventure.dto.UserDto;
import isa.FishingAdventure.dto.UserTokenState;
import isa.FishingAdventure.exception.ResourceConflictException;
import isa.FishingAdventure.model.*;
import isa.FishingAdventure.security.util.TokenUtils;
import isa.FishingAdventure.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VacationHomeOwnerService homeOwnerService;

    @Autowired
    private FishingInstructorService instructorService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        if (!user.isActivated()) {
            return ResponseEntity.ok(null);
        }
        String jwt = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), false);
        String refreshToken = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), true);

        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserTokenState(jwt, refreshToken, roles));
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<UserTokenState> refreshToken(@RequestHeader("Authorization") String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User user = userService.findByEmail(email);

        String jwt = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), false);
        String refreshToken = tokenUtils.generateToken(user.getEmail(), user.getUserType().getName(), true);

        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserTokenState(jwt, refreshToken, roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<Client> addUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {

        Client userClient;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException(userDto.getId(), "Email already exists");
        } else {
            userClient = this.clientService.save(userDto);
            sendRegistrationEmail(userDto);
        }

        return new ResponseEntity<>(userClient, HttpStatus.CREATED);
    }

    private void sendRegistrationEmail(UserDto userDto) {
        try {
            String token = generateRegistrationToken(userDto.getEmail());
            String html = createConfirmEmail(token);
            emailService.sendEmail(userDto.getEmail(), "Confirm registration", html);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String generateRegistrationToken(String email) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setEmail(email);
        confirmationToken.setToken(tokenUtils.generateToken(email, "ROLE_CLIENT", false));
        confirmationTokenService.save(confirmationToken);

        return confirmationToken.getToken();
    }

    @PostMapping("/signup/homeOwner")
    public ResponseEntity<VacationHomeOwner> addHomeOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        VacationHomeOwner vacationHomeOwner;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException(userDto.getId(), "Email already exists");
        } else {
            vacationHomeOwner = this.homeOwnerService.save(userDto);
        }

        return new ResponseEntity<>(vacationHomeOwner, HttpStatus.CREATED);
    }

    @PostMapping("/signup/boatOwner")
    public ResponseEntity<BoatOwner> addBoatOwner(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        BoatOwner boatOwner;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException(userDto.getId(), "Email already exists");
        } else {
            boatOwner = this.boatOwnerService.save(userDto);
        }

        return new ResponseEntity<>(boatOwner, HttpStatus.CREATED);
    }

    @PostMapping("/signup/fishingInstructor")
    public ResponseEntity<FishingInstructor> addFishingInstructor(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
        FishingInstructor fishingInstructor;
        if (userService.isEmailRegistered(userDto.getEmail())) {
            throw new ResourceConflictException(userDto.getId(), "Email already exists");
        } else {
            fishingInstructor = this.instructorService.save(userDto);
        }

        //TODO: send registration to admin for approval

        return new ResponseEntity<>(fishingInstructor, HttpStatus.CREATED);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirm(@RequestParam(name = "token") String token) {

        ConfirmationToken confirmationToken = confirmationTokenService.findByConfirmationToken(token);

        if (token != null) {
            Client user = clientService.findByEmail(confirmationToken.getEmail());
            user.setActivated(true);
            clientService.save(user);
            confirmationTokenService.delete(confirmationToken);
        } else {
            return ResponseEntity.badRequest()
                    .body("Error");
        }

        return ResponseEntity.ok()
                .body("Success");
    }

    private String createConfirmEmail(String token) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;font-family:'Open Sans', sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \n" +
                "  <meta name=\"x-apple-disable-message-reformatting\"> \n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\"> \n" +
                "  <title>New message</title> \n" +
                "  <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--> \n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \n" +
                "  <!--[if !mso]><!-- --> \n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Oswald:300,700&display=swap\" rel=\"stylesheet\"> \n" +
                "  <!--<![endif]--> \n" +
                "  <!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]--> \n" +
                "  <!--[if !mso]><!-- --> \n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\"> \n" +
                "  <!--<![endif]--> \n" +
                "  <style type=\"text/css\">\n" +
                "#outlook a {\n" +
                "\tpadding:0;\n" +
                "}\n" +
                ".ExternalClass {\n" +
                "\twidth:100%;\n" +
                "}\n" +
                ".ExternalClass,\n" +
                ".ExternalClass p,\n" +
                ".ExternalClass span,\n" +
                ".ExternalClass font,\n" +
                ".ExternalClass td,\n" +
                ".ExternalClass div {\n" +
                "\tline-height:100%;\n" +
                "}\n" +
                ".es-button {\n" +
                "\tmso-style-priority:100!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "}\n" +
                "a[x-apple-data-detectors] {\n" +
                "\tcolor:inherit!important;\n" +
                "\ttext-decoration:none!important;\n" +
                "\tfont-size:inherit!important;\n" +
                "\tfont-family:inherit!important;\n" +
                "\tfont-weight:inherit!important;\n" +
                "\tline-height:inherit!important;\n" +
                "}\n" +
                ".es-desk-hidden {\n" +
                "\tdisplay:none;\n" +
                "\tfloat:left;\n" +
                "\toverflow:hidden;\n" +
                "\twidth:0;\n" +
                "\tmax-height:0;\n" +
                "\tline-height:0;\n" +
                "\tmso-hide:all;\n" +
                "}\n" +
                "[data-ogsb] .es-button {\n" +
                "\tborder-width:0!important;\n" +
                "\tpadding:25px 40px 25px 40px!important;\n" +
                "}\n" +
                "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:28px!important; text-align:left } h2 { font-size:20px!important; text-align:left } h3 { font-size:14px!important; text-align:left } h1 a { text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:28px!important } h2 a { text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:20px!important } h3 a { text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:14px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:14px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button, button.es-button { font-size:14px!important; display:block!important; border-bottom-width:20px!important; border-right-width:0px!important; border-left-width:0px!important; border-top-width:20px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } }\n" +
                "</style> \n" +
                " </head> \n" +
                " <body style=\"width:100%;font-family:'Open Sans', sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"> \n" +
                "  <div class=\"es-wrapper-color\" style=\"background-color:#F5F5F5\"> \n" +
                "   <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#f5f5f5\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]--> \n" +
                "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\"> \n" +
                "     <tr style=\"border-collapse:collapse\"> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\"> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#1B2A2F;background-repeat:repeat;background-position:center top\"> \n" +
                "         <tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" bgcolor=\"#111517\" style=\"padding:0;Margin:0;background-color:#111517\"> \n" +
                "           <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#111517\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#111517;width:600px\"> \n" +
                "             <tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:25px;padding-bottom:40px\"> \n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tr style=\"border-collapse:collapse\"> \n" +
                "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#EF0D33;font-size:14px\"><img src=\"https://kkplso.stripocdn.email/content/guids/CABINET_b0ac786bd0f413af460b298bfd5d374e/images/logo_white.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"330\" height=\"134\"></a></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table></td> \n" +
                "             </tr> \n" +
                "           </table></td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \n" +
                "         <tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#F5F5F5;width:600px\"> \n" +
                "             <tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:40px;padding-bottom:40px\"> \n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tr style=\"border-collapse:collapse\"> \n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:580px\"> \n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:40px\"><h1 style=\"Margin:0;line-height:34px;mso-line-height-rule:exactly;font-family:Oswald, sans-serif;font-size:28px;font-style:normal;font-weight:bold;color:#262626\">WELCOME!</h1></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\">We're excited to have you get started. First, you need to confirm your account. Just press the button below.</p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:40px;padding-bottom:40px\"> \n" +
                "                       <!--[if mso]><a href=\"https://viewstripo.email/\" target=\"_blank\" hidden>\n" +
                "\t<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email/\" \n" +
                "                style=\"height:64px; v-text-anchor:middle; width:234px\" arcsize=\"0%\" stroke=\"f\"  fillcolor=\"#2d516f\">\n" +
                "\t\t<w:anchorlock></w:anchorlock>\n" +
                "\t\t<center style='color:#ffffff; font-family:Oswald, sans-serif; font-size:12px; font-weight:700; line-height:12px;  mso-text-raise:1px'>CONFIRM ACCOUNT</center>\n" +
                "\t</v:roundrect></a>\n" +
                "<![endif]--> \n" +
                "                       <!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#1B2A2F;background:#2d516f;border-width:0px;display:inline-block;border-radius:0px;width:auto;mso-hide:all\"><a href=\"http://localhost:8080/auth/confirm-account?token=");
        html.append(token);
        html.append("\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:12px;border-style:solid;border-color:#2d516f;border-width:25px 40px 25px 40px;display:inline-block;background:#2d516f;border-radius:0px;font-family:Oswald, sans-serif;font-weight:bold;font-style:normal;line-height:14px;width:auto;text-align:center\">CONFIRM ACCOUNT</a></span> \n" +
                "                       <!--<![endif]--></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\">If that doesn't work, copy and paste the following link in your browser:</p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\"><strong><a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#2d516f;font-size:20px\" href=\"\">http://localhost:8080/auth/confirm-account?token=");
        html.append(token);
        html.append("</a></strong></p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\">If you have any questions, just reply to this email—we're always happy to help out.</p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\"><strong>Cheers, The Developer Team</strong></p></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table></td> \n" +
                "             </tr> \n" +
                "           </table></td> \n" +
                "         </tr> \n" +
                "       </table> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#111517;background-repeat:repeat;background-position:center top\"> \n" +
                "         <tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#111517;width:600px\"> \n" +
                "             <tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:40px;padding-bottom:40px\"> \n" +
                "               <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" \n" +
                "                        cellspacing=\"0\"><tr><td style=\"width:175px\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> \n" +
                "                 <tr style=\"border-collapse:collapse\"> \n" +
                "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:175px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#EF0D33;font-size:12px\"><img src=\"https://kkplso.stripocdn.email/content/guids/CABINET_b0ac786bd0f413af460b298bfd5d374e/images/logo_white_fa8.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"175\" height=\"71\"></a></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;font-size:0\"> \n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                         <tr style=\"border-collapse:collapse\"> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://kkplso.stripocdn.email/content/assets/img/social-icons/logo-white/facebook-logo-white.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\" height=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://kkplso.stripocdn.email/content/assets/img/social-icons/logo-white/twitter-logo-white.png\" alt=\"Tw\" title=\"Twitter\" width=\"32\" height=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://kkplso.stripocdn.email/content/assets/img/social-icons/logo-white/youtube-logo-white.png\" alt=\"Yt\" title=\"Youtube\" width=\"32\" height=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img src=\"https://kkplso.stripocdn.email/content/assets/img/social-icons/logo-white/instagram-logo-white.png\" alt=\"Ig\" title=\"Instagram\" width=\"32\" height=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                         </tr> \n" +
                "                       </table></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:365px\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> \n" +
                "                 <tr style=\"border-collapse:collapse\"> \n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:365px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:18px;color:#BCBDBD;font-size:12px\">We are students at the Faculty of Technical Sciences in Novi Sad&nbsp;trying to make this project for a course in Internet Software&nbsp;Architecture.</p></td> \n" +
                "                     </tr> \n" +
                "                   </table></td> \n" +
                "                 </tr> \n" +
                "               </table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--></td> \n" +
                "             </tr> \n" +
                "           </table></td> \n" +
                "         </tr> \n" +
                "       </table></td> \n" +
                "     </tr> \n" +
                "   </table> \n" +
                "  </div>  \n" +
                " </body>\n" +
                "</html>");
        return html.toString();
    }

}