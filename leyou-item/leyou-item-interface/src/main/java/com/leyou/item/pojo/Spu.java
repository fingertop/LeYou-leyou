package com.leyou.item.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name="tb_spu")
@Data
public class Spu {

    @Id
    /**
     * 是否使用 JDBC 方式获取主键，优先级最高，设置为 true 后，不对其他配置校验
     */
    @KeySql(useGeneratedKeys = true)//
    private  Long id;
    private  Long brandId;
    private  Long cid1;
    private  Long cid2;
    private  Long cid3;
    private  String title;
    private  String subTitle;
    private  Boolean saleable;

    @JsonIgnore//页面忽略该字段
    private  Boolean valid;//是否删除,逻辑删除用

    private  Date createTime;

    @JsonIgnore//页面忽略该字段
    private  Date lastUpdateTime;

    @Transient
    private  String cname;
    @Transient
    private  String bname;



}
