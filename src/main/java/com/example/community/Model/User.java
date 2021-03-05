package com.example.community.Model;

public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long  gmtcreate;
    private long gmtmodifie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public long getGmtmodifie() {
        return gmtmodifie;
    }

    public void setGmtmodifie(long gmtmodifie) {
        this.gmtmodifie = gmtmodifie;
    }
}
