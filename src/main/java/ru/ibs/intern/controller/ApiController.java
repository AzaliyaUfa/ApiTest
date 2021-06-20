package ru.ibs.intern.controller;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.ibs.intern.entity.SearchParameters;
import ru.ibs.intern.service.DictionariesServiceImpl;
import ru.ibs.intern.service.SearchServiceImpl;
import ru.ibs.intern.service.VacanciesServiceImpl;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/", consumes = {MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    static String url = "https://api.hh.ru/vacancies";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DictionariesServiceImpl dictionary;

    @Autowired
    VacanciesServiceImpl vacancies;

    @PostConstruct
    public void getRate() {
        dictionary.getCurrentRate();
        dictionary.getAreas();
    }

    @GetMapping("allVacancies")
    public String getAllVacancies() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @GetMapping("javaVacancies")
    public String getJavaVac() {
        ResponseEntity<String> response = restTemplate.exchange(url + "?text=java", HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getVacancies(@RequestBody SearchParameters params) {
        String searchURL = SearchServiceImpl.getSearchURL(params);
        vacancies.getVacancies(20, searchURL);
        return "Got it.";
    }

    @PostMapping(value = "javaSearch", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String  getJavaVacancies() {
        vacancies.getVacancies(20, url + "?text=java");
        return "Поиск завершён"; //// пока работает
    }

}
