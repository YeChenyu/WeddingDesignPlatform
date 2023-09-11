package com.fingerart.weddingdesign.service;

import com.fingerart.weddingdesign.entity.TResourceCategory;

import java.util.List;

/**
 * 后台资源分类管理Service
 * Created by macro on 2020/2/5.
 */
public interface TResourceCategoryService {

    /**
     * 获取所有资源分类
     */
    List<TResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    int create(TResourceCategory tResourceCategory);

    /**
     * 修改资源分类
     */
    int update(Long id, TResourceCategory tResourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}
