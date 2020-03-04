package com.leyou.item.web;


import com.leyou.common.expojo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GoodsController {

    @Autowired
    GoodsService goodsService;


    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> findGoods(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "key",required = false) String key){



        return ResponseEntity.ok(goodsService.findGoods(page,rows,saleable,key));
    }



}
