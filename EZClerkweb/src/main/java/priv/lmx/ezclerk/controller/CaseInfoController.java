package priv.lmx.ezclerk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.lmx.ezclerk.service.IServices;

/**
 * @lmx
 */
@Controller
@RequestMapping("/caseInfo")
public class CaseInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CaseInfoController.class);


    @RequestMapping("/getAllCase")
    public String getAllCase() {
        logger.info("show all case");
        return "caseList";
    }


}
