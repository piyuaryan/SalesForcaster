package au.com.hackfood.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String location;
    private Double lat;
    private Double lon;

    private Date dateEstablished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(Date dateEstablished) {
        this.dateEstablished = dateEstablished;
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

        if (!(obj instanceof Restaurant))
            return false;

        Restaurant other = (Restaurant) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        //TODO: Piyush: Return in JSON format
        return "WeeklyProfit [id=" + id + ", name=" + name + ", address=" + address + ", lat=" + lat + ", lon=" + lon
                + ", location=" + location + " dateEstablished=" + dateEstablished + "]";
    }

}
