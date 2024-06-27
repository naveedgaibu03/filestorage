package com.example.filestorage.service;

import com.example.filestorage.model.File;
import com.example.filestorage.model.User; // Import User entity
import com.example.filestorage.repository.FileRepository;
import com.example.filestorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    @Autowired
    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public void saveFile(MultipartFile file, String username) throws IOException {
        User owner = userRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("Owner not found for username: " + username);
        }

        File newFile = new File();
        newFile.setName(file.getOriginalFilename());
        newFile.setContentType(file.getContentType());
        newFile.setData(file.getBytes());
        newFile.setOwner(owner); // Set the User object as the owner
        newFile.setVersion(1);
        fileRepository.save(newFile);
    }

    public List<File> listFiles(String ownerUsername) {
        User owner = userRepository.findByUsername(ownerUsername);
        if (owner == null) {
            throw new RuntimeException("Owner not found for username: " + ownerUsername);
        }
        return fileRepository.findByOwnerUsername(ownerUsername);
    }

    public File getFile(Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        return optionalFile.orElse(null);
    }

    public void updateFile(Long id, MultipartFile file) throws IOException {
        Optional<File> optionalExistingFile = fileRepository.findById(id);
        if (optionalExistingFile.isPresent()) {
            File existingFile = optionalExistingFile.get();
            existingFile.setName(file.getOriginalFilename());
            existingFile.setContentType(file.getContentType());
            existingFile.setData(file.getBytes());
            existingFile.setVersion(existingFile.getVersion() + 1);
            fileRepository.save(existingFile);
        }
    }
}
