package com.fingerart.weddingdesign.controller;

import cn.hutool.core.collection.CollUtil;
import com.fingerart.weddingdesign.dto.DeviceParam;
import com.fingerart.weddingdesign.dto.UmsAdminLoginParam;
import com.fingerart.weddingdesign.dto.UmsAdminParam;
import com.fingerart.weddingdesign.dto.UpdateAdminPasswordParam;
import com.fingerart.weddingdesign.entity.TAdmin;
import com.fingerart.weddingdesign.entity.TRole;
import com.fingerart.weddingdesign.service.SettingsService;
import com.fingerart.weddingdesign.service.TAdminService;
import com.fingerart.weddingdesign.service.TRoleService;
import com.fingerdesign.weddingdesign.api.CommonPage;
import com.fingerdesign.weddingdesign.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 小程序用户管理Controller
 * Created by yecy on 2023/8/07.
 */
@Controller
@Api(tags = "UserController")
@Tag(name = "UserController", description = "小程序用户管理")
@RequestMapping("/user")
public class UserController {

    @Value("${wechat.mini.appId}")
    private String wechatMiniAppid;
    @Value("${wechat.mini.appSecret}")
    private String wechatMiniAppSecret;
    @Autowired
    private TAdminService adminService;
    @Autowired
    private SettingsService settingsService;


    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam(value = "js_code", required = true) String js_code,
                              @RequestBody DeviceParam device) {
        System.out.println("login js_code="+ js_code);
        System.out.println("login device="+ device.toString());
//        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
//        if (token == null) {
//            return CommonResult.validateFailed("用户名或密码错误");
//        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", "hello");
//        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//        String refreshToken = adminService.refreshToken(token);
//        if (refreshToken == null) {
//            return CommonResult.failed("token已经过期！");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", refreshToken);
//        tokenMap.put("tokenHead", tokenHead);
//        return CommonResult.success(tokenMap);
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        TAdmin tAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", tAdmin.getUsername());
//        data.put("menus", roleService.getMenuList(tAdmin.getId()));
        data.put("icon", tAdmin.getAvatar());
        List<TRole> roleList = adminService.getRoleList(tAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(TRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<TAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TAdmin> getItem(@PathVariable Long id) {
        TAdmin admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody TAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



}
