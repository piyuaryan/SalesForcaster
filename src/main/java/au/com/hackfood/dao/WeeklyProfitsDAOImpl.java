package au.com.hackfood.dao;

import au.com.hackfood.model.WeeklyProfit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;
import java.util.List;

@Repository
//@Transactional(propagation = Propagation.REQUIRED)
public class WeeklyProfitsDAOImpl extends AbstractDAO implements WeeklyProfitsDAO {

    @Transactional
    public List<WeeklyProfit> findAll() {
        CriteriaQuery<WeeklyProfit> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(WeeklyProfit.class);
        criteriaQuery.select(criteriaQuery.from(WeeklyProfit.class));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<WeeklyProfit> getProfitsByWeek(Date dt) {
        String qry = "SELECT wp FROM WeeklyProfit wp WHERE wp.weekEndingAt = :dt";
        TypedQuery<WeeklyProfit> query = getEntityManager().createQuery(qry, WeeklyProfit.class);
        query.setParameter("dt", dt);
        return query.getResultList();
    }

    @Transactional
    public WeeklyProfit findProfits(String name, String nameOfBusiness, Date dt) {
        String qry = "SELECT wp FROM WeeklyProfit wp WHERE wp.name = :nm and wp.nameOfBusiness = :businessName and wp.weekEndingAt = :dt";
        TypedQuery<WeeklyProfit> query = getEntityManager().createQuery(qry, WeeklyProfit.class);
        query.setParameter("nm", name);
        query.setParameter("businessName", nameOfBusiness);
        query.setParameter("dt", dt);
        return query.getSingleResult();
    }

    @Transactional
    public Long save(WeeklyProfit wp) {
        persist(wp);
        flush();
        return wp.getId();
    }
}
