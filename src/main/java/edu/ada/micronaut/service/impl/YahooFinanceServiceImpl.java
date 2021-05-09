package edu.ada.micronaut.service.impl;

import edu.ada.micronaut.service.FinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;

@Singleton
public class YahooFinanceServiceImpl implements FinancialService {

    protected static final Logger logger = LoggerFactory.getLogger(YahooFinanceServiceImpl.class);


    @Override
    public Object getFinancialData(String stock_index) {

        Stock stock = null;
        BigDecimal price = BigDecimal.ZERO;
        try {
            stock = YahooFinance.get(stock_index);
            price = stock.getQuote(true).getPrice();
            return price;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return price;
    }

}

