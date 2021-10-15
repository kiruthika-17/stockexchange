package com.model.stockexchange.dto;

public class AuthenticationResponse {

    private Long id;
    private String userName;
    private String jwtToken;

    public AuthenticationResponse(Long id, String userName, String jwtToken) {
        this.id = id;
        this.userName = userName;
        this.jwtToken = jwtToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
