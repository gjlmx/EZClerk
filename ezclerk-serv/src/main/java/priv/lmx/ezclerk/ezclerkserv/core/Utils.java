package priv.lmx.ezclerk.ezclerkserv.core;

import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LawsDocType;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiRepre;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @lmx
 */
public class Utils {

    public static String litiRepreFormate(LitiRepre litiRepre) {
        litiRepre.setDescription(litiRepre.getName() + "," + litiRepre.getDanwei() + litiRepre.getType());
        return litiRepre.getDescription();
    }

    public static String litiPartFormate(LitiPart litiPart) {
        return "";
    }


    /**
     * @param courtCase
     */
    public static HashMap<String, Object> caseinfoFormat(CourtCase courtCase, LitiPart litiPart) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("anhao", courtCase.getCaseNum());
        map.put("anyou", courtCase.getAnyou());
        map.put("yingsuanming", courtCase.getCaseFalName());
        map.put("fasuriqi", dateFarmate(new Date()));
        map.put("kaitingshijian", timeFarmate(courtCase.getKaitingdate()));
        if(courtCase.getHavePreservation()){

        }
//        ${casePreservationInfoDetil} :  保全裁定保全详情
//        ${baoquancaidinganming} :  保全裁定案名
//        ${casePreservationcaiding} :  裁定主文
        if (litiPart != null) {
            map.put("tarName", litiPart.getName());
            map.put("dsrdizhi", litiPart.getAddr());
            String fasuwenshu = "";
            if (litiPart.getLawsPartType().equals("法人")&&courtCase.getHavePreservation()){
                fasuwenshu = "民事诉状、当事人送达地址确认书、权利义务告知书、授权委托书、应诉通知书、开庭传票、民事裁定书、法人代表身份证明";//法人有保全
            }else if(litiPart.getLawsPartType().equals("法人")&&!courtCase.getHavePreservation()){
                fasuwenshu = "民事诉状、当事人送达地址确认书、权利义务告知书、授权委托书、应诉通知书、开庭传票、法人代表身份证明";//法人无保全
            }else if(litiPart.getLawsPartType().equals("公民")&&courtCase.getHavePreservation()){
                fasuwenshu = "民事诉状、当事人送达地址确认书、权利义务告知书、授权委托书、应诉通知书、开庭传票、民事裁定书";//公民有保全
            }else if(litiPart.getLawsPartType().equals("公民")&&!courtCase.getHavePreservation()){
                fasuwenshu = "民事诉状、当事人送达地址确认书、权利义务告知书、授权委托书、应诉通知书、开庭传票";//公民无保全
            }
            map.put("fasuwenshu",fasuwenshu);
        }
        return map;
    }

    public static String getfileName(CourtCase courtCase, LitiPart litiPart, LawsDocType lawsDocType){
        if (litiPart == null){
            return courtCase.getCaseNum() + "-" + lawsDocType.getName() + ".xml";
        }
       return courtCase.getCaseNum() + "-" + litiPart.getName()+ "-" + lawsDocType.getName() + ".xml";
    }


    private static String timeFarmate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return simpleDateFormat.format(date);
    }

    private static String dateFarmate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(date);
    }


    public static void caseInfoFormat(CourtCase courtCase) {
        String caseFalName = "";
        String ygs = "";
        String bgs = "";
        String dsr = "";
        List<LitiPart> litiParts = courtCase.getLitiParts();
        if (litiParts == null) {
            return;
        }
        Iterator<LitiPart> it = litiParts.iterator();
        while (it.hasNext()) {
            LitiPart litiPart = it.next();
            if (litiPart.getType().equals("原告")) {
                ygs = ygs + "原告" + litiPart.getName() + "、";
            } else if (litiPart.getType().equals("被告")) {
                bgs = bgs + "被告" + litiPart.getName() + "、";
            } else if (litiPart.getType().equals("第三人")) {
                dsr = dsr + "第三人" + litiPart.getName() + "、";
            }
        }
        ygs = ygs.length() > 0 ? ygs.substring(0, ygs.length() - 1) : ygs;
        bgs = bgs + dsr;
        bgs = bgs.length() > 0 ? bgs.substring(0, bgs.length() - 1) : bgs;
        caseFalName = ygs + "诉" + bgs + courtCase.getAnyou();
        courtCase.setCaseFalName(caseFalName);
    }

    /**
     * 文件下载
     *
     * @param response
     * @param bytes
     * @param filename
     */
    public static void renderWord(HttpServletResponse response, final byte[] bytes, final String filename) {
        /*  initResponseHeader(response, PDF_TYPE);  */
        setFileDownloadHeader(response, filename);
        if (null != bytes) {
            try {
                response.getOutputStream().write(bytes);
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * 文件命名
     *
     * @param response
     * @param fileName
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
            response.setHeader("X-Suggested-Filename", "\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
        }
    }
}
