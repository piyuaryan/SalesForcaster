package au.com.hackfood.dao;

import au.com.hackfood.model.Restaurant;
import au.com.hackfood.model.RestaurantsSale;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional(propagation = Propagation.REQUIRED)
public class RestaurantsSaleDAOImpl extends AbstractDAO implements RestaurantsSaleDAO {

    @Transactional
    public List<RestaurantsSale> findAll() {
        CriteriaQuery<RestaurantsSale> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(RestaurantsSale.class);
        criteriaQuery.select(criteriaQuery.from(RestaurantsSale.class));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public RestaurantsSale findSalesByDateAndType(Restaurant r, Date dt, String cakeType) {
        String qry = "SELECT rs FROM RestaurantsSale rs WHERE rs.restaurant = :rst and rs.saleDate = :dt and rs.cakeType = :tp";
        TypedQuery<RestaurantsSale> query = getEntityManager().createQuery(qry, RestaurantsSale.class);
        query.setParameter("rst", r);
        query.setParameter("dt", dt);
        query.setParameter("tp", cakeType);
        return query.getSingleResult();
    }

    @Override
    public List<RestaurantsSale> findSalesByRestaurant(Restaurant r) {
        String qry = "SELECT rs FROM RestaurantsSale rs WHERE rs.retaurant = :r";
        TypedQuery<RestaurantsSale> query = getEntityManager().createQuery(qry, RestaurantsSale.class);
        query.setParameter("r", r);
        return query.getResultList();
    }

    @Override
    public List<RestaurantsSale> findSalesForRestaurantByDate(Restaurant r, Date dt) {
        String qry = "SELECT rs FROM RestaurantsSale rs WHERE rs.retaurant = :r and rs.saleDate = :dt";
        TypedQuery<RestaurantsSale> query = getEntityManager().createQuery(qry, RestaurantsSale.class);
        query.setParameter("r", r);
        query.setParameter("dt", dt);
        return query.getResultList();
    }

    @Transactional
    public Long save(RestaurantsSale rs) {
        persist(rs);
        flush();
        return rs.getId();
    }
}
