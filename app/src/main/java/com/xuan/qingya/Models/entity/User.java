package com.xuan.qingya.Models.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by zhouzhixuan on 2017/11/29.
 */

public class User implements Parcelable, Serializable {
    private int id;
    private String email;
    private String username;
    private String password;
    private String signature;
    private String regDate;
    private String token;
    private String lastLogin;
    private String emailCode;
    private String reserved;

    public User(String email, String username, String password, String signature) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.signature = signature;
    }

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        username = in.readString();
        password = in.readString();
        signature = in.readString();
        regDate = in.readString();
        token = in.readString();
        lastLogin = in.readString();
        emailCode = in.readString();
        reserved = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(signature);
        parcel.writeString(regDate);
        parcel.writeString(token);
        parcel.writeString(lastLogin);
        parcel.writeString(emailCode);
        parcel.writeString(reserved);
    }
}
