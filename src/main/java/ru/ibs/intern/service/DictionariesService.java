package ru.ibs.intern.service;

import ru.ibs.intern.entity.Area;
import ru.ibs.intern.entity.Currency;

public interface DictionariesService {

    Currency addCurrency(String code, Double rate, String name);
    Area addArea(Integer id, String name);
}
