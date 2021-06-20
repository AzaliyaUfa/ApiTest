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
public class Currency {

    @Id
    private String currencyCode;
    private Double currencyRate;
    private String currencyName;

}
