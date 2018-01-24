package priv.lmx.ezclerk.core.entity;

import java.util.Set;

/**
 * @lmx 诉讼参与人
 */
public class LitigationParticipants {
    private String name;

    private Set<ContactInfo> contactInfos;

    private LitiPartType litiPartType;//诉讼参与人类型

    private LawsuitPartiesType lawsuitPartiesType;//当事人类型


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(Set<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public LitiPartType getLitiPartType() {
        return litiPartType;
    }

    public void setLitiPartType(LitiPartType litiPartType) {
        this.litiPartType = litiPartType;
    }

    public LawsuitPartiesType getLawsuitPartiesType() {
        return lawsuitPartiesType;
    }

    public void setLawsuitPartiesType(LawsuitPartiesType lawsuitPartiesType) {
        this.lawsuitPartiesType = lawsuitPartiesType;
    }
}
