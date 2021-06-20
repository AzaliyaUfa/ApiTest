package ru.ibs.intern.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Vacancy {

    @Id
    @GeneratedValue
    private Long id;
    private Long vacancyId;
    private String name;
    /*@OneToOne(cascade = CascadeType.ALL)
    private Area area;*/

    private Integer areaId;
    private String areaName;
    private Long salaryFrom;
    private Long salaryTo;
    private String salaryCurrency;
    private String companyName;

    public Vacancy(Long vacancyId, String name, Integer areaId, String areaName, Long salaryFrom, Long salaryTo, String salaryCurrency, String companyName) {
        this.vacancyId = vacancyId;
        this.name = name;
        this.areaId = areaId;
        this.areaName = areaName;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.salaryCurrency = salaryCurrency;
        this.companyName = companyName;
    }
}
