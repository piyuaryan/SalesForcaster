package au.com.hackfood.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weather_and_events")
public class WeatherAndEvents extends AbstractTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    private String eventName;
    private String eventLocation;
    private Date eventDate;
    private Double temperature;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof WeatherAndEvents))
            return false;

        WeatherAndEvents other = (WeatherAndEvents) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        //TODO: Piyush: Return in JSON format
        return "WeeklyProfit [id=" + id + ", [Restaurant=" + restaurant + "], eventName=" + eventName + ", eventLocation=" + eventLocation + ", eventDate=" + eventDate
                + ", temperature=" + temperature + "]";
    }

}
