package com.alexnail.mrcrtestasnmt.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;

@Service
public class FixerClient implements ExternalRatesClient {

    @Value("${exchangerate.remote.service.url:http://data.fixer.io/api}") //TODO: no idea why it's not being resolved but the key is
    private String baseUrl;

    @Value("${exchangerate.remote.service.key}")
    private String accessKey;

    @Override
    public FixerResponseModel getLatestRates() {
        return new RestTemplate()
                .getForObject(baseUrl + "/latest?access_key={key}", FixerResponseModel.class, Map.of("key", accessKey));
    }
}
