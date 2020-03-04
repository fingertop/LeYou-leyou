package com.leyou.item.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spec_param")
public class SpecParam {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private  Long cid;
    private  Long groupId;

    //生成sql时 该字段名为被指定的  sql字段名为关键字时推荐使用
    @Column(name = "`numeric`")
    private  Boolean numeric;
    private  Boolean generic;
    private  Boolean searching;
    private  String unit;
    private  String segments;

}
