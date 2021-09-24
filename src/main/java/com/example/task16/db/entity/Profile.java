package com.example.task16.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Profile entity
 *
 * @author Pavlo Manuilenko
 */
@Entity
@Table(name = "profile_info")
@Getter
@Setter
public class Profile implements Serializable {
    private static final long serialVersionUID = -1473879605284728654L;
    @Id
    @GeneratedValue
    @Column(name = "profileid",columnDefinition = "BINARY(16)")
    private UUID profileId ;
    @Column(name = "userfirstname", length = 25)
    private String userFirstName;
    @Column(name = "usersurname", length = 25)
    private String userSurName;
    @Column(name = "userregistrationdate", columnDefinition = "DATETIME")
    private LocalDateTime userRegistrationDate;
    @Column(name = "accountbalance")
    private Double accountBalance;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(getProfileId(), profile.getProfileId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfileId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append(", profileId=").append(profileId);
        sb.append(", userFirstName='").append(userFirstName).append('\'');
        sb.append(", userSurName='").append(userSurName).append('\'');
        sb.append(", userRegistrationDate=").append(userRegistrationDate);
        sb.append(", accountBalance=").append(accountBalance);
        sb.append('}');
        return sb.toString();
    }
}
