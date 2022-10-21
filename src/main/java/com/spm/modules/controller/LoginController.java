package com.spm.modules.controller;

import com.spm.common.Res;
import com.spm.common.ResponseCode;
import com.spm.common.utils.UserUtils;
import com.spm.modules.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/15 19:26
 */
@RestController
@RequestMapping
@Api(tags = "登录相关接口")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public Res<Map<String,Object>> login(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            info.put("token",subject.getSession().getId());
            info.put("userId",UserUtils.getUserId());
            return Res.success("登录成功", info);
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            return Res.errorMsg("用户名或密码错误");
        } catch (LockedAccountException e){
            e.printStackTrace();
            return Res.errorMsg("该账号已被禁用，请联系管理员");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Res.errorMsg("登录异常，请联系管理员");
        }
    }

    /**
     * 用户注册接口
     * @param dto dto
     * @return
     */
/*    @PostMapping("register")
    public Res<String> register(@RequestBody RegisterDTO dto){
        return userService.insertRegister(dto);
    }*/

    @GetMapping("/to_login")
    public Res<String> toLogin(){
        return Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"请先登录账号");
    }
    @GetMapping("/unauthc")
    public Res<String> unAuthor(){
        return Res.errorCodeMsg(403,"当前用户没有操作权限");
    }

    @GetMapping("/logout")
    public Res<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Res.successMsg("退出成功");
    }
}
