package com.gurus.mobility.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
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
    @Size(max = 20)
    public String userName;
    @Column(nullable = false)
    @Size(max = 120)
    public String password;
    @Column(nullable = false)
    @Size(max = 50)
    @Email
    public String email;
    @Column(nullable = true, length = 64)
    public String profilePic;
    public boolean isActive;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String encode) {

    }
}
