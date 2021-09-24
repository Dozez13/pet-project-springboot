package com.example.task16.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Table(name = "user_role", indexes = {
        @Index(name = "idx_role_userauthority_unq", columnList = "userAuthority, userId", unique = true)
})
@Entity
@Getter
@Setter
public class Role implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id ;
    @Column(name = "userauthority", length = 100)
    private String userAuthority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) && Objects.equals(getUserAuthority(), role.getUserAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserAuthority());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", userAuthority='").append(userAuthority).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
