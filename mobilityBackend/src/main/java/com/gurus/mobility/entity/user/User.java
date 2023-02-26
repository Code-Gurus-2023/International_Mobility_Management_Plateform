package com.gurus.mobility.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
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

   // @Column(updatable = false)
    //private String verificationCode;
    //private MultipartFile[] imageFile;
    private String image;
    private String profileImage;
    private Boolean active;
    public boolean isActive;
    private Boolean hasAccount;


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

    @ElementCollection
    private Set<ERole> roles = new HashSet<>();

    public User(String identifiant, String userName, String email, String password) {
        this.identifiant = identifiant;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


}
