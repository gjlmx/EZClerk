package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.lmx.ezclerk.ezclerkserv.domain.IEzclerkRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;

import java.util.List;
import java.util.Map;

@RestController
public class CaseinfoController {

    @Autowired
    public IEzclerkRep iEzclerkRep;

    @RequestMapping("saveCase")
    public CourtCase saveCase(@RequestBody CourtCase courtCase){
        iEzclerkRep.save(courtCase);
        return courtCase;
    }

    @RequestMapping("allCase")
    public List<CourtCase> getCases(){
        return iEzclerkRep.findAll();
    }

    @GetMapping("getCaseByid" )
    public CourtCase getCaseByid(@RequestBody Map<String,Object> reqMap){
        Long id = (Long) reqMap.get("0");
        return iEzclerkRep.findOne(id);
    }
}
