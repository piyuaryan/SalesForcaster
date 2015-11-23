package au.com.salesforecaster.service;

import au.com.salesforecaster.dao.RestaurantsDAO;
import au.com.salesforecaster.dao.RestaurantsSaleDAO;
import au.com.salesforecaster.dao.WeatherAndEventsDAO;
import au.com.salesforecaster.dao.WeeklyProfitsDAO;
import au.com.salesforecaster.model.WeeklyProfit;
import au.com.salesforecaster.util.AppUtils;
import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DataServiceImpl implements DataService {

    final static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

    @Autowired
    private WeeklyProfitsDAO wpDao;

    @Autowired
    private WeatherAndEventsDAO waeDao;

    @Autowired
    private RestaurantsDAO rstDao;

    @Autowired
    private RestaurantsSaleDAO rSaleDao;

    public List<WeeklyProfit> findAll() {
        return wpDao.findAll();
    }

    @Override
    public String getWeatherInfo() throws Exception {
        String result = null;
        try {

//            Restaurant r = rstDao.findRestaurantByName("R1");
//            List<RestaurantsSale> saleList = rSaleDao.findSalesForRestaurantByDate(r, new Date());

//            if (saleList != null && !saleList.isEmpty()) {
//                List<WeatherAndEvents> eventList = waeDao.findByDate(new Date());
//
//            }

            OwmClient owm = new OwmClient();
            //owm.setAPPID("8b49c14ff3e8031b1b5d155cd0f3d7da");
            WeatherStatusResponse currentWeather = owm.currentWeatherAtCity("Tokyo", "JP");
            if (currentWeather.hasWeatherStatus()) {
                WeatherData weather = currentWeather.getWeatherStatus().get(0);
                if (weather.getPrecipitation() == Integer.MIN_VALUE) {
                    WeatherData.WeatherCondition weatherCondition = weather.getWeatherConditions().get(0);
                    String description = weatherCondition.getDescription();
                    if (description.contains("rain") || description.contains("shower"))
                        System.out.println("No rain measures in Tokyo but reports of " + description);
                    else
                        System.out.println("No rain measures in Tokyo: " + description);
                } else
                    System.out.println("It's raining in Tokyo: " + weather.getPrecipitation() + " mm/h");
            }

            result = AppUtils.SUCCESS;
        } catch (JSONException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw e;
        }
//        catch (Exception e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            throw e;
//        }
        return result;
    }


}
