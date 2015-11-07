package au.com.hackfood.dao;

import au.com.hackfood.model.WeatherAndEvents;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional(propagation = Propagation.REQUIRED)
public class WeatherAndEventsDAOImpl extends AbstractDAO implements WeatherAndEventsDAO {

    @Transactional
    public List<WeatherAndEvents> findAll() {
        CriteriaQuery<WeatherAndEvents> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(WeatherAndEvents.class);
        criteriaQuery.select(criteriaQuery.from(WeatherAndEvents.class));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<WeatherAndEvents> findByDate(Date dt) {
        String qry = "SELECT wa FROM WeatherAndEvents wa WHERE wa.eventDate = :dt";
        TypedQuery<WeatherAndEvents> query = getEntityManager().createQuery(qry, WeatherAndEvents.class);
        query.setParameter("dt", dt);
        return query.getResultList();
    }

    @Override
    public List<WeatherAndEvents> findByEventName(String eventName) {
        String qry = "SELECT wa FROM WeatherAndEvents wa WHERE wa.eventName = :name";
        TypedQuery<WeatherAndEvents> query = getEntityManager().createQuery(qry, WeatherAndEvents.class);
        query.setParameter("name", eventName);
        return query.getResultList();
    }

    @Transactional
    public Long save(WeatherAndEvents wa) {
        persist(wa);
        flush();
        return wa.getId();
    }
}
