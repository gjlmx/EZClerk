package priv.lmx.ezclerk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.lmx.ezclerk.service.IServices;
import priv.lmx.ezclerk.service.impl.ServicesImpl;

@Controller
@RequestMapping("/a")
public class TestDb {
    private static final Logger logger = LoggerFactory.getLogger(TestDb.class);

    @Autowired
    private  IServices iServices;

    @RequestMapping("/index")
    public String index() {
        iServices.test();
        //输出日志文件
        logger.error("controoler--------------------");
        return "index";
    }
}
