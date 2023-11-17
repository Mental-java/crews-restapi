package com.mentaljava.mentaljavarestapiproject.table.report.entity;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "REPORT")
@AllArgsConstructor
@Getter
@ToString
public class Report {

    @Id
    @Column(name = "REPORT_ID")
    private int reportId;

    @Column(name = "REPORT_TARGET")
    private String reportTarget;

    @Column(name = "REPORT_REASON")
    private String reportReason;

    @Column(name = "REPORT_CONTENT")
    private String reportContent;

    @ManyToOne
    @JoinColumn(name = "REPORTER")
    private User reporter;

    @Column(name = "REPORT_CATEGORY")
    private int reportCategory;

    @Column(name = "REPORT_STATUS")
    private int reportStatus;

    public Report() {}

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setReportTarget(String reportTarget) {
        this.reportTarget = reportTarget;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public void setReportCategory(int reportCategory) {
        this.reportCategory = reportCategory;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }
}
