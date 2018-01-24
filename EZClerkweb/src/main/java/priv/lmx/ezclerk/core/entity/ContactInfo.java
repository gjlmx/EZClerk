package priv.lmx.ezclerk.core.entity;

public class ContactInfo {
    private String phoneNum;
    private String telephoneNum;
    private String addr;
    private Boolean isDomicil = Boolean.TRUE;//户籍地或者登记地
    private String contact;//联系人名称
    private Boolean isService;//是否是送达地址

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Boolean getDomicil() {
        return isDomicil;
    }

    public void setDomicil(Boolean domicil) {
        isDomicil = domicil;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getService() {
        return isService;
    }

    public void setService(Boolean service) {
        isService = service;
    }
}

