package priv.lmx.ezclerk.ezclerkserv.domain.entity;

public enum LawsDocType {
    CHUANPIAO(1,"传票","chuanpiao.ftl",false),YINGSUTONGZHISHU(2,"应诉通知书","",false);

    private Integer id;
    private String name;
    private String temlPath;
    private Boolean only;
    LawsDocType(Integer id, String name, String temlPath, Boolean only){
        this.only = only;
        this.id = id;
        this.name = name;
        this.temlPath = temlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemlPath() {
        return temlPath;
    }

    public void setTemlPath(String temlPath) {
        this.temlPath = temlPath;
    }

    public Boolean getOnly() {
        return only;
    }

    public void setOnly(Boolean only) {
        this.only = only;
    }
}
