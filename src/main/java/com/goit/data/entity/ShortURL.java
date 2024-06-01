package com.goit.data.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortURL {
    private String url;
    private Long id;
    private String longURL;
    private User creator;
    private List<User> users;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
}
