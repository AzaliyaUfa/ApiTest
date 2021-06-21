package ru.ibs.intern.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.ibs.intern.entity.SearchParameters;
import ru.ibs.intern.entity.Vacancy;
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

    @Autowired
    SearchServiceImpl searchService;

    /*@Autowired
    ObjectMapper objectMapper;*/

    @PostConstruct
    public void getRate() {
        dictionary.getCurrentRate();
        dictionary.getAreas();
    }

    @PostMapping("allVacancies")
    public String getAllVacancies() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    @PostMapping("javaVacancies")
    public String getJavaVac() {
        ResponseEntity<String> response = restTemplate.exchange(url + "?text=java", HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    /*@PostMapping(value = "mapper", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getJavaVacanyMapper(@RequestBody SearchParameters params) {
        String searchURL = searchService.getSearchURL(params);
        vacancies.getVacancies1(200, searchURL);
        return "mapper done";
    }*/

//    {"name": "java", "areaName": "Москва", "onlyWithSalary": true}
//    {"name": "java", "areaName": "Томская область", "onlyWithSalary": true}
//      {"name": "java", "areaName": "all", "onlyWithSalary": true}
//    http://localhost:8080/searchByParams

    @PostMapping(value = "searchByParams", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getVacancies(@RequestBody SearchParameters params) {
        vacancies.truncateTable();
        String searchURL = searchService.getSearchURL(params);
        vacancies.getVacancies(30, searchURL);
        return "Got it.";
    }

    @PostMapping(value = "javaSearch", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String  getJavaVacancies() {
        vacancies.getVacancies(30, url + "?text=java");
        return "Поиск завершён"; //// пока работает
    }

}
