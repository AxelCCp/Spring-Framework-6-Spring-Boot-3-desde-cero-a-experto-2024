package com.spring3.app.springboot_web.model.dto;

import com.spring3.app.springboot_web.model.entity.User;

public class UserDto {

    public void setTitle(String title) {
        this.title = title;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }
    public User getUser() {
        return user;
    }
    
    private String title;
    private User user;

}
