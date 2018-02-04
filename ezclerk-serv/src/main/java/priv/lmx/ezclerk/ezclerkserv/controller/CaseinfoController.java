package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import priv.lmx.ezclerk.ezclerkserv.domain.entity.CaseLog;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.service.CaseinfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CaseinfoController {

    @Autowired
    public CaseinfoService caseinfoService;

    @RequestMapping("saveCase")
    public CourtCase saveCase(@RequestBody CourtCase courtCase) {
        return caseinfoService.saveCase(courtCase);
    }

    @RequestMapping("getCaseLogs")
    public List<CaseLog> getCaseLogs(@RequestParam(value = "caseId") String id){
        Long caseId = Long.parseLong(id);
        return caseinfoService.getCaseLogs(caseId);
    }

    @RequestMapping("saveCaseLogs")
    public CaseLog saveCaseLogs(@RequestBody CaseLog caseLog){
        return caseinfoService.saveCaseLogs(caseLog);
    }

    @RequestMapping("allCase")
    public List<CourtCase> getCases() {
        return caseinfoService.findAllCases();
    }

    @RequestMapping(value = "getCaseByid", method = RequestMethod.GET)
    public CourtCase getCaseByid(@RequestParam(value = "caseId") String id) {
        Long caseId = Long.parseLong(id);
        return caseinfoService.getCaseByid(caseId);
    }


    @RequestMapping("addlitipart")
    public LitiPart add(@RequestBody LitiPart litiPart) {
        if (litiPart.getCaseId() != null) {
            return caseinfoService.saveLP(litiPart);
        }
        return null;
    }

    @RequestMapping(value = "createDoc")
    public void createDoc(HttpServletResponse response, @RequestParam(value = "caseId") String caseId,
                          @RequestParam(value = "litiPartId") String litiPartId,
                          @RequestParam(value = "lawsDocType") String lawsDocType) {
        LawsDocType lawsDocTypeL;
        switch (Integer.parseInt(lawsDocType)) {
            case 1:
                lawsDocTypeL = LawsDocType.CHUANPIAO;
                break;
            case 2:
                lawsDocTypeL = LawsDocType.YINGSUTONGZHISHU;
                break;
            case 4:
                lawsDocTypeL = LawsDocType.BAOQUAN;

                break;
            default:
                lawsDocTypeL = LawsDocType.FASU;
                break;
        }
        Long caseIdL = Long.parseLong(caseId);
        Long litiPartIdL = Long.parseLong(litiPartId);
        caseinfoService.createLawsDoc(response, caseIdL, litiPartIdL, lawsDocTypeL);
    }

    @RequestMapping(value = "createDocTest")
    public void createDocTest(HttpServletRequest request, HttpServletResponse response) {
        LawsDocType lawsDocType = LawsDocType.CHUANPIAO;
        caseinfoService.createLawsDoc(response, 1L, 1L, lawsDocType);
    }

}
