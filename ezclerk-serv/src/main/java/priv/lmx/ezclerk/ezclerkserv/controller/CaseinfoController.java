package priv.lmx.ezclerk.ezclerkserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.lmx.ezclerk.ezclerkserv.core.Utils;
import priv.lmx.ezclerk.ezclerkserv.domain.ICourtCaseRep;
import priv.lmx.ezclerk.ezclerkserv.domain.ILitiPartRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.service.CaseinfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        Utils.caseInfoFormat(courtCase);
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
