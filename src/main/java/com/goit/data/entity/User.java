package com.goit.data.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String password;
    private Long id;
    private List<LongURL> longURLs;
    private List<ShortURL> shortURLs;
}
