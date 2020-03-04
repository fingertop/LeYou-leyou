package com.leyou.item.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.common.expojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service

public class BrandService {


    //单表操作 通用mapper基本可以解决
    @Autowired
    private BrandMapper brandMapper;


    public PageResult<Brand> findBrand(Integer page, Integer rows,
                                       String sortBy, Boolean desc, String key) {

        //开始分页   分页助手在mybatis 执行sql语句前拦截 拼接sql语句
        PageHelper.startPage(page,rows);


             //WHERE 'name' LIKE '%X%' OR letter == 'X'
        //过滤
        Example brand = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            //设置过滤条件
            brand.createCriteria().orLike("name","%"+key+"%")
                    .orEqualTo("letter",key.toUpperCase());
        }

        //排序    //ORDER BY id DESC
        if (StringUtils.isNotBlank(sortBy)) {
            brand.setOrderByClause(sortBy+(desc ?" DESC":" ASC"));
        }

        //查询 //Page<E> extends ArrayList<E>  自己强转不够优雅 交给PageInfo
        List<Brand> brandList = brandMapper.selectByExample(brand);
        if (CollectionUtils.isEmpty(brandList)) {
            throw new LeyouException(ExceptionEnums.brand_not_found);
        }

        //解析分页结果
        PageInfo<Brand> info = new PageInfo<>(brandList);

        return new PageResult<>(info.getTotal(),brandList);
    }

    /**
     * 复杂操作 添加事务为宜
     * @param brand
     * @param cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //设置id为null以防万一 数据库中id为自增长
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count!=1) {
            throw new LeyouException(ExceptionEnums.brand_save_failed);
        }

        //新增中间表 没有实体类做载体 就用注解写sql
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count!=1) {
                throw new LeyouException(ExceptionEnums.category_brand_save_failed);
            }
        }
    }

    public Brand queryByBrandIds(Long brandIds) {
        Brand brand = brandMapper.selectByPrimaryKey(brandIds);

        if (brand ==null) {
            throw new LeyouException(ExceptionEnums.brand_not_found);
        }

        return brand;
    }
}
