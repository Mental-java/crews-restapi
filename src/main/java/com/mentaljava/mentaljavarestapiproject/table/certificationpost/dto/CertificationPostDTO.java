package com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;

import java.time.LocalDate;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CertificationPostDTO {

    private Integer postId;

    private String postTitle;

    private String postContent;

    private LocalDate postDate;

    private CrewDTO crewId;
}
