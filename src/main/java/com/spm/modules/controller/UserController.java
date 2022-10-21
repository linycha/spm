package com.spm.modules.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.common.Res;
import com.spm.common.utils.UserUtils;
import com.spm.modules.dto.PasswordDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.dto.UserDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.User;
import com.spm.modules.service.UserService;
import com.spm.modules.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linyuc
 * @date 2019/12/18 15:10
 */
@RestController
@CrossOrigin(origins="*",maxAge=3600)
@RequestMapping("user")
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取用户个人信息
     */
    @GetMapping("info")
    @ApiOperation("获取用户个人信息")
    public Res<User> info(){
        User user = userService.selectByUsername(null,UserUtils.getUserId());
        if(user == null){
            return Res.errorMsg("找不到当前用户");
        }
        return Res.success(user);
    }
    @GetMapping("list")
    public Res<IPage<User>> list(QueryDTO dto){
        return Res.success(userService.selectPage(dto));
    }
    /**
     * 根据id值在表里是否存在判断是新增还是修改操作
     * @param entity
     * @return
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody User entity){
        return userService.saveOrUpdateUser(entity);
    }
    @PutMapping("update_mobile")
    @ApiOperation("修改手机号")
    public Res<String> updateMobile(String mobile){
        User user = new User();
        user.setId(UserUtils.getUserId());
        user.setMobile(mobile);
        return userService.updateMobile(user);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("delete")
    public Res<String> delete(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("id不能为空！");
        }
        List<User> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e->
                list.add(User.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = userService.updateBatchById(list);
        if(result){
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }

    @PutMapping("update_name")
    @ApiOperation("修改用户名")
    public Res<String> updateName(@RequestBody String username){
        if(StringUtils.isBlank(username)){
            return Res.errorMsg("用户名不能为空");
        }
        return userService.updateUsername(User.builder()
                .id(UserUtils.getUserId()).username(username).build());
    }

    @PutMapping("updatePwd")
    @ApiOperation("修改用户个人密码")
    public Res<String> updatePassword(@RequestBody PasswordDTO dto){
        return userService.updatePassword(dto);
    }

    /**
     * 未登录状态下忘记密码的重置密码
     * */
    @PostMapping("rest_password")
    public Res<String> restPassword(String username,String passwordNew,String forgetToken){
        return userService.restPassword(username,passwordNew,forgetToken);
    }

    /**
     * 修改用户角色
     * @param dto
     * @return
     */
    @PutMapping("updateRole")
    public Res<String> updateRole(@RequestBody UserDTO dto){
        int result = userService.updateRole(dto);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }
}
