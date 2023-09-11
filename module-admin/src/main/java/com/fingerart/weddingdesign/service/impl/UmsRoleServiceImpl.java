package com.fingerart.weddingdesign.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TRole;
import com.fingerart.weddingdesign.entity.TRoleExample;
import com.fingerart.weddingdesign.mapper.TRoleMapper;
import com.github.pagehelper.PageHelper;
import com.fingerart.weddingdesign.mapper.RoleMapper;
import com.fingerart.weddingdesign.service.AdminCacheService;
import com.fingerart.weddingdesign.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台角色管理Service实现类
 * Created by macro on 2018/9/30.
 */
@Service
public class UmsRoleServiceImpl implements TRoleService {
    @Autowired
    private TRoleMapper tRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminCacheService adminCacheService;

    @Override
    public int create(TRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return tRoleMapper.insert(role);
    }

    @Override
    public int update(Long id, TRole role) {
        role.setId(id);
        return tRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        TRoleExample example = new TRoleExample();
        example.createCriteria().andIdIn(ids);
        int count = tRoleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<TRole> list() {
        return tRoleMapper.selectByExample(new TRoleExample());
    }

    @Override
    public List<TRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        TRoleExample example = new TRoleExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return tRoleMapper.selectByExample(example);
    }

    @Override
    public List<TResource> listResource(Long roleId) {
        return roleMapper.getResourceListByRoleId(roleId);
    }

}
