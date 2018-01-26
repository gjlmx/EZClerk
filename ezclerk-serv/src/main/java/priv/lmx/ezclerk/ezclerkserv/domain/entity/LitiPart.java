package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @lmx
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class LitiPart {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String lawsPartType;
    private String dlrInfo;
    private String addr;
    @OneToMany
    private List<LitiPart> litiParts = null;//代理人

    private Boolean isPson = Boolean.TRUE;

    private Integer age = isPson ? 0 : null;
    private String csrq = isPson ? "" : null;
    private String hujidi = isPson ? "" : null;
    private String gmsfhm = isPson ? "" : null;


    private String tyshxydm = isPson ? null : "";//信用代码
    private String fddbr = isPson ? null : "";//法定代表人


}
