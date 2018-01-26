package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Long id;
    private String caseName;
    private String caseFalName;
    private String niandu;
    @OneToMany
    private Set<LitiPart> litiParts;
    private String type;
    private Date liandate;
    private Date jieandate;
    private Boolean simp = Boolean.TRUE;//简易程序
    @OneToMany
    private List<CaseLog> caseLogs;


}
