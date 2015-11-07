package au.com.hackfood.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restaurants_sale")
public class RestaurantsSale extends AbstractTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    private String cakeType;
    private Date saleDate;
    private String month;
    private String day;
    private Integer itemsLeft;
    private Integer itemsMade;
    private Double saleAmt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCakeType() {
        return cakeType;
    }

    public void setCakeType(String cakeType) {
        this.cakeType = cakeType;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getItemsLeft() {
        return itemsLeft;
    }

    public void setItemsLeft(Integer itemsLeft) {
        this.itemsLeft = itemsLeft;
    }

    public Integer getItemsMade() {
        return itemsMade;
    }

    public void setItemsMade(Integer itemsMade) {
        this.itemsMade = itemsMade;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Double getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(Double saleAmt) {
        this.saleAmt = saleAmt;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

        if (!(obj instanceof RestaurantsSale))
            return false;

        RestaurantsSale other = (RestaurantsSale) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        //TODO: Piyush: Return in JSON format
        return "WeeklyProfit [id=" + id + ", [Restaurant=" + restaurant + "], cakeType=" + cakeType + ", saleDate=" + saleDate
                + ", month=" + month + ", day=" + day + ", itemsLeft=" + itemsLeft + ", itemsMade=" + itemsMade + "]";
    }

}
