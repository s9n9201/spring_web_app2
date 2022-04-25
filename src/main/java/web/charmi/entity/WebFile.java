package web.charmi.entity;

public class WebFile {
    private Integer FRecId;
    private String FMoudle;
    private String FFromUUID;
    private String FFileName;
    private String FUUIDName;
    private Long FSize;
    private Integer FSort;

    public Integer getFRecId() {
        return FRecId;
    }

    public void setFRecId(Integer FRecId) {
        this.FRecId=FRecId;
    }

    public String getFMoudle() {
        return FMoudle;
    }

    public void setFMoudle(String FMoudle) {
        this.FMoudle=FMoudle;
    }

    public String getFFromUUID() {
        return FFromUUID;
    }

    public void setFFromUUID(String FFromUUID) {
        this.FFromUUID=FFromUUID;
    }

    public String getFFileName() {
        return FFileName;
    }

    public void setFFileName(String FFileName) {
        this.FFileName=FFileName;
    }

    public String getFUUIDName() {
        return FUUIDName;
    }

    public void setFUUIDName(String FUUIDName) {
        this.FUUIDName=FUUIDName;
    }

    public Long getFSize() {
        return FSize;
    }

    public void setFSize(Long FSize) {
        this.FSize=FSize;
    }

    public Integer getFSort() {
        return FSort;
    }

    public void setFSort(Integer FSort) {
        this.FSort=FSort;
    }
}
