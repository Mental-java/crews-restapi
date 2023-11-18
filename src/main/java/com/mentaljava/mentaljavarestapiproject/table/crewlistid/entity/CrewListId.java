package com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CrewListId implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CREW_ID")
    private int crewId;

}
