package com.spm.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.dto.UserDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.Role;
import com.spm.modules.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User selectByUsernameOrUserId(@Param("username") String username,@Param("userId") Integer userId);

    /**
     * 通过用户id获取对应角色信息
     * @param userId
     * @return
     */
    Role selectRoleByUserId(String userId);
    /**
     * 添加用户的角色
     */
    int insertRole(@Param("userId")Integer userId,@Param("roleId")Integer roleId);
    int updateByPrimaryKey(User record);
    int checkUsername(String username);
    int checkMobile(String mobile);
    User selectLogin(@Param("username")String username,@Param("password")String password);
    int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
    int checkPassword(@Param("password")String password,@Param("userId")String userId);
    String selectUsernameByMobile(String phone);

    IPage<User> selectPage(IPage<Object> page, QueryDTO dto);

    int updateRole(UserDTO dto);
}