package priv.lmx.ezclerk.core.entity;

import java.util.Date;

public class CivilLitiPart extends LitigationParticipants {

    private Integer age;
    private Date bornDate;
    private String idNum;
    private Sex sex = Sex.nan;

    public ContactInfo getServiceAddr() {
        return null;
    }

    public String getName() {
        return null;
    }

    public LitiPartType getType() {
        return null;
    }

    public Boolean insContactInfo(ContactInfo contactInfo) {
        return null;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
