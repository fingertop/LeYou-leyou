package com.leyou.item.mapper;

import com.leyou.item.pojo.Specification;
import com.leyou.item.pojo.SpecificationGroup;
import com.leyou.item.pojo.SpecificationGroupParam;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecificationMapper extends Mapper<Specification> {


    @Results({
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "specifications",column = "specifications")
    })
    @Select("SELECT category_id,specifications FROM tb_specification WHERE category_id = #{cid}")
    public  List<Specification> findAll( Long cid);

    @Results({
            @Result(property = "group",column = "group"),
            @Result(property = "params",column = "params",
                    many = @Many(select = "com.leyou.item.mapper.SpecificationMapper.paramsList"))
    })
    @Select("SELECT name,params FROM  ")
    public List<SpecificationGroup> specificationsList();
    @Results({
            @Result(property = "k",column = "k"),
            @Result(property = "searchable",column = "searchable"),
            @Result(property = "global",column = "global"),
            @Result(property = "options",column = "options"),
            @Result(property = "numerical",column = "numerical"),
            @Result(property = "unit",column = "unit")
    })
    @Select("SELECT k,searchable,global,options,numerical,unit FROM")
    public List<SpecificationGroupParam> paramsList();


}
