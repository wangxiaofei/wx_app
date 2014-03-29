package com.app.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    /**
     * weixinToken
     */
	public static String WECHAT_TOKEN ;
    
    /**
     * session key user
     */
    public static final String SESSION_USER = "user";
    public static final String SESSION_KEY_USER = "sessionKeyUser";
    public static final String SESSION_KEY_USER_ID = "sessionUserId";
    public static final String SESSION_SEED = "seed";
    public static final String SESSION_USER_NAME = "userName";
    
    /**
     * 邮件发送相关
     */
    public static String EMAIL_HOST ;
    public static String EMIAL_FROM ;
    public static String EMAIL_PASSWORD ;
    public static String EMAIL_USER_NAME ;
    public static String SYS_DOMAIN ;
    public static String LOG_IMG ;
    
    /**
     * url 规则文件读取
     */
    public static String URL_FILTER_POSITION;
    public static String URL_FILTER_TURN_ON;
    
    public static String REPORT_BASE_DIR ;

    /**
     * Demo 账户ID
     */
    public static final Integer USERID_DEMO = 2;

    // 加密关键字
    public static final String WATCH_WORD = "miaozhen.supertool.userMiner";
    // 记住用户和密码
    public static final String REMEMBERED_PASSWORD = "rememberedPassword";
    public static final String REMEMBERED_USERNAME = "rememberedUserName";
    public static final String REMEMBERED_STATUS = "rememberedStatus";
    public static final String REMEMBERED_STATUS_FALSE = "false";
    public static final String REMEMBERED_STATUS_TRUE = "true";
    public static final String COOKIE_USERNAME = "auun";
    public static final String COOKIE_PASSWORD = "auunpd";
    public static final String USER = "user";
    /**
     * 用户role
     */
    public static final String USER_AUTH = "auth";
    public static final String ADMIN = "admin";
    public static final String NORMAL = "normal";
    /**
     * 官方人群和自定义人群的前缀
     */
    public static final String OFFICIAL_ID_PREFIX = "M";
    public static final String FOLK_ID_PREFIX = "C";
    /**
     * 任务类型的id，这个需要和数据库中的id对应
     */
    public static final Integer SINGLE_TASK_TYPE_ID = 1;
    public static final Integer CROSS_TASK_TYPE_ID = 2;
    public static final Integer COMPARE_TASK_TYPE_ID = 3;
    /**
     * 任务状态 要与数据库同步
     */
    public static int NO_VALIDATE = 1;// 未验证
    public static int VALIDATING = 2;// 验证中
    public static int NO_START = 3;// 未开始
    public static int OPERATING = 4;// 运算中
    public static int FINISHED = 5;// 已完成
    public static int RELEASE = 6;// 发布
    public static int VALIDATE_FAILURE = 7;// 验证失败
    public static int OPERATE_FAILURE = 8;// 计算失败
    // role
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_NORMAL = "normal";
    //人群行为路径分析名称
    public static final String DEMO_PATH_ANY_NAME = "人群行为路径分析";
    // 角色名称
    public static final Map<String, String> roleMap = new HashMap<String, String>();
    static {
        roleMap.put(ROLE_ADMIN, "管理员");
        roleMap.put(ROLE_NORMAL, "普通用户");
    }
    public static final String AUTHORITY_OFFICIALLIST = "viewOfficialList";

    public static final String AUTHORITY_SINGLETASK = "singleTask";

    public static final String AUTHORITY_CROSSTASK = "crossTask";

    public static final Map<String, String> authMap = new HashMap<String, String>();
    static {
        authMap.put(AUTHORITY_OFFICIALLIST, "查看官方发布任务");
        authMap.put(AUTHORITY_SINGLETASK, "创建单人群分析任务");
        authMap.put(AUTHORITY_CROSSTASK, "创建交叉人群分析任务");
    }

    /**
     * ajax responseText code ok is 'true'
     */
    public static final boolean WRITER_RESULT_OK = true;

    /**
     * ajax responseText code bad is 'false'
     */
    public static final boolean WRITER_RESULT_ERROR = false;
    
    public static final String DEFAULT_PWD = "123456";
    public static final String SPLITER_COMMA = ",";
    public static final String SPLITER_SEMICOLON = ";";

    // logger map
    /**
     * LOGGER_ACTION_A = "登录";
     */
    public static final String LOGGER_ACTION_A = "登录";
    /**
     * LOGGER_ACTION_B = "退出";
     */
    public static final String LOGGER_ACTION_B = "退出";
    /**
     * LOGGER_ACTION_C = "添加普通任务";
     */
    public static final String LOGGER_ACTION_C = "添加普通任务";
    /**
     * LOGGER_ACTION_D = "添加组合分析任务";
     */
    public static final String LOGGER_ACTION_D = "添加组合分析任务";
    /**
     * LOGGER_ACTION_E = "修改普通任务";
     */
    public static final String LOGGER_ACTION_E = "修改普通任务";
    /**
     * LOGGER_ACTION_F = "修改组合分析任务";
     */
    public static final String LOGGER_ACTION_F = "修改组合分析任务";
    /**
     * LOGGER_ACTION_G = "删除任务";
     */
    public static final String LOGGER_ACTION_G = "删除任务";
    /**
     * LOGGER_ACTION_H = "开始任务";
     */
    public static final String LOGGER_ACTION_H = "开始任务";
    /**
     * LOGGER_ACTION_I = "普通任务验证";
     */
    public static final String LOGGER_ACTION_I = "普通任务验证";
    /**
     * LOGGER_ACTION_J = "发布任务";
     */
    public static final String LOGGER_ACTION_J = "发布任务";
    /**
     * LOGGER_ACTION_K = "增加用户";
     */
    public static final String LOGGER_ACTION_K = "增加用户";
    /**
     * LOGGER_ACTION_L = "修改用户个人信息";
     */
    public static final String LOGGER_ACTION_L = "修改用户个人信息";
    /**
     * LOGGER_ACTION_M = "修改用户任务次数";
     */
    public static final String LOGGER_ACTION_M = "修改用户任务次数";
    /**
     * LOGGER_ACTION_N = "修改用户权限";
     */
    public static final String LOGGER_ACTION_N = "修改用户权限";
    /**
     * LOGGER_ACTION_O = "删除用户";
     */
    public static final String LOGGER_ACTION_O = "删除用户";
    /**
     * LOGGER_ACTION_P = "修改密码";
     */
    public static final String LOGGER_ACTION_P = "修改密码";
    /**
     * LOGGER_ACTION_Q = "修改个人信息";
     */
    public static final String LOGGER_ACTION_Q = "修改个人信息";
    /**
     * LOGGER_ACTION_R = "报表下载";
     */
    public static final String LOGGER_ACTION_R = "报表下载";
    /**
     * 操作模块 系统
     */
    public static final String LOGGER_MODULE_SYS = "系统";
    /**
     * LOGGER_MODULE_TASK = "任务管理";
     */
    public static final String LOGGER_MODULE_TASK = "任务管理";
    /**
     * LOGGER_MODULE_ACCOUNT = "账户管理";
     */
    public static final String LOGGER_MODULE_ACCOUNT = "账户管理";
    /**
     * LOGGER_MODULE_INFO = "个人信息管理";
     */
    public static final String LOGGER_MODULE_INFO = "个人信息管理";
    /**
     * LOGGER_MODULE_REPORT = "报表展示";
     */
    public static final String LOGGER_MODULE_REPORT = "报表展示";

    /**
     * 报表 模板URI
     */
    public static String POI_TEMPLATE_FILE_URI;

    /**
     * 帮助文档 admin
     */
    public static String POI_TEMPLATE_HELPER_ADMIN;

    /**
     * 帮助文档 普通用户
     */
    public static String POI_TEMPLATE_HELPER;

    /**
     * 页面 top 10 以外的 其它
     */
    public static final String TOP_OTHER = "其它";

    /**
     * top num 10
     */
    public static final Integer TOP_10 = 10;

    /**
     * top num 50
     */
    public static final Integer TOP_50 = 50;

    /**
     * top num 100
     */
    public static final Integer TOP_100 = 100;
    
    /**
     * currentPage 1
     */
    public static final Integer PAGE_CURRENTPAGE_1 = 1;
    
    /**
     * 目标链接 node id
     */
    public static final Integer NODE_ID_TARGET = -1;
    /**
     * 任务的状态
     */
    public static final Integer TASK_UNCOMITED=1;
    public static final Integer TASK_INQUEUE=2;
    public static final Integer TASK_CACULATED=3;
    public static final Integer TASK_COMPLETED=4;
    public static final Integer TASK_FAILUER=5;
    
	public static final String REGION_JSON_ID = "id";
	
	public static final String REGION_JSON_PID="pid";
	
	public static final String REGION_JSON_NAME = "name";
	
	public static final String REGION_JSON_NODES = "children";
    

}
