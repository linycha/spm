package com.spm.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.common.Res;
import com.spm.modules.dao.GradeMapper;
import com.spm.modules.dao.UserMapper;
import com.spm.modules.dto.PasswordDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.dto.UserDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.User;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    Res<String> updateMobile(User user);

    /**
     * 修改用户密码
     * @param dto dto
     * @return
     */
    Res<String> updatePassword(PasswordDTO dto);

    Res<String> checkValid(String str, String type);

    Res<String> restPassword(String username,String passwordNew,String forgetToken);

    User selectByUsername(String username, Integer userId);

    String selectUsernameByMobile(String mobile);

    Res<String> updateUsername(User user);

    IPage<User> selectPage(QueryDTO dto);

    Res<String> saveOrUpdateUser(User user);

    int updateRole(UserDTO dto);

}
