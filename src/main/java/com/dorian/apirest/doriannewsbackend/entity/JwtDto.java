package com.dorian.apirest.doriannewsbackend.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {
   
	private String token;
    
	private String bearer = "Bearer";
    
	private String customerName;
    
	private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String customerName, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.customerName = customerName;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}