package com.goit.user.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message =
            "Password must contain at least 8 characters, including digits, upper and lower case letters")
    private String password;

   /* @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<longURL> longURLs;

    @ManyToMany(mappedBy = "users")
    private List<ShortUrl> shortUrls;*/

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
