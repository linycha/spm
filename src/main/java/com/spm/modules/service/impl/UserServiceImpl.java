package com.spm.modules.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.common.Const;
import com.spm.common.Res;
import com.spm.common.utils.CheckUtil;
import com.spm.common.utils.UserUtils;
import com.spm.modules.dao.UserMapper;
import com.spm.modules.dto.PasswordDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.dto.UserDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.Role;
import com.spm.modules.entity.User;
import com.spm.modules.service.UserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Res<String> updateMobile(User user){
        int result = userMapper.checkMobile(user.getMobile());
        if(result != 1){
            return Res.errorMsg("手机号已存在");
        }
        result = userMapper.updateByPrimaryKeySelective(user);
        if(result >0){
            return Res.successMsg("修改成功");
        }
            return Res.errorMsg("修改失败");
    }
    @Override
    public Res<String> updatePassword(PasswordDTO dto){
        User user = userMapper.selectByPrimaryKey(UserUtils.getUserId());
        String oldPwd = UserUtils.hashTwo(dto.getOldPassword());
        if(!oldPwd.equals(user.getPassword())){
            return Res.errorMsg("输入的旧密码错误");
        }
        int result = userMapper.updateByPrimaryKeySelective(
                User.builder().id(UserUtils.getUserId()).
                        password(UserUtils.hashTwo(dto.getNewPassword())).build()
        );
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }


    /**
     * 校验用户名和email是否存在
     * */
    @Override
    public Res<String> checkValid(String str, String type){
        if(StringUtils.isNotBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return Res.errorMsg("用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = userMapper.checkMobile(str);
                if(resultCount > 0){
                    return Res.errorMsg("手机号码已存在");
                }
            }
        }else{
            return Res.errorMsg("参数错误");
        }
        return Res.successMsg("校验成功");
    }
    @Override
    public Res<String> restPassword(String username,String newPassword,String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return Res.errorMsg("参数错误，token需要传递");
        }
        Res vaildResponse = this.checkValid(username,Const.USERNAME);
        if(vaildResponse.isSuccess()){
            //用户不存在
            return Res.errorMsg("用户不存在");
        }
/*        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return Res.errorMsg("token无效或者过期");
        }
        if(StringUtils.equals(forgetToken,token)){
            String md5Password = UserUtils.hashTwo(newPassword);
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);
            if(rowCount > 0){
                return Res.successMsg("修改密码成功");
            }
        }else{
            return Res.errorMsg("token错误，请重新获取重置密码的token");
        }*/
        return Res.errorMsg("修改密码失败");
    }

    @Override
    public User selectByUsername(String username,Integer userId){
        User user = userMapper.selectByUsernameOrUserId(username,userId);
        if(user != null){
            user.setRoles(user.getRoleList().stream().map(Role::getName).toArray(String[]::new));
        }
        return user;
    }
    @Override
    public String selectUsernameByMobile(String mobile){
        return userMapper.selectUsernameByMobile(mobile);
    }

    @Override
    public Res<String> updateUsername(User user) {
        int count = userMapper.checkUsername(user.getUsername());
        if(count > 0){
            return Res.errorMsg("用户名已存在重复");
        }
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result == 0){
            return Res.errorMsg("修改用户名失败");
        }
        return Res.successMsg("修改用户名成功");
    }

    @Override
    public IPage<User> selectPage(QueryDTO dto) {
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());

        return userMapper.selectPage(page, dto);
    }

    @Override
    public Res<String> saveOrUpdateUser(User user) {
        if(user.getId() != null){
            user.setPassword(UserUtils.hashTwo(user.getPassword()));
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            // 新增的时候才校验用户名手机号
            int count = userMapper.checkMobile(user.getMobile());
            if(count > 0) {
                return Res.errorMsg("手机号已存在");
            }
            int result = userMapper.checkUsername(user.getUsername());
            if(result > 0) {
                return Res.errorMsg("用户名已存在");
            }
            // 初始密码123456
            user.setPassword(UserUtils.hashTwo("123456"));
            userMapper.insertSelective(user);
            // 默认角色收银员
            userMapper.insertRole(user.getId(), 1);
        }

        return Res.successMsg("新增或修改成功");
    }

    @Override
    public int updateRole(UserDTO dto) {
        return userMapper.updateRole(dto);
    }

}
