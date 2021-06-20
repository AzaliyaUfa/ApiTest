package ru.ibs.intern.service;

import org.springframework.stereotype.Service;
import ru.ibs.intern.entity.SearchParameters;


@Service
public class SearchServiceImpl {

    private final static String vacanciesUrl = "https://api.hh.ru/vacancies";

    public static String getSearchURL (SearchParameters params) {

        StringBuilder sb = new StringBuilder(vacanciesUrl);

        if (params.getName() != null)
            sb.append("?text=" + params.getName() + "&search_field=name");

        if (params.getAreaName() != null)
            sb.append("&area=" + params.getAreaName()); //// проработать с бд!

        if (params.getOnlyWithSalary() != null)
            sb.append("&only_with_salary=" + params.getOnlyWithSalary());

        if (params.getSalaryFrom() != null)
            sb.append("&area=" + params.getAreaName()); /// ?????

        if (params.getSalaryTo() != null)
            sb.append("&area=" + params.getSalaryTo()); /// ?????

        return sb.toString();

    }




}
