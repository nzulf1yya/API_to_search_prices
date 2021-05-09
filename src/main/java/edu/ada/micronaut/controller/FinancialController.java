package edu.ada.micronaut.controller;

public interface FinancialController {

    Object getFinancialData(String financial_data_provider, String stock_index);

}
