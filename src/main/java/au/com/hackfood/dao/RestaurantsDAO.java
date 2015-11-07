package au.com.hackfood.dao;

import au.com.hackfood.model.Restaurant;

import java.util.Date;
import java.util.List;

public interface RestaurantsDAO {
    List<Restaurant> findAll();

    Restaurant findRestaurantByName(String name);

    Long save(Restaurant r);

}
