package com.leyou.item.pojo;


import lombok.Data;

import java.util.List;

@Data

public class SpecificationGroup {

    private String group;
    private List<SpecificationGroupParam> params;


}
