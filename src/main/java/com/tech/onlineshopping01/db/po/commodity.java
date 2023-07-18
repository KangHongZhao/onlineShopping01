package com.tech.onlineshopping01.db.po;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class commodity {
    private Integer commodityId;

    private String commodityName;

    private Float price;

    private String description;

    private Integer totalstock;

    private Integer availablestock;

    private Integer sellerid;

    public Integer getCommodityid() {
        return commodityId;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityId = commodityid;
    }

    public String getCommodityname() {
        return commodityName;
    }

    public void setCommodityname(String commodityname) {
        this.commodityName = commodityname == null ? null : commodityname.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getTotalstock() {
        return totalstock;
    }

    public void setTotalstock(Integer totalstock) {
        this.totalstock = totalstock;
    }

    public Integer getAvailablestock() {
        return availablestock;
    }

    public void setAvailablestock(Integer availablestock) {
        this.availablestock = availablestock;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }
}