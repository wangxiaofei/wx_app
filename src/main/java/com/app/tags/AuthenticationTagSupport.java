package com.app.tags;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.app.business.AuthorityService;
import com.app.business.model.User;
import com.app.utils.Constant;
import com.app.utils.SpringBeanFactory;

public class AuthenticationTagSupport extends RequestContextAwareTag {
    /**
	 * UID 
	 */
    private static final long serialVersionUID = 1L;
    
    private AuthorityService authorityService ;
    
    private static enum AUTHORITY {

        /**
         * 账户管理
         */
        ManageAccount,
        
        /**
         * 查看秒针官方发布
         */
        ViewOfficialList,
        
        /**
         * 自定义单人群任务
         */
        CreateSingleTask,
        
        /**
         * 自定义交叉人群
         */
        CreateCrossTask,
        
        /**
         * 是管理员用户
         */
        Admin,
        /**
         * 是普通用户
         */
        Normal,
        /**
         * 是否可以进行URL访问路径分析
         */
        CanUrlVisitAnlysis,
        
    };

    /**
     * 权限名称
     */
    private String name = null;

    /**
     * user ID
     */
    private Integer userId = null;
    
    /**
     * Login User
     */
    private User user ;
    
    /**
     * custom ID
     */
    @SuppressWarnings("unused")
    private Integer customId = null;

    /**
     * 任务id
     */
    private Integer taskId = null;
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    @Override
    protected int doStartTagInternal() throws Exception {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        authorityService = (AuthorityService) SpringBeanFactory.getBean(request, "authorityService");
//        urlVisitTypeService =  (UrlVisitTypeService) SpringBeanFactory.getBean(request, "urlVisitTypeServiceImpl");
        user = (User) request.getSession().getAttribute(Constant.SESSION_KEY_USER);
        
        if (null == user) {
            return SKIP_BODY;
        }
        // 判断是否对结果取反。
        boolean negate = false;
        boolean flag = false;
        
        if (userId == null) {
            userId = user.getUserId();
        }

        if (name == null) {
            throw new IllegalArgumentException("Authentication type is null. ");
        } else if (name.indexOf("!") >= 0) {
            name = name.substring(1);
            negate = true;
        }
        AUTHORITY authority = AUTHORITY.valueOf(name);
        switch (authority) {
        case ManageAccount:
            if (authorityService.canManageAccount(user)) {
                
                flag = true;
            }
            break;
        case ViewOfficialList:
            if (authorityService.canViewOfficialList(user)) {
                flag = true;
            }
            break;
        case CreateSingleTask:
            if (authorityService.canCreateSingleTask(user)) {
                flag = true;
            }
            break;
        case CreateCrossTask:
            if (authorityService.canCreateCrossTask(user)) {
                flag = true;
            }
            break;
        case Admin:
            if(user !=null && Constant.ADMIN.equals(user.getRole())){
                flag = true;
            }
            break;
        case Normal: 
            if(user !=null &&Constant.NORMAL.equals(user.getRole()) ){
                flag = true;
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown authentication type: " + authority);
        }
        if (negate) {
            if (flag) {
                return SKIP_BODY;
            } else {
                return EVAL_BODY_INCLUDE;
            }
        } else {
            if (flag) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }

    }

}
