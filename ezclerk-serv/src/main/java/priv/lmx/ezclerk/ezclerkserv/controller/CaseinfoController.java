package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.lmx.ezclerk.ezclerkserv.domain.ICourtCaseRep;
import priv.lmx.ezclerk.ezclerkserv.domain.ILitiPartRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.service.CaseinfoService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
public class CaseinfoController {

    @Autowired
    public CaseinfoService caseinfoService;

    @Autowired
    public ICourtCaseRep iEzclerkRep;

    @Autowired
    public ILitiPartRep iLitiPartRep;

    @RequestMapping("saveCase")
    public CourtCase saveCase(@RequestBody CourtCase courtCase) {
        iEzclerkRep.save(courtCase);
        return courtCase;
    }

    @RequestMapping("allCase")
    public List<CourtCase> getCases() {
        return iEzclerkRep.findAll();
    }

    @RequestMapping(value = "getCaseByid", method = RequestMethod.GET)
    public CourtCase getCaseByid(@RequestParam(value = "caseId") String id) {
        Long caseId = Long.parseLong(id);
        return caseinfoService.getCaseByid(caseId);
    }


    @RequestMapping("addlitipart")
    public LitiPart add(@RequestBody LitiPart litiPart) {
        if (litiPart.getCaseId() != null) {
            return iLitiPartRep.save(litiPart);
        }
        return null;
    }

    @RequestMapping(value = "createDoc")
    public void createDoc(HttpServletResponse response, @RequestBody Map map) {
        LawsDocType lawsDocType;
        switch (1) {
            case 1:
                lawsDocType = LawsDocType.CHUANPIAO;
                break;
            case 2:
                lawsDocType = LawsDocType.YINGSUTONGZHISHU;
                break;

            default:
                lawsDocType = LawsDocType.CHUANPIAO;
                break;
        }

        Long caseIdL = ((Integer) map.get("caseId")).longValue();
        Long litiPartIdL = ((Integer) map.get("litiPartId")).longValue();
        caseinfoService.createLawsDoc(response, caseIdL, litiPartIdL, lawsDocType);
    }

    @RequestMapping(value = "createDocTest")
    public void createDocTest(HttpServletResponse response) {
        LawsDocType lawsDocType = LawsDocType.CHUANPIAO;

        caseinfoService.createLawsDoc(response, 1L, 1L, lawsDocType);
    }

}
