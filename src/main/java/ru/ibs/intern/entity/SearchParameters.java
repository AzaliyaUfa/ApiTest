package ru.ibs.intern.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchParameters {

    private String name;
    private Integer areaId; //// проработать с бд!
    private String areaName; //// проработать с бд!
    private String onlyWithSalary = "false";
    private Long salaryFrom;
    private Long salaryTo;

}
