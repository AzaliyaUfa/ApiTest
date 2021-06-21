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
    private String areaName;
    private boolean onlyWithSalary = true;
    private Long salaryFrom; //// проработать
    private Long salaryTo; //// проработать

}
