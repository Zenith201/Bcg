package com.boston.bcg.controller;

import com.boston.bcg.helper.ItemHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    ItemHelper helper=ItemHelper.getInstance();

    @GetMapping("/items/preview/{category}")
    public String getSampleItems(@PathVariable("category") String category){
        return helper.getCategorySample(category);
    }

    @GetMapping("/items/all/{category}/{set}")
    public String getAllItems(@PathVariable("category") String category, @PathVariable("set")String set){
        return helper.getCategorySample(category);
    }

    @GetMapping("/search/{query}/{set}")
    public String searchItems(@PathVariable("query") String query, @PathVariable("set")String set){
        return helper.searchItems(query,Integer.parseInt(set));
    }

    @GetMapping("/items/{id}")
    public String searchItems(@PathVariable("id")String id){
        return helper.getItemById(id);
    }


    @GetMapping("/test")
    public String test(){
        return "everything is OK :)";
    }



}
