package priv.lmx.ezclerk.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import priv.lmx.ezclerk.core.entity.TestEntity;

@Repository
public class TestDao {


    @Autowired
    private SessionFactory sessionFactory;


    public TestEntity test(){
        TestEntity testEntity = new TestEntity();
        testEntity.setId(1);
        testEntity.setName("aaaaaa");
        Session session = sessionFactory.openSession();
        testEntity = session.load(TestEntity.class,1);
        testEntity.setName("afisauhdfi");
        session.save(testEntity);


        TestEntity testEntity2 = sessionFactory.openSession().get(TestEntity.class,1);
       // return testEntity;
       String s =  testEntity.toString();
//        return  (TestEntity) sessionFactory.openSession().get(TestEntity.class,1);
        return  testEntity;
    }


}
