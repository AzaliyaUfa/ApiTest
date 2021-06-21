package ru.ibs.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ibs.intern.entity.SearchParameters;
import ru.ibs.intern.repositories.AreasRepo;


@Service
public class SearchServiceImpl {

    @Autowired
    AreasRepo areasRepo;

    public String getSearchURL (SearchParameters params) {

        String vacanciesUrl = "https://api.hh.ru/vacancies";
        StringBuilder sb = new StringBuilder(vacanciesUrl);

        if (params.getName() != null)
            sb.append("?text=").append(params.getName()).append("&search_field=name");

        if (params.getAreaName().equals("all")) {
            sb.append("&area=113");
        } else if(params.getAreaName() != null) {
            sb.append("&area=").append(areasRepo.findByAreaName(params.getAreaName()).getId());
        }

        if (params.isOnlyWithSalary())
            sb.append("&only_with_salary=true");

        if (params.getSalaryFrom() != null)
            sb.append("&********=").append(params.getAreaName()); /// ?????

        if (params.getSalaryTo() != null)
            sb.append("&&********==").append(params.getSalaryTo()); /// ?????

        return sb.toString();

    }




}
