package isa.FishingAdventure.service;

import isa.FishingAdventure.model.Admin;
import isa.FishingAdventure.model.DeleteRequest;
import isa.FishingAdventure.model.User;
import isa.FishingAdventure.repository.DeleteRequestRepository;
import isa.FishingAdventure.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    public List<DeleteRequest> findAllUnreviewed() {
        List<DeleteRequest> requests = new ArrayList<>();
        for (DeleteRequest request : deleteRequestRepository.findAll()) {
            if (!request.isReviewed()) {
                requests.add(request);
            }
        }
        return requests;
    }

    public DeleteRequest findById(int id) { return deleteRequestRepository.getById(id); }

    public void createDeleteRequest(String token, String content) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        User user = userService.findByEmail(email);
        DeleteRequest newRequest = new DeleteRequest(email, content, new Date(), user.getUserType().getName());
        deleteRequestRepository.save(newRequest);
    }

    public boolean checkIfRequestExist(String token) {
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        return deleteRequestRepository.findByEmail(email) != null;
    }

    public List<DeleteRequest> getDeleteRequests(String token) {
        List<DeleteRequest> deleteRequests = new ArrayList<>();
        String email = tokenUtils.getEmailFromToken(token.split(" ")[1]);
        Admin admin = adminService.findByEmail(email);
        if (admin.getHeadAdmin()) {
            deleteRequests = findAllUnreviewed();
        }
        else {
            deleteRequests = findAllExceptAdmin();
        }
        return deleteRequests;
    }

    private List<DeleteRequest> findAllExceptAdmin() {
        List<DeleteRequest> deleteRequests = new ArrayList<>();
        for (DeleteRequest request : findAllUnreviewed()) {
            if (!request.getUserRole().equals("ROLE_ADMIN")) {
                deleteRequests.add(request);
            }
        }
        return deleteRequests;
    }

    public void approveRequest(int id, String response) {
        DeleteRequest request = findById(id);
        request.setReviewed(true);
        userService.delete(request.getEmail());
        deleteRequestRepository.save(request);
        String emailText = createDeletionResponseEmail(response, true);
        try {
            emailService.sendEmail(request.getEmail(), "Request for account deletion", emailText);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }
    }

    public void rejectRequest(int id, String response) {
        DeleteRequest request = findById(id);
        request.setReviewed(true);
        deleteRequestRepository.save(request);
        String emailText = createDeletionResponseEmail(response, false);
        try {
            emailService.sendEmail(request.getEmail(), "Request for account deletion", emailText);
        } catch (Exception e) {
            System.out.println("Email could not be sent.");
        }
    }

    private String createDeletionResponseEmail(String response, boolean requestApproved) {
        String title = "";
        String endPart = "";
        if (requestApproved) {
            title = "Account deleted";
            endPart = "We're sorry to see you go.";
        }
        else {
            title = "Account not deleted";
            endPart = "If you have any questions, just reply to this email - we're always happy to help out.";
        }

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;font-family:'Open Sans', sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"><head> \n" +
                "  <meta charset=\"UTF-8\"> \n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \n" +
                "  <meta name=\"x-apple-disable-message-reformatting\"> \n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\"> \n" +
                "  <title>New Template</title> \n" +
                "  <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]--> \n" +
                "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \n" +
                "  <!--[if !mso]><!-- --> \n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Oswald:300,700&amp;display=swap\" rel=\"stylesheet\"> \n" +
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
                "     <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\"> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#1B2A2F;background-repeat:repeat;background-position:center top\"> \n" +
                "         <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" bgcolor=\"#111517\" style=\"padding:0;Margin:0;background-color:#111517\"> \n" +
                "           <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#111517\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#111517;width:600px\"> \n" +
                "             <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:25px;padding-bottom:40px\"> \n" +
                "               <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                  <td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:21px;color:#BCBDBD;font-size:14px\"><br></p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#EF0D33;font-size:14px\"><img src=\"https://utyeot.stripocdn.email/content/guids/CABINET_fd8fa001216f7afcdf44b7155e9dc9a0/images/logo_white_Efa.png\" alt=\"\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"235\"></a></td> \n" +
                "                     </tr> \n" +
                "                   </tbody></table></td> \n" +
                "                 </tr> \n" +
                "               </tbody></table></td> \n" +
                "             </tr> \n" +
                "           </tbody></table></td> \n" +
                "         </tr> \n" +
                "       </tbody></table> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \n" +
                "         <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#F5F5F5;width:600px\"> \n" +
                "             <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:40px;padding-bottom:40px\"> \n" +
                "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                 <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:580px\"> \n" +
                "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-bottom:40px\"><h1 style=\"Margin:0;line-height:34px;mso-line-height-rule:exactly;font-family:Oswald, sans-serif;font-size:28px;font-style:normal;font-weight:bold;color:#262626\">" + title + "</h1></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\">" + response + "</p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                       \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:40px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\">" + endPart + "</p></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:30px;color:#262626;font-size:20px\"><strong>Sincerely, The Developer Team</strong></p></td> \n" +
                "                     </tr> \n" +
                "                   </tbody></table></td> \n" +
                "                 </tr> \n" +
                "               </tbody></table></td> \n" +
                "             </tr> \n" +
                "           </tbody></table></td> \n" +
                "         </tr> \n" +
                "       </tbody></table> \n" +
                "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:#111517;background-repeat:repeat;background-position:center top\"> \n" +
                "         <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "          <td align=\"center\" style=\"padding:0;Margin:0\"> \n" +
                "           <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#111517;width:600px\"> \n" +
                "             <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "              <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:40px;padding-bottom:40px\"> \n" +
                "               <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" \n" +
                "                        cellspacing=\"0\"><tr><td style=\"width:175px\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> \n" +
                "                 <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                  <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:175px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#EF0D33;font-size:12px\"><img src=\"https://utyeot.stripocdn.email/content/guids/CABINET_fd8fa001216f7afcdf44b7155e9dc9a0/images/logo_white_TnV.png\" alt=\"\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"150\"></a></td> \n" +
                "                     </tr> \n" +
                "                     <tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"center\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;font-size:0\"> \n" +
                "                       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                         <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://utyeot.stripocdn.email/content/assets/img/social-icons/logo-white/facebook-logo-white.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://utyeot.stripocdn.email/content/assets/img/social-icons/logo-white/twitter-logo-white.png\" alt=\"Tw\" title=\"Twitter\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;padding-right:10px\"><img src=\"https://utyeot.stripocdn.email/content/assets/img/social-icons/logo-white/youtube-logo-white.png\" alt=\"Yt\" title=\"Youtube\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                          <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0\"><img src=\"https://utyeot.stripocdn.email/content/assets/img/social-icons/logo-white/instagram-logo-white.png\" alt=\"Ig\" title=\"Instagram\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></td> \n" +
                "                         </tr> \n" +
                "                       </tbody></table></td> \n" +
                "                     </tr> \n" +
                "                   </tbody></table></td> \n" +
                "                 </tr> \n" +
                "               </tbody></table> \n" +
                "               <!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:365px\" valign=\"top\"><![endif]--> \n" +
                "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> \n" +
                "                 <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;width:365px\"> \n" +
                "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \n" +
                "                     <tbody><tr style=\"border-collapse:collapse\"> \n" +
                "                      <td align=\"left\" class=\"es-m-txt-c\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'Open Sans', sans-serif;line-height:18px;color:#BCBDBD;font-size:12px\">We are students at the Faculty of Technical Sciences in Novi Sad&nbsp; &nbsp; trying to make this project for a course in Internet Software&nbsp; &nbsp; &nbsp; &nbsp; Architecture.</p></td> \n" +
                "                     </tr> \n" +
                "                   </tbody></table></td> \n" +
                "                 </tr> \n" +
                "               </tbody></table> \n" +
                "               <!--[if mso]></td></tr></table><![endif]--></td> \n" +
                "             </tr> \n" +
                "           </tbody></table></td> \n" +
                "         </tr> \n" +
                "       </tbody></table></td> \n" +
                "     </tr> \n" +
                "   </tbody></table> \n" +
                "  </div>  \n" +
                " \n" +
                "</body></html>");
        return html.toString();
    }
}
