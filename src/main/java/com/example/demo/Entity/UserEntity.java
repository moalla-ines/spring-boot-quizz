package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "type_user",joinColumns =
    @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "role_id", referencedColumnName = "idrole") )
    private List<Role>roles = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuizHistory> quizHistoryList;

    public UserEntity(Integer id, String username, String password, String email, List<Role> roles, List<QuizHistory> quizHistoryList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles=roles;
        this.quizHistoryList = quizHistoryList != null ? quizHistoryList : new ArrayList<>();
    }

    public UserEntity(String username, String password, String email, List<Role>roles, List<QuizHistory> quizHistoryList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles=roles;
        this.quizHistoryList = quizHistoryList != null ? quizHistoryList : new ArrayList<>();
    }

    public UserEntity(String username, String password, String email, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserEntity(List<QuizHistory> quizHistoryList) {
        this.quizHistoryList = quizHistoryList;
    }

    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer iduser) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
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
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<QuizHistory> getQuizHistoryList() {
        return quizHistoryList;
    }

    public void setQuizHistoryList(List<QuizHistory> quizHistoryList) {
        this.quizHistoryList = quizHistoryList;
    }
}