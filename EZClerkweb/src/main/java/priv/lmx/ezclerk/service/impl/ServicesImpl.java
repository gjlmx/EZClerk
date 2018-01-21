package priv.lmx.ezclerk.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lmx.ezclerk.core.entity.TestEntity;
import priv.lmx.ezclerk.dao.TestDao;
import priv.lmx.ezclerk.service.IServices;


@Service
public class ServicesImpl implements IServices {

    private static final Logger logger = LoggerFactory.getLogger(ServicesImpl.class);
    @Autowired
    private TestDao testDao;

    public TestEntity test() {
        TestEntity entity = testDao.test();
        logger.error(entity.getName().toString());
        return entity;
    }
}
