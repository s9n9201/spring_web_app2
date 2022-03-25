package web.charmi.entity;

public class Role {

    private Integer RRecId;
    private enumRole name;

    public Role(enumRole name) {
        this.name=name;
    }

    public Integer getRRecId() {
        return RRecId;
    }

    public void setRRecId(Integer RRecId) {
        this.RRecId=RRecId;
    }

    public enumRole getName() {
        return name;
    }

    public void setName(enumRole name) {
        this.name=name;
    }
}
