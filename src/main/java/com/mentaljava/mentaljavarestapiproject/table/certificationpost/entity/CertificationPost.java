package com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity;

import javax.persistence.*;
import java.util.Date;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;


@Entity
@Table(name = "CERTIFICATION_POST")
@AllArgsConstructor
@Getter
@ToString
public class CertificationPost {

    @Id
    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "POST_DATE")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "CREW_ID")
    private Crew crewId;

    public CertificationPost() {}

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setCrewId(Crew crewId) {
        this.crewId = crewId;
    }
}
