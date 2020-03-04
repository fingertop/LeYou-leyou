package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    price_cannot_be_null(400,"价格不能为空"),
    category_not_found(404,"商品分类未查到"),
    brand_not_found(404,"品牌不存在" ),
    brand_save_failed(500,"新增品牌失败" ),
    category_brand_save_failed(500,"新增中间表失败" ),
    upload_failed(500,"文件上传失败"),
    file_type_invalid(500,"文件类型不匹配"),
    spec_group_not_found(404,"商品规格组未发现"),
    param_not_found(404,"商品规格参数未发现"),

    goods_not_found(404,"不存在"),
    ;

    private int status;
    private String msg;

}
