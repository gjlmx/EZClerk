package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
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
    private Long caseId;
    private String name;
    private String type;//公民法人其他组织
    private String lawsPartType;//原告、被告、第三人
    private String addr;
    private String phone;
    @OneToMany
    private List<LitiRepre> litiRepres = null;//代理人
    private String litiRepresInfo;//格式化代理人信息

    private Boolean isPson = Boolean.TRUE;

    private Integer age = isPson ? 0 : null;//年龄
    private Date csrq;//出生日期
    private String gmsfhm = isPson ? "" : null;//公民身份证号码
    private String minzu;//民族
    private String sex;


    private String tyshxydm = isPson ? null : "";//信用代码
    private String fddbr = isPson ? null : "";//法定代表人

    private String description;//描述


}
