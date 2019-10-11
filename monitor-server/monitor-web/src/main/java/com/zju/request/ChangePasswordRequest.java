package com.zju.request;

public class ChangePasswordRequest {

    private Long id;

    private String oldPassword;

    private String newPassword;

    public Long getId() {
        return id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
