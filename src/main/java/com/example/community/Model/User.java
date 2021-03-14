package com.example.community.Model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long  gmtcreate;
    private long gmtmodifie;
    private String avatarurl;


}
