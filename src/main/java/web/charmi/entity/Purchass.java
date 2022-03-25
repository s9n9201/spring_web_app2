package web.charmi.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

public class Purchass {

    private List<PurchassDetail> purchassDetailList;

    @Positive(message="資料異常，請重新操作！", groups=Update.class)
    private Integer PRecId;

    @NotBlank(message="請輸入店家名稱！")
    private String PStore;

    @Positive(message="請輸入訂貨金額！")
    private Float PCost;

    private Integer PItemRow;

    private Integer PArrived;
    private Date PBuyDate;
    private Date PArriveDate;
    private Integer PRecOrg;
    private Date PRecDate;
    private Integer PUpdateOrg;
    private Date PUpdateDate;
    private Integer PisDelete;
    private Integer PDeleteOrg;
    private Date PDeleteDate;

    public List<PurchassDetail> getPurchassDetailList() {
        return purchassDetailList;
    }

    public void setPurchassDetailList(List<PurchassDetail> purchassDetailList) {
        this.purchassDetailList=purchassDetailList;
    }

    public Integer getPRecId() {
        return PRecId;
    }

    public void setPRecId(Integer PRecId) {
        this.PRecId=PRecId;
    }

    public String getPStore() {
        return PStore;
    }

    public void setPStore(String PStore) {
        this.PStore=PStore;
    }

    public Float getPCost() {
        return PCost;
    }

    public void setPCost(Float PCost) {
        this.PCost=PCost;
    }

    public Integer getPItemRow() {
        return PItemRow;
    }

    public void setPItemRow(Integer PItemRow) {
        this.PItemRow=PItemRow;
    }

    public Integer getPArrived() {
        return PArrived;
    }

    public void setPArrived(Integer PArrived) {
        this.PArrived=PArrived;
    }

    public Date getPBuyDate() {
        return PBuyDate;
    }

    public void setPBuyDate(Date PBuyDate) {
        this.PBuyDate=PBuyDate;
    }

    public Date getPArriveDate() {
        return PArriveDate;
    }

    public void setPArriveDate(Date PArriveDate) {
        this.PArriveDate=PArriveDate;
    }

    public Integer getPRecOrg() {
        return PRecOrg;
    }

    public void setPRecOrg(Integer PRecOrg) {
        this.PRecOrg=PRecOrg;
    }

    public Date getPRecDate() {
        return PRecDate;
    }

    public void setPRecDate(Date PRecDate) {
        this.PRecDate=PRecDate;
    }

    public Integer getPUpdateOrg() {
        return PUpdateOrg;
    }

    public void setPUpdateOrg(Integer PUpdateOrg) {
        this.PUpdateOrg=PUpdateOrg;
    }

    public Date getPUpdateDate() {
        return PUpdateDate;
    }

    public void setPUpdateDate(Date PUpdateDate) {
        this.PUpdateDate=PUpdateDate;
    }

    public Integer getPisDelete() {
        return PisDelete;
    }

    public void setPisDelete(Integer pisDelete) {
        PisDelete=pisDelete;
    }

    public Integer getPDeleteOrg() {
        return PDeleteOrg;
    }

    public void setPDeleteOrg(Integer PDeleteOrg) {
        this.PDeleteOrg=PDeleteOrg;
    }

    public Date getPDeleteDate() {
        return PDeleteDate;
    }

    public void setPDeleteDate(Date PDeleteDate) {
        this.PDeleteDate=PDeleteDate;
    }

    public interface Insert extends Default {

    }

    public interface Update extends Default {

    }
}
