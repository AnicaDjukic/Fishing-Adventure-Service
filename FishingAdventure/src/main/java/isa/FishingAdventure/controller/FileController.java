package isa.FishingAdventure.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import isa.FishingAdventure.dto.NewFiledto;
import isa.FishingAdventure.dto.UploadFileDto;
import isa.FishingAdventure.model.Image;
import isa.FishingAdventure.service.FileService;
import isa.FishingAdventure.service.ImageService;

@RestController
public class FileController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private FileService fileStorageService;

    @Autowired
    private ImageService imageService;

    public UploadFileDto uploadFile(String image, String serviceName, String imageIndex) throws Exception {
        String service = serviceName.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s", "").toLowerCase();
        String fileName = fileStorageService.storeFile(image, service + imageIndex);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileDto(fileName, fileDownloadUri);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Image> uploadMultipleFiles(@RequestBody NewFiledto dto) {
        List<Image> list = new ArrayList<>();
        for (int i = 0; i < dto.getImages().size(); i++) {
            Image newImage = new Image();
            try {
                UploadFileDto fileDto = uploadFile(dto.getImages().get(i), dto.getServiceName(), Integer.toString(i));
                newImage.setPath(fileDto.getFileName());
                newImage.setCoverImage(i == dto.getCoverImage());
                Image image = imageService.save(newImage);
                list.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
            throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
