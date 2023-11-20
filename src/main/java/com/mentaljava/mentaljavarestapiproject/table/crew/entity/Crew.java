package com.mentaljava.mentaljavarestapiproject.table.crew.entity;

import com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity.CrewCategory;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CREW")
@AllArgsConstructor
@Getter
@ToString
public class Crew {

    @Id
    @Column(name = "CREW_ID")
    @GeneratedValue
    private int crewId;

    @Column(name = "CREW_NAME")
    private String crewName;

    @ManyToOne
    @JoinColumn(name = "CAPTAIN")
    private User captain;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "CREW_CATEGORYCODE")
    private CrewCategory crewCategoryCode;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CREW_RECRUITMENT_POST")
    private String crewRecruitmentPost;

    @Column(name = "CREW_RECRUITMENT_CONTENT")
    private String crewRecruitmentContent;

    @Column(name = "RECRUITMENT_STATUS")
    private String recruitmentStatus;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    public Crew() {}

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCrewCategoryCode(CrewCategory crewCategoryCode) {
        this.crewCategoryCode = crewCategoryCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCrewRecruitmentPost(String crewRecruitmentPost) {
        this.crewRecruitmentPost = crewRecruitmentPost;
    }

    public void setCrewRecruitmentContent(String crewRecruitmentContent) {
        this.crewRecruitmentContent = crewRecruitmentContent;
    }

    public void setRecruitmentStatus(String recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
