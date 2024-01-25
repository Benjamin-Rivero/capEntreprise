package fr.benjamin.cap_entreprise.service;

import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class FileUploadService {

    private ServletContext servletContext;

    private final static String PATH = "/resources/image/";

    public String uploadFile(MultipartFile file, String namespace){
        try {
            String absolutePath= servletContext.getRealPath(PATH + namespace);

            File dir = new File(absolutePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(absolutePath + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            return PATH + namespace+"/"+file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed.");
            return "Error : Something goes wrong...";
        }
    }


}
