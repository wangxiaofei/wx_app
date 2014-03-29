package com.app.persistance;

import java.util.List;
import java.util.Map;

import com.app.business.model.User;

public interface UserDAO{
    /**
     * 获得用户列表，可以分页
     * 
     * @param paramMap
     * @return
     */
    public List<User> getUserList(Map<String, Object> paramMap);

    public Integer getUserListCount(Map<String, Object> paramMap);

    /**
     * 根据用户名和密码来查找用户
     * 
     * @param paramMap
     * @return
     */
    public User getUserByUserNameAndPassword(User user);

    /**
     * 按用户名查找
     * @param userName
     * @param isDeleted = 0
     * @return
     */
    public User getUserByName(String loginName);
    
    /**
     * search by loginName
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName);
    
    /**
     * 按id 查找用户 角色
     * @param userID
     * @return
     */
    public String getRoleByUserID(Integer userId);

    /**
     * save user
     * @param user
     */
    public void save(User user);
    
    public void saveList(List<User> userList);
    
    public void saveArray(User[] userArray);

    /**
     * get  user by id
     * @param userId
     * @return
     */
    public User getUserById(Integer userId);



    /**
     * delete
     * @param userId
     */
    public void delete(Integer userId);

    /**
     * 获取用户已开始的任务数
     * @return
     */
    public Integer getUsedTaskCountByUserId(Integer userId);

    /**
     * 修改 个人信息
     * @param user
     */
    public void modifyInfo(User user);

    /**
     * 修改密码
     * @param user
     * @param userId
     * @param password (hasEncript)
     */
    public void modifyPassword(User user);

    /**
     * 获得 普通用户 列表
     * @param role 角色
     * @return
     */
    public List<User> getUserListByRole(String role);

    /**
     * 所有用户列表
     * @return
     */
    public List<User> getAllUserList();

	public void update(User user);

    /**
     * 获取用户 忽视 isdeleted
     * @param userName
     * @return
     */
//    public User getUserByNameIgnoreIsdeleted(String userName);

    

    
}
