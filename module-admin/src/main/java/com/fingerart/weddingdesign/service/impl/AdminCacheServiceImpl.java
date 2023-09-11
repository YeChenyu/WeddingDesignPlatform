package com.fingerart.weddingdesign.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.fingerart.weddingdesign.mapper.AdminRoleRelationMapper;
import com.fingerart.weddingdesign.entity.TAdmin;
import com.fingerart.weddingdesign.entity.TAdminRoleRelation;
import com.fingerart.weddingdesign.entity.TAdminRoleRelationExample;
import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.mapper.TAdminRoleRelationMapper;
import com.fingerdesign.weddingdesign.service.RedisService;
import com.fingerart.weddingdesign.service.AdminCacheService;
import com.fingerart.weddingdesign.service.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户缓存操作Service实现类
 * Created by macro on 2020/3/13.
 */
@Service
public class AdminCacheServiceImpl implements AdminCacheService {
    @Autowired
    private TAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TAdminRoleRelationMapper tAdminRoleRelationMapper;
    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long adminId) {
        TAdmin admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        TAdminRoleRelationExample example = new TAdminRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<TAdminRoleRelation> relationList = tAdminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        TAdminRoleRelationExample example = new TAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<TAdminRoleRelation> relationList = tAdminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationMapper.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public TAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (TAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(TAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

    @Override
    public List<TResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        return (List<TResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<TResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }
}
