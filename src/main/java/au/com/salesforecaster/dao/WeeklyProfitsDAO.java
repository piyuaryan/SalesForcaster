package au.com.salesforecaster.dao;

import au.com.salesforecaster.model.WeeklyProfit;

import java.util.Date;
import java.util.List;

public interface WeeklyProfitsDAO {
    List<WeeklyProfit> findAll();

    List<WeeklyProfit> getProfitsByWeek(Date dt);

    WeeklyProfit findProfits(String name, String nameOfBusiness, Date dt);

    Long save(WeeklyProfit wp);

}
