package com.app.business.Impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.business.UserService;
import com.app.business.model.PageVO;
import com.app.business.model.User;
import com.app.persistance.UserDAO;
import com.app.utils.Constant;
import com.app.utils.MD5Utils;
import com.app.utils.MailUtils;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	private final String MSG_USERNAME = "用户名错误";
	private final String MSG_USERNAME_BLANK = "请输入用户名";
	private final String MSG_PASSWORD = "密码错误";

	@Autowired
	private UserDAO userDAO;

	@Override
	public PageVO getPagedAccountList(Map<String, Object> paramMap) {
		PageVO page = new PageVO(paramMap);
		// 处理 过滤条件
		// this.changeSearchKeyword(paramMap);

		Integer count = userDAO.getUserListCount(paramMap);

		List<User> userList = userDAO.getUserList(paramMap);

		page.setRows(userList);
		page.setTotalCount(count);

		page.fillPaged(paramMap);
		return page;
	}

	@Override
	public User checkLogin(User user) throws Exception {
		User loginUser = userDAO.getUserByLoginName(user.getLoginName());
		if (loginUser == null)
			throw new Exception("user " + user.getLoginName() + " not exists");
		if (!user.getPassword().equalsIgnoreCase(loginUser.getPassword()))
			throw new Exception("user " + user.getLoginName() + " password mismatch");

		return loginUser;
	}

	/**
	 * user.password 》》 MD5
	 * 
	 * @param user
	 * @return
	 */
	private User dealPassword(User user) {

		user.setPassword(MD5Utils.getInstance().encryptToMD5(user.getPassword()));
		return user;
	}

	/**
	 * 登录判断
	 * 
	 * @param user
	 * @return ok is null else error info
	 */
	private String loginMessage(User user) {

		if (null != user && StringUtils.isNotBlank(user.getUserName())) {
			User persistanceUser = this.userDAO.getUserByName(user.getUserName());
			if (null == persistanceUser) {
				return MSG_USERNAME;
			} else {
				persistanceUser = this.userDAO.getUserByUserNameAndPassword(user);
				if (null == persistanceUser) {
					return MSG_PASSWORD;
				}
			}
			// 成功登录 返回值 null
			return null;
		}

		return MSG_USERNAME_BLANK;
	}

	/**
	 * 处理过滤条件 ‘搜索关键字’
	 * 
	 * @param paramMap
	 */
	private void changeSearchKeyword(Map<String, Object> paramMap) {
		String key = (String) paramMap.get("searchKeyword");
		if (StringUtils.isNotBlank(key)) {
			paramMap.put("nameKey", "%" + key + "%");
			paramMap.put("roleKey", this.roleKeyConvert(key));
		}
	}

	/**
	 * 角色搜索关键字 对应关系
	 * 
	 * @param roleKey
	 * @return
	 */
	private String roleKeyConvert(String roleKey) {

		if (StringUtils.isNotBlank(roleKey)) {
			if (Constant.roleMap.get(Constant.ROLE_ADMIN).indexOf(roleKey) != -1) {

				return Constant.ROLE_ADMIN;
			} else if (Constant.roleMap.get(Constant.ROLE_NORMAL).indexOf(roleKey) != -1) {

				return Constant.ROLE_NORMAL;
			}
		}
		return null;
	}

	@Override
	public boolean checkNameExist(String userName, Integer id) {
		// 添加
		if (null == id) {
			return this.checkNameExistAdd(userName);
		} else {
			return this.checkNameExistModify(userName, id);
		}

	}

	/**
	 * exist -> false else false
	 * 
	 * @param userName
	 * @return
	 */
	private boolean checkNameExistAdd(String userName) {

		User user = this.userDAO.getUserByName(userName);
		if (null != user) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * exist -> false else false
	 * 
	 * @param loginName
	 * @return
	 */
	@Override
	public boolean checkLoginNameExists(String loginName) {

		User user = userDAO.getUserByName(loginName);
		if (null != user) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * exist -> false else false
	 * 
	 * @param userName
	 * @return
	 */
	private boolean checkNameExistModify(String userName, Integer id) {

		User user = this.userDAO.getUserByName(userName);
		if (null != user && id != user.getUserId()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 添加
	 * 
	 * @param user
	 */
	public void add(User user) {

		if (null != user) {
			// set default pwd
			// user.setPassword(MD5Utils.getInstance().encryptToMD5(Constant.DEFAULT_PWD));
			// if (Constant.ROLE_ADMIN.equals(user.getRole())) {
			// user.setAuth(this.getAdminAuth());
			// user.setTaskCount(null);
			// }

			userDAO.save(user);
		}

	}

	@Override
	public void addUserList(List<User> userList) {
		userDAO.saveList(userList);
	}
	
	@Override
	public void addUserArray(User[] userArray) {
		userDAO.saveArray(userArray);
	}

	public void modify(User user) {
		if (null != user) {
			// if (Constant.ROLE_ADMIN.equals(user.getRole())) {
			// user.setAuth(this.getAdminAuth());
			// user.setTaskCount(null);
			// }

			this.userDAO.update(user);
		}
	}

	/**
	 * admin has all auth
	 * 
	 * @return
	 */
	@Override
	public String getAdminAuth() {
		Set<String> sets = Constant.authMap.keySet();
		StringBuilder sb = new StringBuilder();
		if (null != sets) {
			for (String auth : sets) {
				sb.append(auth);
				sb.append(Constant.SPLITER_COMMA);
			}
		}

		return sb.toString();
	}

	public User getUserById(Integer userId) {

		return this.userDAO.getUserById(userId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer userId) {

		this.userDAO.delete(userId);
	}

	@Override
	public Integer getUsedTaskCountByUserId(Integer userId) {

		return this.userDAO.getUsedTaskCountByUserId(userId);
	}

	@Override
	public void modifyInfo(User user) {
		this.userDAO.modifyInfo(user);
	}

	@Override
	public boolean oldPwdChecker(User user) {

		User persistUser = this.userDAO.getUserById(user.getUserId());
		if (StringUtils.isNotBlank(user.getPassword()) && persistUser.getPassword().equals(MD5Utils.getInstance().encryptToMD5(user.getPassword()))) {
			return true;
		}
		return false;
	}

	@Override
	public void modifyPassword(User user) {
		user.setPassword(MD5Utils.getInstance().encryptToMD5(user.getPassword()));
		this.userDAO.modifyPassword(user);
	}

	@Override
	public boolean getPassword(String userName) {
		User user = this.userDAO.getUserByName(userName);
		if (null != user) {
			return this.sendRestPwdEmail(user);
		}
		return false;
	}

	/**
	 * 发送 重置 密码 链接 邮件
	 * 
	 * @param user
	 * @return
	 */
	private boolean sendRestPwdEmail(User user) {
		boolean result = true;
		try {
			// TODO
			MailUtils.getInstance().sendEmail(user.getEmail(), "重置密码", this.getResetPwdEmailContent(user));
		} catch (Exception e) {
			result = false;
			logger.error("EMail Send Error...", e);
		}
		return result;
	}

	/**
	 * 发送 重置密码 邮件 内容
	 * 
	 * @param user
	 * @return
	 */
	private String getResetPwdEmailContent(User user) {
		String resetHref = this.getRestHref(user);
		StringBuilder sb = new StringBuilder("尊敬的用户：<br/>您好！<br/>请点击：");
		sb.append("<a href=\"");
		sb.append(resetHref);
		sb.append("\">");
		sb.append(resetHref);
		sb.append("</a><br/>");
		sb.append("（若无法直接点击，请您复制/粘帖链接到地址栏来访问）<br/>");
		sb.append("将您的密码重置为初始密码。<br/>");
		sb.append(" 密码重置后请您尽快修改您的密码。<br/>");
		sb.append("以上信息如有疑问，请线下联系知客系统管理员。<br/>");
		sb.append("本邮件为系统自动发送，请勿回复此邮件地址，谢谢合作！<br/><br/><br/><br/>");
		sb.append("秒针系统<br/>");
		sb.append("<img src=\"" + Constant.LOG_IMG + "\" width=\"391\" height=\"37\" /></p>");

		return sb.toString();
	}

	/**
	 * 重置密码 链接
	 * 
	 * @return
	 */
	private String getRestHref(User user) {
		StringBuilder href = new StringBuilder(Constant.SYS_DOMAIN);
		href.append("account/forget/restPwd/");
		href.append(user.getUserId());
		href.append("/");
		href.append(this.getRestPwdToken(user.getUserId(), user.getPassword()));
		href.append("/");

		return href.toString();
	}

	@Override
	public boolean resetPassword(Integer userId, String token) {
		// check token
		if (!this.resetPwdTokenChecker(userId, token)) {
			return false;
		}
		User user = new User();
		user.setUserId(userId);
		user.setPassword(MD5Utils.getInstance().encryptToMD5(Constant.DEFAULT_PWD));
		this.userDAO.modifyPassword(user);
		return true;
	}

	/**
	 * 重置密码token 校验 场景1：token 不为空并且等于 MD5(userId+oldPassword) true 其它场景：返回false
	 * 
	 * @param userId
	 * @param token
	 * @return
	 */
	private boolean resetPwdTokenChecker(Integer userId, String token) {
		User user = this.userDAO.getUserById(userId);
		if (StringUtils.isBlank(token) || null == user || !token.equals(this.getRestPwdToken(userId, user.getPassword()))) {
			return false;
		}
		return true;
	}

	/**
	 * 重置密码Token：对用户id用户密码做MD5，16进制32加密
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	private String getRestPwdToken(Integer userId, String password) {

		return MD5Utils.getInstance().encryptToMD5(userId + password);
	}

	@Override
	public List<User> getAllUserList() {

		return this.userDAO.getAllUserList();
	}
}
