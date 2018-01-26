package priv.lmx.ezclerk.ezclerkserv.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @lmx
 * 代理人信息
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class LitiRepre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String danwei;
    private String phoneNum;
    private String addr;
    private String description;

}
