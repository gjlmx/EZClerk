package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.lmx.ezclerk.ezclerkserv.core.Constant;
import priv.lmx.ezclerk.ezclerkserv.domain.IEzclerkRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiRepre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @lmx
 */
@RestController
public class HelloWorldController {


    @Autowired
    public IEzclerkRep iEzclerkRep;

    @RequestMapping("json/courtCase")
    public CourtCase json() {
        CourtCase s = new CourtCase();
        s.setCaseName("1414h");
        List<CourtCase> list = new ArrayList<>();
        list.add(s);
        iEzclerkRep.save(s);
        return iEzclerkRep.findByCaseName("1414h");
    }

    @RequestMapping("json/addlitipart")
    public String add(@RequestBody LitiPart litiPart){
        System.out.println(litiPart.toString());
        return "succ";


    }
    @RequestMapping("json/litipart")
    public List<LitiPart> jsonb() {
       LitiPart result = new LitiPart();
       result.setIsPson(Boolean.TRUE);
       result.setAddr("青岛市崂山区深圳路111号");
       result.setAge(23);
       result.setType(Constant.LITI_PART_TYPE_YG);
       result.setGmsfhm("370202192301011111");
       result.setLawsPartType(Constant.LAWS_PART_TYPE_GM);
       result.setSex("男");
       result.setCsrq(new Date());
       result.setMinzu("汉族");
       result.setName("张三");
        LitiRepre litiRepre = new LitiRepre();
        litiRepre.setName("苦律师");
       litiRepre.setDanwei("sfhhsuahu律师事务所");
       litiRepre.setPhoneNum("12345663453");
       List<LitiRepre> litiRepres = new ArrayList<>();
       litiRepres.add(litiRepre);
       litiRepres.add(litiRepre);
       result.setLitiRepres(litiRepres);
       result.setLitiRepresInfo(litiRepre.toString());
       List<LitiPart> litiParts = new ArrayList<>();
       litiParts.add(result);
       litiParts.add(result);
       return litiParts;
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
