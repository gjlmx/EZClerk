package priv.lmx.ezclerk.ezclerkserv.core;

import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiRepre;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @lmx
 */
public class Utils {

    public static String litiRepreFormate(LitiRepre litiRepre){
            litiRepre.setDescription(litiRepre.getName() + ","+litiRepre.getDanwei()+litiRepre.getType());
            return litiRepre.getDescription();
    }

    public static String litiPartFormate(LitiPart litiPart){
      return "";
    }


    /**
     *
     * @param courtCase
     * @param hashMap
     */
    public static void caseinfoFormat(CourtCase courtCase, HashMap<String, Object> hashMap){


    }

    /**
     * 文件下载
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
     * @param response
     * @param fileName
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            // 中文文件名支持
            String encodedfileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
        }
    }
}
