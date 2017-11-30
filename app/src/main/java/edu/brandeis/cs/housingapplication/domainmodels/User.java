package edu.brandeis.cs.housingapplication.domainmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private String name;
    private String userName;
    private String passwordHash;
    private String profilePictureUrl;
    private boolean isTenant;
    private String userID;

    public User() {
        //for Jackson
    }

    public User(String name, String userName, String passwordHash, String profilePictureUrl, boolean isTenant) {
        this.name =name;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.profilePictureUrl = profilePictureUrl;
        this.isTenant = isTenant;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }

    @JsonProperty(value = "isTenant")
    public boolean isTenant() {
        return isTenant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setTenant(boolean tenant) {
        isTenant = tenant;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nUsername: " + userName +
                "\nUser Id: " + userID +  "\nIs tenant: " + isTenant;
    }
}
