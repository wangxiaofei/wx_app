package com.app.business;

import java.util.List;
import java.util.Map;

import com.app.business.model.PageVO;
import com.app.business.model.User;

public interface UserService {
    
    /**
     * check login 
     * 
     * @param user
     * 
     * @return ok user.message is blank otherwise login failure user.message is error info
     * @throws Exception 
     */
    public User checkLogin(User user) throws Exception;

    public PageVO getPagedAccountList(Map<String, Object> paramMap);

    /**
     * 用户 重名 校验
     * @param userName
     * @param id
     * @return
     */
    public boolean checkNameExist(String userName, Integer id);

    /**
     * 添加
     * @param user
     */
    public void add(User user);
    
    public void addUserList(List<User> userList) throws Exception;
    
    public void addUserArray(User[] userArray) throws Exception;

    /**
     * get user by Id
     * @param userId
     * @return
     */
    public User getUserById(Integer userId);

    /**
     * 修改用户
     * @param user
     */
    public void modify(User user);

    /**
     * delete
     * @param userId
     */
    public void delete(Integer userId);

    /**
     *  用户 当前已经开始的任务数
     * @param userId
     * @return
     */
    public Integer getUsedTaskCountByUserId(Integer userId);

    /**
     * 修改 个人信息
     * @param user
     */
    public void modifyInfo(User user);

    /**
     * password new old compare
     * @param user
     * @return
     */
    public boolean oldPwdChecker(User user);

    /**
     * 修改密码
     * @param user
     */
    public void modifyPassword(User user);
    
    /**
     * 获取 admin权限字符
     * @return
     */
    public String getAdminAuth() ;

    /**
     * 发送 重置密码 邮件
     * 
     * @param userName
     * @return
     */
    public boolean getPassword(String userName);

    /**
     * 重置用户密码
     * @param userId
     * @param token
     * @return
     */
    public boolean resetPassword(Integer userId, String token);

    /**
     * 获得 所有用户 列表
     * @return
     */
    public List<User> getAllUserList();

    /**
     * 重名 校验
     * @param loginName
     * @return
     */
    boolean checkLoginNameExists(String loginName);

}