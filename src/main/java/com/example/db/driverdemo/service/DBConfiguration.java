package com.example.db.driverdemo.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
public class DBConfiguration {
   
    private File secureConnectBundle;
    private String clientId;
    private String clientSecret;
   
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getClientSecret() {
        return clientSecret;
    }


    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }


    
    public void setSecureConnectBundle(File secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }


    public File getSecureConnectBundle() {
        return secureConnectBundle;
    }
}
