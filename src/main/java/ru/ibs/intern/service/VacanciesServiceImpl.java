package ru.ibs.intern.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ibs.intern.entity.Vacancy;
import ru.ibs.intern.repositories.VacanciesRepo;


@Service
public class VacanciesServiceImpl {

    @Autowired
    private VacanciesRepo vacanciesRepo;

    private final static RestTemplate restTemplate = new RestTemplate();

    public void getVacancies(int requiredVacanciesNumber, String url) {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            JsonNode currentVacanciesList = restTemplate.exchange(url + "&page=" + i, HttpMethod.GET
                                            , null, JsonNode.class).getBody();
            for (int j = 0; j < 20; j++) {
                Vacancy vacancy = new Vacancy();
                writeData(currentVacanciesList.get("items").get(j), vacancy);
                vacanciesRepo.save(vacancy);
                count++;
            }
            if(count == requiredVacanciesNumber) break;

        }
    }

    private void writeData(JsonNode currentHHVacancy, Vacancy vacancy) {

        if (!currentHHVacancy.get("salary").isNull()) {
            if (!currentHHVacancy.get("salary").get("from").isNull()) {
                vacancy.setSalaryFrom(currentHHVacancy.get("salary").get("from").asLong());
            }
            if (!currentHHVacancy.get("salary").get("to").isNull()) {
                vacancy.setSalaryTo(currentHHVacancy.get("salary").get("to").asLong());
            }
            if (!currentHHVacancy.get("salary").get("currency").isNull()) {
                vacancy.setSalaryCurrency(currentHHVacancy.get("salary").get("currency").asText());
            }
        }

        if (!currentHHVacancy.get("id").isNull())
            vacancy.setVacancyId(currentHHVacancy.get("id").asLong());

        if (!currentHHVacancy.get("name").isNull())
            vacancy.setName(currentHHVacancy.get("name").asText());

        if (!currentHHVacancy.get("area").isNull()) {
            if (!currentHHVacancy.get("area").get("id").isNull()) {
                vacancy.setAreaId(currentHHVacancy.get("area").get("id").asInt());
            }
            if (!currentHHVacancy.get("area").get("name").isNull()) {
                vacancy.setAreaName(currentHHVacancy.get("area").get("name").asText());
            }
        }
        if (!currentHHVacancy.get("employer").isNull()) {
            if (!currentHHVacancy.get("employer").get("name").isNull()) {
                vacancy.setCompanyName(currentHHVacancy.get("employer").get("name").asText());
            }
        }
    }

}
