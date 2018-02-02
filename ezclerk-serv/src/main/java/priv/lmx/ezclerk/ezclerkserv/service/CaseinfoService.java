package priv.lmx.ezclerk.ezclerkserv.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lmx.ezclerk.ezclerkserv.core.Utils;
import priv.lmx.ezclerk.ezclerkserv.domain.ICaseLogRep;
import priv.lmx.ezclerk.ezclerkserv.domain.ICourtCaseRep;
import priv.lmx.ezclerk.ezclerkserv.domain.ILitiPartRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CaseLog;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CaseinfoService {

    @Autowired
    Configuration configuration;

    @Autowired
    ICourtCaseRep iEzclerkRep;

    @Autowired
    ILitiPartRep iLitiPartRep;

    @Autowired
    ICaseLogRep iCaseLogRep;


    public CourtCase getCaseByid(Long caseId) {
        CourtCase courtCase = iEzclerkRep.findOne(caseId);
        List<LitiPart> litiParts = iLitiPartRep.findByCaseId(caseId);
        courtCase.setLitiParts(litiParts);
        return courtCase;
    }

    public List<CaseLog> getCaseLogs(Long caseId){
        return iCaseLogRep.findByCaseId(caseId);
    }

    public CaseLog saveCaseLogs(CaseLog caseLog){
        return iCaseLogRep.save(caseLog);
    }

    public File createLawsDoc(HttpServletResponse response,Long caseId, Long  litiPartId, LawsDocType lawsDocType) {
        if(litiPartId == null){
            return createLawsDoc(response,getCaseByid(caseId),null, lawsDocType);
        }
        return createLawsDoc(response,getCaseByid(caseId), iLitiPartRep.findOne(litiPartId), lawsDocType);
    }

    public File createLawsDoc(HttpServletResponse response,CourtCase courtCase,LitiPart litiPart, LawsDocType lawsDocType) {
        try {
            Template temple = configuration.getTemplate(lawsDocType.getTemlPath());
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> docvalMap = Utils.caseinfoFormat(courtCase,litiPart);
            temple.process(docvalMap, stringWriter);
            String str = stringWriter.toString();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(str.getBytes());
            stringWriter.flush();
            stringWriter.close();
            Utils.renderWord(response,baos.toByteArray(),Utils.getfileName(courtCase,litiPart,lawsDocType));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CourtCase saveCase(CourtCase courtCase) {
        Utils.caseInfoFormat(courtCase);
        return iEzclerkRep.save(courtCase);
    }

    public List<CourtCase> findAllCases() {
        return iEzclerkRep.findAll();
    }

    public LitiPart saveLP(LitiPart litiPart) {
        return iLitiPartRep.save(litiPart);
    }
}
