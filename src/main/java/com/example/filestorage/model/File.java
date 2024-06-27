package com.example.filestorage.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contentType;
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;  // Assuming owner is of type User

    private int version;
}
