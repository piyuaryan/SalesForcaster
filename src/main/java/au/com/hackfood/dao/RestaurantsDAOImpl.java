package au.com.hackfood.dao;

import au.com.hackfood.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
//@Transactional(propagation = Propagation.REQUIRED)
public class RestaurantsDAOImpl extends AbstractDAO implements RestaurantsDAO {

    @Transactional
    public List<Restaurant> findAll() {
        CriteriaQuery<Restaurant> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(Restaurant.class);
        criteriaQuery.select(criteriaQuery.from(Restaurant.class));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        String qry = "SELECT r FROM Restaurant r WHERE r.name = :name";
        TypedQuery<Restaurant> query = getEntityManager().createQuery(qry, Restaurant.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Transactional
    public Long save(Restaurant r) {
        persist(r);
        flush();
        return r.getId();
    }
}
