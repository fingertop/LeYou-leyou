package com.leyou.item.service;


import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.mapper.SpecificationParamMapper;
import com.leyou.item.pojo.Specification;
import com.leyou.item.pojo.SpecificationGroupParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {


    @Autowired
    SpecGroupMapper specGroupMapper;

    @Autowired
    SpecParamMapper specParamMapper;


    @Autowired
    SpecificationMapper specificationMapper;


    @Autowired
      SpecificationParamMapper specificationParamMapper;


    public List<Specification> specGroupByCid(Long cid) {
   /* public List<SpecGroup> specGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
D:\java\mirsered\gmall\day12\gmall-parent\gmall-manage-service\src\main\java\com\gec\gmall\manage\mapperD:\java\mirsered\gmall\day12\gmall-parent\gmall-manage-service\src\main\java\com\gec\gmall\manage\mapper
        List<SpecGroup> list = specGroupMapper.select(specGroup);

        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.spec_group_not_found);
        }
        return  list;*/


        List<Specification> list = specificationMapper.findAll(cid);
        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.spec_group_not_found);
        }
        return list;

    }

    public List<SpecificationGroupParam> ParamsByGid(String gname) {
       /* public List<SpecParam> ParamsByGid(Long gid) {

        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);

        List<SpecParam> list = specParamMapper.select(specParam);

        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.param_not_found);
        }
        return  list;*/

        SpecificationGroupParam sgp =  new SpecificationGroupParam();
        List<SpecificationGroupParam> list = specificationParamMapper.select(sgp);
        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.param_not_found);
        }
        return  list;
    }

}
