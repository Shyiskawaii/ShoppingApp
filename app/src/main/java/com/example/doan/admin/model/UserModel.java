package com.example.doan.admin.model;

public class UserModel {

    private int UserID;
    private String UserName;
    private String Password;
    private String Role;
    private String Status;

    public UserModel(int userID, String userName, String password, String role, String status) {
        UserID = userID;
        UserName = userName;
        Password = password;
        Role = role;
        Status = status;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
