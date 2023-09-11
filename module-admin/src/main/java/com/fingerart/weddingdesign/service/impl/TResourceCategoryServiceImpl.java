package com.fingerart.weddingdesign.service.impl;

import com.fingerart.weddingdesign.entity.TResourceCategory;
import com.fingerart.weddingdesign.entity.TResourceCategoryExample;
import com.fingerart.weddingdesign.mapper.TResourceCategoryMapper;
import com.fingerart.weddingdesign.service.TResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 * Created by macro on 2020/2/5.
 */
@Service
public class TResourceCategoryServiceImpl implements TResourceCategoryService {
    @Autowired
    private TResourceCategoryMapper tResourceCategoryMapper;

    @Override
    public List<TResourceCategory> listAll() {
        TResourceCategoryExample example = new TResourceCategoryExample();
        example.setOrderByClause("sort desc");
        return tResourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(TResourceCategory tResourceCategory) {
        tResourceCategory.setCreateTime(new Date());
        return tResourceCategoryMapper.insert(tResourceCategory);
    }

    @Override
    public int update(Long id, TResourceCategory tResourceCategory) {
        tResourceCategory.setId(id);
        return tResourceCategoryMapper.updateByPrimaryKeySelective(tResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return tResourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
