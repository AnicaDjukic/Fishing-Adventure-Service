package isa.FishingAdventure.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import isa.FishingAdventure.dto.UploadFileDto;
import isa.FishingAdventure.model.FileStorage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class FileService {

    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileStorage fileStorageProperties) throws Exception {
        this.fileStorageLocation = Paths.get(System.getProperty(
                "user.dir") + "/images")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(String imageString, String fileName) throws Exception {
        // create a buffered image
        BufferedImage image = null;
        System.out.println("ccccccccccccccccccccccc");
        System.out.println(imageString);
        byte[] imageByte;
        imageByte = Base64.getDecoder().decode(imageString.trim());
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        // write the image to a file
        String targetLocation = this.fileStorageLocation.toString();
        ImageIO.write(image, "jpg", new File(targetLocation + "/" + fileName + ".jpg"));

        return fileName + ".jpg";
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }

}
