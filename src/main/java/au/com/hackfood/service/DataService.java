package au.com.hackfood.service;

import au.com.hackfood.model.WeeklyProfit;

import java.util.List;

public interface DataService {
    List<WeeklyProfit> findAll();
}
