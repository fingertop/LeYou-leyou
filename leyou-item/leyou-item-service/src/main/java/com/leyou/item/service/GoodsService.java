package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LeyouException;
import com.leyou.common.expojo.PageResult;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Spu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService  {


    @Autowired
    SpuDetailMapper spuDetailMapper;

    @Autowired
    SpuMapper spuMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


    public PageResult<Spu> findGoods(Integer page, Integer rows, Boolean saleable, String key) {



        //分页
        PageHelper.startPage(page,rows);

        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        //搜索字段过滤
        if (StringUtils.isNotBlank(key)) {
                criteria.andLike("title","%"+key+"%");
        }

        //上下架过滤
        if (saleable!=null) {
            criteria.andEqualTo("saleable",saleable);
        }

        //排序                last_update_time lastUpdateTime
        example.setOrderByClause("last_update_time DESC");

        //查询
        List<Spu> list = spuMapper.selectByExample(example);

        //判断
        if (CollectionUtils.isEmpty(list)) {
            throw new LeyouException(ExceptionEnums.goods_not_found);
        }

        //解析分类和品牌的名称
        loadCategoryAndBrandName(list);
        

        //解析分页
        PageInfo<Spu> info = new PageInfo<>(list);

        return new PageResult<>(info.getTotal(),list);
    }

    private void loadCategoryAndBrandName(List<Spu> list) {

        for (Spu spu : list) {

            // 根据ids 处理分类名称
            List<String> cNames = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    //.stream().map(c -> c.getName());
                    .stream().map(Category::getName).collect(Collectors.toList());//取出集合中的对象的某个属性集

            spu.setCname(StringUtils.join(cNames,"/"));//把集合的值变成  以/拼接的字符串

            // 处理品牌名称
            spu.setBname(brandService.queryByBrandIds(spu.getBrandId()).getName());

        }

    }
}
