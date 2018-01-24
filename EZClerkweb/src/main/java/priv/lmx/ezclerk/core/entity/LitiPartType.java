package priv.lmx.ezclerk.core.entity;

/**
 * 诉讼参与人类型
 */
public enum LitiPartType {
    PROSECUTOR(1L,"原告","原告"),
    DEFENDANT(2L,"被告","被告"),
    THIRD_PARTY(3L,"第三人","第三人"),
    CLERKS(4L,"书记员",""),
    WITNESS(5L,"证人",""),
    EXPERT_WITNESSES(6L,"鉴定人",""),
    EXAMINERS(7L,"勘验人","")

    ;

    private Long id;
    private String name;
    private String description;

    LitiPartType(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
