package priv.lmx.ezclerk.ezclerkserv.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private String pwd;
    private String faguanName;
    private String sjyName;
}
