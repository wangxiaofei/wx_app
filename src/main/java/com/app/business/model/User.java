package com.app.business.model;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    private Integer userId;
    
    private String loginName;

    private String userName;

    private String password;

    private String role;

    /**
     * 权限 ; 分割
     */
    private String auth;

    private String email;

    private String telephone;

    private String description;

    private Integer isDeleted;

    private Integer taskCount;

    private Integer overTaskCount;

    private Integer runingTaskCount;

    /**
     * 占用的任务数()
     */
    private Integer usedTaskCounct;

    /**
     * 是否保持登录状态
     */
    private Boolean keepLogin = false;

    /**
     * msg
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getOverTaskCount() {
        return overTaskCount;
    }

    public void setOverTaskCount(Integer overTaskCount) {
        this.overTaskCount = overTaskCount;
    }

    public Integer getRuningTaskCount() {
        return runingTaskCount;
    }

    public void setRuningTaskCount(Integer runingTaskCount) {
        this.runingTaskCount = runingTaskCount;
    }

    public Boolean getKeepLogin() {
        return keepLogin;
    }

    public void setKeepLogin(Boolean keepLogin) {
        this.keepLogin = keepLogin;
    }

    public Integer getUsedTaskCounct() {
        return usedTaskCounct;
    }

    public void setUsedTaskCounct(Integer usedTaskCounct) {
        this.usedTaskCounct = usedTaskCounct;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
