package com.example.task16.service.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class ProfileDto {
    private UUID profileId ;

    private String userFirstName;

    private String userSurName;

    private LocalDateTime userRegistrationDate;

    private Double accountBalance;

    private UUID userId;

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public void setUserSurName(String userSurName) {
        this.userSurName = userSurName;
    }

    public LocalDateTime getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public void setUserRegistrationDate(LocalDateTime userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
