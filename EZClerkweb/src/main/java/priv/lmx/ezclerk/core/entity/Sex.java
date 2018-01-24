package priv.lmx.ezclerk.core.entity;

public enum Sex {
    nan("男"),nv("女");
    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    String get(){
        return this.sex;
    }

}
