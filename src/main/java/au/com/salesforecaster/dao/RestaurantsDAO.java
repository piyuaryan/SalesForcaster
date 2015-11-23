package au.com.salesforecaster.dao;

import au.com.salesforecaster.model.Restaurant;

import java.util.List;

public interface RestaurantsDAO {
    List<Restaurant> findAll();

    Restaurant findRestaurantByName(String name);

    Long save(Restaurant r);

}
