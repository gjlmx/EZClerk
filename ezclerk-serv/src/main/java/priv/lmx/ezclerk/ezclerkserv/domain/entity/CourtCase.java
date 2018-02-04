package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import priv.lmx.ezclerk.ezclerkserv.core.Constant;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @lmx
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Court_Case")
public class CourtCase {
    @Id
    @GeneratedValue
    private Long caseId;
    private String anyou;
    private String caseNum;
    private String caseName;
    private String caseFalName;
    private String indexxx = "1";
    private Long userId = 1L;
    private Integer caseStatus= Constant.CASE_STATUS_FASU;
    private String niandu;
    @OneToMany
    private List<LitiPart> litiParts;
    private String type;
    private Date liandate;
    private Date jieandate;
    private Date kaitingdate;
    private Boolean simp = Boolean.TRUE;//简易程序
    private Boolean havePreservation = Boolean.FALSE;//是否有保全
    private String  casePreservationInfoDetil;
    private String  casePreservationcaiding;
}
