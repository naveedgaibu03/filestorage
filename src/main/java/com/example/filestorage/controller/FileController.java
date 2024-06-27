package com.example.filestorage.controller;

import com.example.filestorage.model.File;
import com.example.filestorage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    // Endpoint to list files for the authenticated user
    @GetMapping("/files")
    public String listFiles(Model model, Authentication authentication) {
        model.addAttribute("files", fileService.listFiles(authentication.getName()));
        return "files"; // Assuming "files.html" template exists to display the files
    }

    // Endpoint to display the file upload form
    @GetMapping("/files/upload")
    public String showUploadForm() {
        return "upload"; // Assuming "upload.html" template exists for file upload form
    }

    // Endpoint to handle file upload
    @PostMapping("/files/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication) {
        try {
            fileService.saveFile(file, authentication.getName());
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception appropriately, e.g., logging or error message
        }
        return "redirect:/files"; // Redirect to list files page after successful upload
    }

    // Endpoint to download a file by ID
    @GetMapping("/files/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        File file = fileService.getFile(id);
        if (file != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(new ByteArrayResource(file.getData()));
        } else {
            // Handle case where file with given ID is not found (return 404 or appropriate response)
            return ResponseEntity.notFound().build();
        }
    }
}
