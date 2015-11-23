package au.com.salesforecaster.dao;

import au.com.salesforecaster.model.WeatherAndEvents;

import java.util.Date;
import java.util.List;

public interface WeatherAndEventsDAO {
    List<WeatherAndEvents> findAll();

    List<WeatherAndEvents> findByDate(Date dt);

    List<WeatherAndEvents> findByEventName(String eventName);

    Long save(WeatherAndEvents rs);
}
