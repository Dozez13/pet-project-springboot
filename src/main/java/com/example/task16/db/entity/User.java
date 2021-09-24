package com.example.task16.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * User entity
 *
 * @author Pavlo Manuilenko
 */
@Table(name = "user_info")
@Entity
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1789771068899105277L;
    @Id
    @GeneratedValue
    @Column(name = "userid",columnDefinition = "BINARY(16)")
    private UUID userId ;
    @Column(name = "login", unique = true, length = 20)
    private String login;
    @Column(name = "userpassword", length = 100)
    private String password;
    @Column(name = "useremail", unique = true, length = 320)
    private String userEmail;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
