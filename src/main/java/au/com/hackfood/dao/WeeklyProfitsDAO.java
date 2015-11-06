package au.com.hackfood.dao;

import au.com.hackfood.model.WeeklyProfit;

import java.util.Date;
import java.util.List;

public interface WeeklyProfitsDAO {
    List<WeeklyProfit> findAll();

    List<WeeklyProfit> getProfitsByWeek(Date dt);

    WeeklyProfit findProfits(String name, String nameOfBusiness, Date dt);

    Long save(WeeklyProfit wp);

}
