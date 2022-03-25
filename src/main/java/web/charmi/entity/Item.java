package web.charmi.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.util.Date;

public class Item {

    private ItemType itemType;

    @Positive(message="資料異常，請重新操作！", groups=Update.class)
    private Integer IRecId;

    @Positive(message="請選擇物品種類！")
    private Integer ITRecId;

    @NotBlank(message="請輸入物品名稱！")
    private String IName;

    @NotBlank(message="請輸入批貨店家！")
    private String ISource;

    @NotBlank(message="請輸入製造地！")
    private String IMadeIn;

    private Float IAmount;

    private Float ICost;

    private Float IPrice;

    private Float ITotal;

    private Integer IRecOrg;

    private Date IRecDate;

    private Integer IUpdateOrg;

    private Date IUpdateDate;

    private Integer IisDelete;

    private Integer IDeleteOrg;

    private Date IDeleteDate;

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType=itemType;
    }

    public Integer getIRecId() {
        return IRecId;
    }

    public void setIRecId(Integer IRecId) {
        this.IRecId=IRecId;
    }

    public Integer getITRecId() {
        return ITRecId;
    }

    public void setITRecId(Integer ITRecId) {
        this.ITRecId=ITRecId;
    }

    public String getIName() {
        return IName;
    }

    public void setIName(String IName) {
        this.IName=IName;
    }

    public String getISource() {
        return ISource;
    }

    public void setISource(String ISource) {
        this.ISource=ISource;
    }

    public String getIMadeIn() {
        return IMadeIn;
    }

    public void setIMadeIn(String IMadeIn) {
        this.IMadeIn=IMadeIn;
    }

    public Float getIAmount() {
        return IAmount;
    }

    public void setIAmount(Float IAmount) {
        this.IAmount=IAmount;
    }

    public Float getICost() {
        return ICost;
    }

    public void setICost(Float ICost) {
        this.ICost=ICost;
    }

    public Float getIPrice() {
        return IPrice;
    }

    public void setIPrice(Float IPrice) {
        this.IPrice=IPrice;
    }

    public Float getITotal() {
        return ITotal;
    }

    public void setITotal(Float ITotal) {
        this.ITotal=ITotal;
    }

    public Integer getIRecOrg() {
        return IRecOrg;
    }

    public void setIRecOrg(Integer IRecOrg) {
        this.IRecOrg=IRecOrg;
    }

    public Date getIRecDate() {
        return IRecDate;
    }

    public void setIRecDate(Date IRecDate) {
        this.IRecDate=IRecDate;
    }

    public Integer getIUpdateOrg() {
        return IUpdateOrg;
    }

    public void setIUpdateOrg(Integer IUpdateOrg) {
        this.IUpdateOrg=IUpdateOrg;
    }

    public Date getIUpdateDate() {
        return IUpdateDate;
    }

    public void setIUpdateDate(Date IUpdateDate) {
        this.IUpdateDate=IUpdateDate;
    }

    public Integer getIisDelete() {
        return IisDelete;
    }

    public void setIisDelete(Integer iisDelete) {
        IisDelete=iisDelete;
    }

    public Integer getIDeleteOrg() {
        return IDeleteOrg;
    }

    public void setIDeleteOrg(Integer IDeleteOrg) {
        this.IDeleteOrg=IDeleteOrg;
    }

    public Date getIDeleteDate() {
        return IDeleteDate;
    }

    public void setIDeleteDate(Date IDeleteDate) {
        this.IDeleteDate=IDeleteDate;
    }

    public interface Insert extends Default {

    }

    public interface Update extends Default {

    }

}
