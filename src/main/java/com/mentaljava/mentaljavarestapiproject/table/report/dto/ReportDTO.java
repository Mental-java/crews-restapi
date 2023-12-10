package com.mentaljava.mentaljavarestapiproject.table.report.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {

    private Integer reportId;

    private UserDTO reportTarget;

    private String reportContent;

    private UserDTO reporter;

    private Integer reportCategory;

    private Integer reportStatus;

    private CrewDTO reportCrew;
}
