package com.fingerart.weddingdesign.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.fingerart.weddingdesign.bo.AdminUserDetails;
import com.fingerart.weddingdesign.dto.UmsAdminParam;
import com.fingerart.weddingdesign.dto.UpdateAdminPasswordParam;
import com.fingerart.weddingdesign.entity.*;
import com.fingerart.weddingdesign.mapper.AdminRoleRelationMapper;
import com.fingerart.weddingdesign.mapper.TAdminMapper;
import com.fingerart.weddingdesign.mapper.TAdminRoleRelationMapper;
import com.fingerart.weddingdesign.mapper.TLoginLogMapper;
import com.fingerart.weddingdesign.service.AdminCacheService;
import com.fingerart.weddingdesign.service.TAdminService;
import com.fingerart.weddingdesign.service.TUserService;
import com.fingerart.weddingdesign.util.JwtTokenUtil;
import com.fingerart.weddingdesign.util.SpringUtil;
import com.fingerdesign.weddingdesign.exception.Asserts;
import com.fingerdesign.weddingdesign.util.RequestUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 小程序用户管理Service实现类
 * Created by yecy on 2023/8/07.
 */
@Service
public class TUserServiceImpl implements TUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TUserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TAdminMapper adminMapper;
    @Autowired
    private TAdminRoleRelationMapper tAdminRoleRelationMapper;
    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private TLoginLogMapper loginLogMapper;

    public String getWechatArticleList(String token) {
        // 获取文章列表
        LOGGER.info("WX_TOKEN token="+ token);
        String WX_TOKEN_URL =  "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token;

        String serverResult = null;
        HttpURLConnection conn = null;
        try {
            URL serverUrl = new URL(WX_TOKEN_URL);
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            //必须设置false，否则会自动redirect到重定向后的地址
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //设置body内的参数，put到JSONObject中
            JSONObject param = new JSONObject();
            param.put("type", "news");  // 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
            param.put("offset", "0");   // 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
            param.put("count", "20");   // 返回素材的数量，取值在1到20之间

            conn.connect();
            // 得到请求的输出流对象
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            writer.write(param.toString());
            writer.flush();

            serverResult = getReturn(conn);
            LOGGER.debug("WX_TOKEN="+ serverResult);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }finally {
            if(conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        if(StringUtils.isEmpty(serverResult)){
            LOGGER.error("WX_TOKEN fetch failed");
            return null;
        }
        return serverResult;
    }

    public String getReturn(HttpURLConnection connection) {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TAdmin getAdminByUsername(String username) {
//        //先从缓存中获取数据
//        TAdmin admin = getCacheService().getAdmin(username);
//        if (admin != null) return admin;
//        //缓存中没有从数据库中获取
//        TAdminExample example = new TAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        List<TAdmin> adminList = adminMapper.selectByExample(example);
//        if (adminList != null && adminList.size() > 0) {
//            admin = adminList.get(0);
//            //将数据库中的数据存入缓存中
//            getCacheService().setAdmin(admin);
//            return admin;
//        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        LOGGER.info("login: username=%s, password=%s", username, password);
        String token = null;
        //密码需要客户端加密后传递
//        try {
//            UserDetails userDetails = loadUserByUsername(username);
//            if(!passwordEncoder.matches(password,userDetails.getPassword())){
//                Asserts.fail("密码不正确");
//            }
//            if(!userDetails.isEnabled()){
//                Asserts.fail("帐号已被禁用");
//            }
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            token = jwtTokenUtil.generateToken(userDetails);
////            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
//        } catch (AuthenticationException e) {
//            LOGGER.warn("登录异常:{}", e.getMessage());
//        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        TAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        TLoginLog loginLog = new TLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        TAdmin record = new TAdmin();
        record.setLoginTime(new Date());
        TAdminExample example = new TAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public TAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        TAdminExample example = new TAdminExample();
        TAdminExample.Criteria criteria = example.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, TAdmin admin) {
        admin.setId(id);
        TAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = adminMapper.updateByPrimaryKeySelective(admin);
//        getCacheService().delAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
//        getCacheService().delAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
//        getCacheService().delResourceList(id);
        return count;
    }



}
