package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.lmx.ezclerk.ezclerkserv.domain.IEzclerkRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @lmx
 */
@RestController
public class HelloWorldController {

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public IEzclerkRep iEzclerkRep;

    @RequestMapping("json")
    public Boolean json() {
//        Inner i = new Inner();
//        redisTemplate.execute(new RedisCallback<Object>() {
//
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                connection.set(redisTemplate.getStringSerializer().serialize(i.name+""),
//                        redisTemplate.getStringSerializer().serialize(i.age+""));
//                return null;
//            }
//        });
//        return i;
        CourtCase s = new CourtCase();
        s.setCaseName("1414h");
        List<CourtCase> list = new ArrayList<>();
        list.add(s);
        iEzclerkRep.save(s);
        return true;
    }

    public class Hi{
        Inner inner = Inner.HII;
        String name = "names";
    }
    enum Inner {
       HII("bi"),SJFI("sf");
        private String name;
        Inner(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    }

}
