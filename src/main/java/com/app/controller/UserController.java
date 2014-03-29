package com.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.business.UserService;
import com.app.business.model.PageVO;
import com.app.business.model.User;
import com.app.utils.MD5Encrypter;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * show userList.jsp page
     * @return
     */
    @RequestMapping(value="/loadUsers")
    public String loadUsers(){
        return "user/userList";
    }
    
    /**
     * show addUser.jsp page
     * @return
     */
    @RequestMapping(value="jumpCreate")
    public String jumpCreate(){
        return "user/addUser";
    }
    
    /**
     * show modifyUser.jsp page
     * @return
     */
    @RequestMapping(value="jumpModify")
    public String jumpModify(Integer userId,Model model){
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/modifyUser";
    }
    
    /**
     * create new user
     * @return
     */
    @RequestMapping(value="create")
    public @ResponseBody boolean create(@RequestParam Map<String,Object> params,HttpServletRequest req,HttpServletResponse res){
        User user = new User();
        user.setAuth((String)params.get("auth"));
        user.setLoginName((String)params.get("loginName"));
        user.setUserName((String)params.get("userName"));
        user.setEmail((String)params.get("email"));
        user.setPassword(MD5Encrypter.encrypt((String)params.get("password")));
        user.setTelephone((String)params.get("telephone"));
        try{
            userService.add(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    /**
     * modify user
     * @return
     */
    @RequestMapping(value="modify")
    public @ResponseBody boolean modify(@RequestParam Map<String,Object> params,HttpServletRequest req,HttpServletResponse res){
        Integer userId = Integer.parseInt((String)params.get("userId"));
        User user = userService.getUserById(userId);
        
        user.setAuth((String)params.get("auth"));
        //user.setLoginName((String)params.get("loginName"));
        user.setUserName((String)params.get("userName"));
        user.setEmail((String)params.get("email"));
        String password = (String)params.get("password");
        if(password!=null && !"".equals(password))
            user.setPassword(MD5Encrypter.encrypt(password));
        user.setTelephone((String)params.get("telephone"));
        try{
            userService.modify(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    /**
     * get user data list
     * @param params
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value="list")
    public @ResponseBody PageVO list(@RequestParam Map<String,Object> params,HttpServletRequest req,HttpServletResponse res){
        PageVO result = userService.getPagedAccountList(params);
        return result;
    }
    
    /**
     * check login name
     * @param loginName
     * @return
     */
    @RequestMapping(value="/checkLoginName")
    public @ResponseBody boolean checkLoginName(String loginName){
        return userService.checkLoginNameExists(loginName);
    }
    
    /**
     * check password
     * @param loginName
     * @return
     */
    @RequestMapping(value="checkPassword")
    public @ResponseBody boolean checkPassword(@RequestParam Map<String,Object> params){
        Integer userId = Integer.parseInt((String)params.get("userId"));
        User user = userService.getUserById(userId);
        String password = MD5Encrypter.encrypt((String)params.get("password"));
        if(password.equals(user.getPassword()))
            return true;
        return false;
    }
    
    /**
     * delete user by id
     * @param userId
     * @return
     */
    @RequestMapping(value="delete")
    public @ResponseBody boolean delete(Integer userId){
        try{
            userService.delete(userId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    @RequestMapping(value="test")
    public String jumpTestWebsocket(){
    	return "testWebSocket";
    }
    
}
