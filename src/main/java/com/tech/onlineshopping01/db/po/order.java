package com.tech.onlineshopping01.db.po;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Builder
@AllArgsConstructor
public class order {
    private Integer orderid;

    private Integer commodityid;

    private String ordernum;

    private Integer orderstatus;

    private Integer orderamount;

    private Integer userid;

    private Date createtime;

    private Date paytime;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCommodityid() {
        return commodityid;
    }


    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(Integer orderamount) {
        this.orderamount = orderamount;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }
}