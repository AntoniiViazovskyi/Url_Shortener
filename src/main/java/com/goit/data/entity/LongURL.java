package com.goit.data.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongURL {
    private String url;
    private Long id;
    private User creator;
    private ShortURL shortURL;
}
