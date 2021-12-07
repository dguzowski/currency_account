package com.dguzowski.currency.exchange.converter;

import com.dguzowski.currency.exchange.converter.model.CurrencyData;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.logging.Level;

@Log
@Component
public class NBPCurrencyDataProvider implements CurrencyDataProvider {

    private final String url;
    private final RestTemplate restTemplate;

    @Autowired
    public NBPCurrencyDataProvider(@Value("${currency.exchange.nbp.current}") String url, RestTemplateBuilder restTemplateBuilder) {
        this.url = url;
        this.restTemplate = restTemplateBuilder.build();
    }

    public Optional<CurrencyData> getCurrentDollarData() {
        CurrencyData currencyData = restTemplate.getForObject(url, CurrencyData.class);
        if (currencyData == null) {
            log.log(Level.WARNING, "Cannot retrieve valid data from NBP service ");
        }
        return Optional.ofNullable(currencyData);
    }
}
