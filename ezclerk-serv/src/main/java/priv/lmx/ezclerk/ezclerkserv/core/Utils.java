package priv.lmx.ezclerk.ezclerkserv.core;

import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiRepre;

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

}
