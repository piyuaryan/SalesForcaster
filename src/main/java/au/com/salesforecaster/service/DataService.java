package au.com.salesforecaster.service;

import au.com.salesforecaster.model.WeeklyProfit;

import java.util.List;

public interface DataService {
    List<WeeklyProfit> findAll();

    String getWeatherInfo() throws Exception;
}
