package com.fingerart.weddingdesign.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TResourceExample;
import com.fingerart.weddingdesign.mapper.TResourceMapper;
import com.github.pagehelper.PageHelper;
import com.fingerart.weddingdesign.service.AdminCacheService;
import com.fingerart.weddingdesign.service.TResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by macro on 2020/2/2.
 */
@Service
public class TResourceServiceImpl implements TResourceService {
    @Autowired
    private TResourceMapper tResourceMapper;
    @Autowired
    private AdminCacheService adminCacheService;
    @Override
    public int create(TResource tResource) {
        tResource.setCreateTime(new Date());
        return tResourceMapper.insert(tResource);
    }

    @Override
    public int update(Long id, TResource tResource) {
        tResource.setId(id);
        int count = tResourceMapper.updateByPrimaryKeySelective(tResource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public TResource getItem(Long id) {
        return tResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = tResourceMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<TResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        TResourceExample example = new TResourceExample();
        TResourceExample.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andUrlLike('%'+urlKeyword+'%');
        }
        return tResourceMapper.selectByExample(example);
    }

    @Override
    public List<TResource> listAll() {
        return tResourceMapper.selectByExample(new TResourceExample());
    }
}
