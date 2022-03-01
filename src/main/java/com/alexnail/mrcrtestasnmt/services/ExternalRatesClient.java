package com.alexnail.mrcrtestasnmt.services;

import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;

public interface ExternalRatesClient {

    FixerResponseModel getLatestRates();

}
