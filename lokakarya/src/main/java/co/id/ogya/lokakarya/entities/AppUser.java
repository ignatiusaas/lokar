package co.id.ogya.lokakarya.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "TBL_APP_USER")
public class AppUser {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "EMPLOYEE_STATUS")
    private byte employeeStatus;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "DIVISION_ID")
    private String divisionId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @PrePersist
    private void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}