package priv.lmx.ezclerk.ezclerkserv.service;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lmx.ezclerk.ezclerkserv.core.Utils;
import priv.lmx.ezclerk.ezclerkserv.domain.ICourtCaseRep;
import priv.lmx.ezclerk.ezclerkserv.domain.ILitiPartRep;
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


    public CourtCase getCaseByid(Long caseId) {
        CourtCase courtCase = iEzclerkRep.findOne(caseId);
        List<LitiPart> litiParts = iLitiPartRep.findByCaseId(caseId);
        courtCase.setLitiParts(litiParts);
        return courtCase;
    }

    public File createLawsDoc(HttpServletResponse response,Long caseId, LawsDocType lawsDocType) {
        if(!lawsDocType.getOnly()){
            List<LitiPart> litiParts = iLitiPartRep.findByCaseId(caseId);
            Iterator<LitiPart> it = litiParts.iterator();
            while (it.hasNext()) {
                LitiPart litiPart = it.next();
                createLawsDoc(response,litiPart.getCaseId(),litiPart.getLitiPartId(), lawsDocType);
            }
            return null;
        }else {
            return createLawsDoc(response,getCaseByid(caseId),new LitiPart(),lawsDocType);
        }
    }

    public File createLawsDoc(HttpServletResponse response,Long caseId, Long  litiPartId, LawsDocType lawsDocType) {
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
            String filename = courtCase.getCaseNum() + "-" + litiPart.getName()+ "-" + lawsDocType.getName() + ".xml";
            Utils.renderWord(response,baos.toByteArray(),filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
