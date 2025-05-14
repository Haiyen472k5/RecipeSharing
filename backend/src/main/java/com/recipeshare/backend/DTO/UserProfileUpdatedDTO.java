package com.recipeshare.backend.DTO;

import java.time.LocalDate;

public class UserProfileUpdatedDTO {
    private Long id;
    private String avatarUrl;
    private String fullName;
    private String username;
    private LocalDate birthDate;

    public UserProfileUpdatedDTO() {}

    public UserProfileUpdatedDTO(String avatarUrl, String fullName, String username, LocalDate birthDate) {
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
        this.username = username;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
