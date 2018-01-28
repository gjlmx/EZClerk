package priv.lmx.ezclerk.ezclerkserv.domain.entity;

public enum LawsDocType {
    chuanpiao(1,"传票",""),yingsutongzhishu(2,"应诉通知书","");

    private Integer id;
    private String name;
    private String temlPath;
    LawsDocType(Integer id, String name, String temlPath){
        this.id = id;
        this.name = name;
        this.temlPath = temlPath;
    }

}
