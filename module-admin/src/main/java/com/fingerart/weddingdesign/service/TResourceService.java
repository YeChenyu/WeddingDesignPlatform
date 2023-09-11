package com.fingerart.weddingdesign.service;

import com.fingerart.weddingdesign.entity.TResource;

import java.util.List;

/**
 * 后台资源管理Service
 * Created by macro on 2020/2/2.
 */
public interface TResourceService {
    /**
     * 添加资源
     */
    int create(TResource umsResource);

    /**
     * 修改资源
     */
    int update(Long id, TResource umsResource);

    /**
     * 获取资源详情
     */
    TResource getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<TResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<TResource> listAll();
}
