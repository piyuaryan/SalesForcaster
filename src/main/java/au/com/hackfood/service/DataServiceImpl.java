package au.com.hackfood.service;

import au.com.hackfood.dao.WeeklyProfitsDAO;
import au.com.hackfood.model.Subjects;
import au.com.hackfood.model.WeeklyProfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DataServiceImpl implements DataService {

    @Autowired
    private WeeklyProfitsDAO wpDao;

    public List<WeeklyProfit> findAll() {
        return wpDao.findAll();
    }
}
