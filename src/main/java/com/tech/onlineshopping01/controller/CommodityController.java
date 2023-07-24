package com.tech.onlineshopping01.controller;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.tech.onlineshopping01.db.dao.CommodityDao;
import com.tech.onlineshopping01.db.dao.OnlineShopUserDao;
import com.tech.onlineshopping01.db.po.commodity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {
    @Resource
    CommodityDao dao;

    @RequestMapping ("/addItem")
    public String addItem() {
        return "add_commodity";
    }
    @RequestMapping("/addItemAction")
    public String addItemAction(
            @RequestParam("commodityId") int commodityId,
            @RequestParam("commodityName") String commodityName,
            @RequestParam("price") Float price,
            @RequestParam("description") String description,
            @RequestParam("totalStock") int totalStock,
            @RequestParam("availableStock") int availableStock,
            @RequestParam("sellerId") int sellerId,
            Map<String, Object> resultMap

    ) {
        commodity onlinecommodity = commodity.builder()
                .commodityId(commodityId)
                .sellerid(sellerId)
                .availablestock(availableStock)
                .commodityName(commodityName)
                .totalstock(totalStock)
                .price(price)
                .description(description)
                .build();
        dao.insertCommodity(onlinecommodity);
        resultMap.put("Item", onlinecommodity);
        return "add_commodity_success";


    }
    @RequestMapping("/")
    public String DefaultItems(Map<String, Object> resultMap) {
        List<commodity> listOfItems = dao.listCommoditiesByUserId(1);
        resultMap.put("itemList", listOfItems);
        listOfItems.get(0).getCommodityname();
        return "list_items";
    }
    @RequestMapping("/listItems/{userId}")
    public String listItems(@PathVariable("userId") int userId, Map<String, Object> resultMap) {
        List<commodity> listOfItems = dao.listCommoditiesByUserId(userId);
        resultMap.put("itemList", listOfItems);
        listOfItems.get(0).getCommodityname();
        return "list_items";
    }

    @RequestMapping("/item/{commodityId}")
    public String getItem(@PathVariable("commodityId") int commodityId, Map<String, Object> resultMap) {
        commodity item = dao.getCommodity(commodityId);
        resultMap.put("commodity", item);
        return "item_detail";
    }

}
