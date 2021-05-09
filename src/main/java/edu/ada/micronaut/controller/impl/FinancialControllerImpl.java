package edu.ada.micronaut.controller.impl;

import edu.ada.micronaut.controller.FinancialController;
import edu.ada.micronaut.service.FinancialService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
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
            logger.info(financial_data_provider);
            return financialService.getFinancialData(stock_index.toUpperCase());
        } else {
            logger.info(financial_data_provider);
            return HttpResponse.badRequest("Provider Name Is Invalid");
        }
    }

}
