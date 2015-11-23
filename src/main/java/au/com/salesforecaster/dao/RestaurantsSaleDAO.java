package au.com.salesforecaster.dao;

import au.com.salesforecaster.model.Restaurant;
import au.com.salesforecaster.model.RestaurantsSale;

import java.util.Date;
import java.util.List;

public interface RestaurantsSaleDAO {
    List<RestaurantsSale> findAll();

    RestaurantsSale findSalesByDateAndType(Restaurant r, Date dt, String cakeType);

    List<RestaurantsSale> findSalesByRestaurant(Restaurant r);

    List<RestaurantsSale> findSalesForRestaurantByDate(Restaurant r, Date dt);

    Long save(RestaurantsSale rs);

}
