package ru.ibs.intern.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ibs.intern.entity.Area;
import ru.ibs.intern.entity.Currency;
import ru.ibs.intern.repositories.AreasRepo;
import ru.ibs.intern.repositories.CurrenciesRepo;

@Service
public class DictionariesServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrenciesRepo currenciesRepo;

    @Autowired
    private AreasRepo areasRepo;

    private static final String hhApiDictionaries = "https://api.hh.ru/dictionaries";
    private static final String hhApiAreas = "https://api.hh.ru/areas";

    public void getCurrentRate() {

        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiDictionaries, HttpMethod.GET,
                                                                    null, JsonNode.class);
        JsonNode currency = response.getBody().get("currency");

        for (int i = 0; i < currency.size(); i++) {
            currenciesRepo.save(new Currency(currency.get(i).get("code").asText()
                                            ,currency.get(i).get("rate").asDouble()
                                            ,currency.get(i).get("name").asText()));
        }
    }

    /*public Currency getCurrencyNameByCode(String currencyCode) {
        return currenciesRepo.findByCurrencyCode(currencyCode).get();

    }*/

    public void getAreas() {
        ResponseEntity<JsonNode> response = restTemplate.exchange(hhApiAreas, HttpMethod.GET,
                null, JsonNode.class);
        JsonNode areas = response.getBody().get(0).get("areas");

        for (int i = 0; i < areas.size(); i++) {
            areasRepo.save(new Area(areas.get(i).get("id").asInt(), areas.get(i).get("name").asText()));
        }
    }


}
