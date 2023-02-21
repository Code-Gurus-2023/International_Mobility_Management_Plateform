package com.gurus.mobility.entity.ForumChat;

import com.gurus.mobility.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dsc", nullable = false)
    private Long idDsc;

    @Column(name = "name_dsc", length = 50)
    private String nameDsc;

    @Column(name = "nbr_message_dsc")
    private Integer nbrMessageDsc;

    @Column(name = "views_dsc")
    private Long viewsDsc;

    @Column(name = "last_message_date")
    private LocalDateTime lastMessageDate;

    @Column(name = "is_active")
    private Boolean isActive;


    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "discussion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Comment> comments;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
