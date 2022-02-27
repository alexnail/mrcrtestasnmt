package com.alexnail.mrcrtestasnmt.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;

@Service
public class FixerClient implements ExternalRatesClient {

    @Value("${exchangerate.remote.service.url}")
    private String baseUrl;

    @Value("#{ @environment['exchangerate.remote.service.key'] }")
    private String accessKey;

    @Override
    public FixerResponseModel latest() {
        return new RestTemplate()
                .getForObject(baseUrl + "/latest/?access_key={key}", FixerResponseModel.class, Map.of("key", accessKey));
    }
}
