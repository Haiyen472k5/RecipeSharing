package com.recipeshare.backend.DTO;

import java.time.LocalDate;

public class UserSimpleDTO {
    private String username;
    private String avatarUrl;
    private LocalDate birthDate;

    public UserSimpleDTO() {}

    public UserSimpleDTO(String username, String avatarUrl, LocalDate birthDate) {
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;

    }

    public UserSimpleDTO(String username, String avatarUrl) {
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
