package priv.lmx.ezclerk.core.entity;

public enum LawsuitPartiesType {
    CITIZEN(1L, "公民"), LEGAL_PERSON(2L, "法人"),
    ORGANIZATION(3L, "其他组织"), LEGAL_REPRESENTATIVES(4L, "法定代表人"),
    LITIGATION_REPRESENTATIVES(5L, "委托诉讼代理人");
    private Long id;
    private String type;

    LawsuitPartiesType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
