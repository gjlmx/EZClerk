package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @lmx
 * 案件处理日志
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class CaseLog {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String logDeteil;
}
