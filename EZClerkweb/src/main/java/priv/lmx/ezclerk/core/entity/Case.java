package priv.lmx.ezclerk.core.entity;

import java.util.Date;

/**
 * @lmx
 */
public class Case {
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    private Long caseId;
    private String caseName;

}
