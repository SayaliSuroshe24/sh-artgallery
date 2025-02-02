package com.art.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    
    private final String uploadDir = "uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        // Ensure directory exists
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique filename to avoid conflicts
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : "";
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Define file path
        Path filePath = Paths.get(uploadDir + uniqueFileName);

        // Save the file to disk
        Files.copy(file.getInputStream(), filePath);

        // Return the file name to be stored in the database
        return uniqueFileName;
    }
}



/*
 * 
 * Explanation of FileStorageService:

uploadDir: This is the directory where the files will be stored. It’s currently set to "uploads/".
saveFile(): This method accepts a MultipartFile, generates a unique filename (using UUID), and stores the file in the specified directory.
The method returns the unique filename to be stored in the database.

 */
