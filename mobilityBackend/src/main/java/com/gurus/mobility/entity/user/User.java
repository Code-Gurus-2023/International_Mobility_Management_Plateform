package com.gurus.mobility.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.Delete;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "identifiant"),
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(nullable = false, length = 10)
    public String identifiant;
    @Column(nullable = false)
    public String userName;
    @Column(nullable = false)
    @Size(max = 120)
    public String password;
    @Column(nullable = false)
    @Email
    public String email;
    private String profileImage;
    private Boolean verified;
    private String token;
    private String verificationCode;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;
    public String phoneNumber;

    public String kind;
    public String Location;
    @Enumerated(EnumType.STRING)
    public StudentSpeciality studentSpeciality;

    public String studentLevel;
    @Enumerated(EnumType.STRING)
    public ForumStatus forumStatus;

    public String professorDiploma;

    public int experienceYears;
    @ManyToOne
    private FileDB image;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String identifiant, String userName, String email, String password) {
        this.identifiant = identifiant;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


}
