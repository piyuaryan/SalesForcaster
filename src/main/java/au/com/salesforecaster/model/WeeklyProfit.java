package au.com.salesforecaster.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weekly_profit")
public class WeeklyProfit extends AbstractTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String nameOfBusiness;
    private Date weekEndingAt;
    private Double sale;
    private Double approximateProfit;
    private String events;

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

    public String getNameOfBusiness() {
        return nameOfBusiness;
    }

    public void setNameOfBusiness(String nameOfBusiness) {
        this.nameOfBusiness = nameOfBusiness;
    }

    public Date getWeekEndingAt() {
        return weekEndingAt;
    }

    public void setWeekEndingAt(Date weekEndingAt) {
        this.weekEndingAt = weekEndingAt;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Double getApproximateProfit() {
        return approximateProfit;
    }

    public void setApproximateProfit(Double approximateProfit) {
        this.approximateProfit = approximateProfit;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
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

        if (!(obj instanceof WeeklyProfit))
            return false;

        WeeklyProfit other = (WeeklyProfit) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        //TODO: Piyush: Return in JSON format
        return "WeeklyProfit [id=" + id + ", name=" + name + ", nameOfBusiness=" + nameOfBusiness + ", weekEndingAt=" + weekEndingAt
                + ", sale=" + sale + ", approximateProfit=" + approximateProfit + ", events=" + events + "]";
    }

}
