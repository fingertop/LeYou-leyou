package com.leyou.item.pojo;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_brand")
@Data
public class Brand {

    @Id
    /**
     * 是否使用 JDBC 方式获取主键，优先级最高，设置为 true 后，不对其他配置校验
     */
    @KeySql(useGeneratedKeys = true)//
    private  Long id;
    private  String name;
    private  String image;
    private  Character letter;


}
