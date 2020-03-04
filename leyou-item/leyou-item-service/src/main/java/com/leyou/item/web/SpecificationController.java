package com.leyou.item.web;

import com.leyou.item.pojo.Specification;
import com.leyou.item.pojo.SpecificationGroupParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {


    @Autowired
    SpecificationService specificationService;


    /**
     * 据cid获取数据
     * @param cid
     * @return
     */
//    @GetMapping("groups/{cid}")
//    public ResponseEntity<List<SpecGroup>> specGroupByCid(@PathVariable("cid") Long cid){
//        return  ResponseEntity.ok(specificationService.specGroupByCid( cid));
//    }

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<Specification>> specGroupByCid(@PathVariable("cid") Long cid){
        return  ResponseEntity.ok(specificationService.specGroupByCid( cid));
    }


//    @GetMapping("params")
//    public ResponseEntity<List<SpecParam>> ParamsByGid(@RequestParam("gid") Long gid){
//        return  ResponseEntity.ok(specificationService.ParamsByGid( gid));
//    }

    @GetMapping("params")
    public ResponseEntity<List<SpecificationGroupParam>> ParamsByGid(@RequestParam("gname") String gname){
        return  ResponseEntity.ok(specificationService.ParamsByGid( gname));
    }
}
