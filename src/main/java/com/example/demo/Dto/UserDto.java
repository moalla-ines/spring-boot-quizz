package com.example.demo.Dto;

public class UserDto {
    private  Integer iduser;
    private String username;
    private String password;
    private String email;


    public UserDto(Integer iduser, String username, String password, String email) {
        this.iduser = iduser;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDto() {
    }


    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
