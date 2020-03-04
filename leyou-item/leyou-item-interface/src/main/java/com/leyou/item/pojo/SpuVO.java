package com.leyou.item.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
public class SpuVO {

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
    private  String cname;
    private  String bname;
    private  Boolean saleable;
    private  Date createTime;




}
