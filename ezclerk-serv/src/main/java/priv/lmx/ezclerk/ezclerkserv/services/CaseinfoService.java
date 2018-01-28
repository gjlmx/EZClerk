package priv.lmx.ezclerk.ezclerkserv.services;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.lmx.ezclerk.ezclerkserv.domain.IEzclerkRep;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CaseinfoService {

    @Autowired
    Configuration configuration;

    @Autowired
    IEzclerkRep iEzclerkRep;

    public File createLawsDoc(CourtCase courtCase, LawsDocType lawsDocType){
        try {
            Template temple=configuration.getTemplate("template.ftl");
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> docvalMap = new HashMap<>();
            temple.process(docvalMap, stringWriter);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return  null;
    }

}
