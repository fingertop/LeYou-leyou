/*
package com.leyou.item.web;


import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.item.pojo.Item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {


    @Autowired
    ItemService itemService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Item> saveItem(Item item){

        if (item.getPrice()==null) {
            //400新增失败  //推荐使用aop处理该消息
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

            throw  new LeyouException(ExceptionEnums.price_cannot_be_null);
        }

        //201新增成功
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(item));
    }
}
*/
