package com.app.business;

import com.app.business.model.User;


/**
 * 
 * 登录 用户 权限 (非登录属于false)
 * 
 * @author wanghaitao
 *
 */
public interface AuthorityService {
    
    
    /**
     * 用户 "账户管理" 权限
     * @param userID
     * @return
     */
    public boolean canManageAccount(User user);

    /**
     * 用户 "查看秒针官方任务列表" 权限
     * @param userId
     * @return
     */
    public boolean canViewOfficialList(User user);
    
    /**
     * " 自定义单人群任务"
     * @param userId
     * @return
     */
    public boolean canCreateSingleTask(User user);

    /**
     * "自定义交叉人群"
     * @param userId
     * @return
     */
    public boolean canCreateCrossTask(User user);
    
}