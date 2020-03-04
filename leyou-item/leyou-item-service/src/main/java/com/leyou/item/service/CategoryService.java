package com.leyou.item.service;


import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByPid(Long pid) {
        //设置查询条件  非空属性作为条件
        Category category = new Category();
        category.setParentId(pid);

        //执行查询
        List<Category> list = categoryMapper.select(category);

        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.category_not_found);
        }
        return list;
    }


    public List<Category> queryByIds(List<Long> ids){
        List<Category> list = categoryMapper.selectByIdList(ids);

        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.category_not_found);
        }
        return list;
    }
}
