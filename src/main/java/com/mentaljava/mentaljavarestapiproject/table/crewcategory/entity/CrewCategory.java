package com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CREW_CATEGORY")
@AllArgsConstructor
@Getter
@ToString
public class CrewCategory {

    @Id
    @Column(name = "CATEGORY_CODE")
    private Integer categoryCode;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    public CrewCategory() {}

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
