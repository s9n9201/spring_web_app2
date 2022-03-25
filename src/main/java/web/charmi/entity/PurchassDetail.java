package web.charmi.entity;

import java.util.Date;

public class PurchassDetail {

    private Item item;
    private Integer PDRecId;
    private Integer PDPRecId;
    private Integer PDIRecId;
    private Float PDAmount;
    private Float PDCost;
    private Float PDTotal;
    private Integer PDRecOrg;
    private Date PDRecDate;
    private Integer PDUpdateOrg;
    private Date PDUpdateDate01;
    private Integer PDisDelete;
    private Integer PDDeleteOrg;
    private Date PDDeleteDate01;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item=item;
    }

    public Integer getPDRecId() {
        return PDRecId;
    }

    public void setPDRecId(Integer PDRecId) {
        this.PDRecId=PDRecId;
    }

    public Integer getPDPRecId() {
        return PDPRecId;
    }

    public void setPDPRecId(Integer PDPRecId) {
        this.PDPRecId=PDPRecId;
    }

    public Integer getPDIRecId() {
        return PDIRecId;
    }

    public void setPDIRecId(Integer PDIRecId) {
        this.PDIRecId=PDIRecId;
    }

    public Float getPDAmount() {
        return PDAmount;
    }

    public void setPDAmount(Float PDAmount) {
        this.PDAmount=PDAmount;
    }

    public Float getPDCost() {
        return PDCost;
    }

    public void setPDCost(Float PDCost) {
        this.PDCost=PDCost;
    }

    public Float getPDTotal() {
        return PDTotal;
    }

    public void setPDTotal(Float PDTotal) {
        this.PDTotal=PDTotal;
    }

    public Integer getPDRecOrg() {
        return PDRecOrg;
    }

    public void setPDRecOrg(Integer PDRecOrg) {
        this.PDRecOrg=PDRecOrg;
    }

    public Date getPDRecDate() {
        return PDRecDate;
    }

    public void setPDRecDate(Date PDRecDate) {
        this.PDRecDate=PDRecDate;
    }

    public Integer getPDUpdateOrg() {
        return PDUpdateOrg;
    }

    public void setPDUpdateOrg(Integer PDUpdateOrg) {
        this.PDUpdateOrg=PDUpdateOrg;
    }

    public Date getPDUpdateDate01() {
        return PDUpdateDate01;
    }

    public void setPDUpdateDate01(Date PDUpdateDate01) {
        this.PDUpdateDate01=PDUpdateDate01;
    }

    public Integer getPDisDelete() {
        return PDisDelete;
    }

    public void setPDisDelete(Integer PDisDelete) {
        this.PDisDelete=PDisDelete;
    }

    public Integer getPDDeleteOrg() {
        return PDDeleteOrg;
    }

    public void setPDDeleteOrg(Integer PDDeleteOrg) {
        this.PDDeleteOrg=PDDeleteOrg;
    }

    public Date getPDDeleteDate01() {
        return PDDeleteDate01;
    }

    public void setPDDeleteDate01(Date PDDeleteDate01) {
        this.PDDeleteDate01=PDDeleteDate01;
    }
}
