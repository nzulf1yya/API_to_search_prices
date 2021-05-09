package edu.ada.micronaut.controller.impl;

import edu.ada.micronaut.controller.FinancialController;
import edu.ada.micronaut.service.FinancialService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/financial")
public class FinancialControllerImpl implements FinancialController {

    protected static final Logger logger = LoggerFactory.getLogger(FinancialControllerImpl.class);

    @Inject
    private FinancialService financialService;

    @Override
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Object getFinancialData(
            @QueryValue("provider") String financial_data_provider,
            @QueryValue("stock_index") String stock_index
    ) {
        if(financial_data_provider.equals("yahoo")) {
            return financialService.getFinancialData(stock_index.toUpperCase());
        } else {
            return HttpResponse.badRequest("Provider Name Is Invalid");
        }
    }

}
