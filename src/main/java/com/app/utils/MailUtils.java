package com.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件工具类
 * @author andy 20112-12-20
 *
 */
public class MailUtils {
    
    private static MailUtils mailUtils;
	
    private String emailHost;//发件人邮箱服务器  
    private String emailFrom;//发件人邮箱  
    private String emailUserName;//发件人用户名   
    private String emailPassword;//发件人密码  
    private Map<String, String> pictures;//邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址  
    private Map<String, String> attachments;//邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址 
    
    private MailUtils() {
        
    }
    
    public static MailUtils getInstance() {
        if (null == mailUtils) {
            mailUtils = new MailUtils();
        }
        return mailUtils;
    }

    private String getEmailHost() {  
        if (StringUtils.isBlank(emailHost)) {  
            emailHost = Constant.EMAIL_HOST;  
        }  
        return emailHost;  
    }  
  
    private String getEmailFrom() {  
        if (StringUtils.isBlank(emailFrom)) {  
            emailFrom = Constant.EMIAL_FROM;  
        }  
        return emailFrom;  
    }  
  
    private String getEmailUserName() {  
        if (StringUtils.isBlank(emailUserName)) {  
            emailUserName = Constant.EMAIL_USER_NAME;  
        }  
        return emailUserName;  
    }  
  
    private String getEmailPassword() {  
        if (StringUtils.isBlank(emailPassword)) {  
            emailPassword = Constant.EMAIL_PASSWORD;  
        }  
        return emailPassword;
    }  
  
  
    /** 
     * 发送邮件 
     *  
     * @author andy 
     * @date 2012-11-08 上午11:18:21 
     * @throws Exception 
     */  
    public void sendEmail(String toEmails, String subject, String content) throws Exception {  
        
        if (this.getEmailHost().equals("") || this.getEmailFrom().equals("")  
                || this.getEmailUserName().equals("")  
                || this.getEmailPassword().equals("")) {  
            throw new RuntimeException("发件人信息不完全，请确认发件人信息！");  
        }  
  
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
  
        // 设定mail server  
        senderImpl.setHost(emailHost);  
  
        // 建立邮件消息  
        MimeMessage mailMessage = senderImpl.createMimeMessage();  
  
        MimeMessageHelper messageHelper = null;  
        messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");  
        // 设置发件人邮箱  
        messageHelper.setFrom(emailFrom);  
        // 添加验证  
        senderImpl.setPassword(emailPassword);
        senderImpl.setUsername(emailUserName);
        
  
        // 设置收件人邮箱  
        String[] toEmailArray = toEmails.split(";");  
        List<String> toEmailList = new ArrayList<String>();  
        if (null == toEmailArray || toEmailArray.length <= 0) {  
            throw new RuntimeException("收件人邮箱不得为空！");  
        } else {  
            for (String s : toEmailArray) {  
                if (StringUtils.isNotBlank(s)) {  
                    toEmailList.add(s);  
                }  
            }  
            if (null == toEmailList || toEmailArray.length == 0) {  
                throw new RuntimeException("收件人邮箱不得为空！");  
            } else {  
                toEmailArray = new String[toEmailList.size()];  
                for (int i = 0; i < toEmailList.size(); i++) {  
                    toEmailArray[i] = toEmailList.get(i);  
                }  
            }  
        }  
        messageHelper.setTo(toEmailArray);  
  
        // 邮件主题  
        messageHelper.setSubject(subject);  
  
        // true 表示启动HTML格式的邮件  
        messageHelper.setText(content, true);  
  
        // 添加图片  
        if (null != pictures) {  
            for (Map.Entry<String, String> entry : pictures.entrySet()) {  
                String cid = entry.getKey();  
                String filePath = entry.getValue();  
                if (null == cid || null == filePath) {  
                    throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");  
                }  
  
                File file = new File(filePath);  
                if (!file.exists()) {  
                    throw new RuntimeException("图片" + filePath + "不存在！");  
                }  
  
                FileSystemResource img = new FileSystemResource(file);  
                messageHelper.addInline(cid, img);  
            }  
        }  
  
        // 添加附件  
        if (null != attachments) {  
            for (Map.Entry<String, String> entry : attachments.entrySet()) {  
                String cid = entry.getKey();  
                String filePath = entry.getValue();  
                if (null == cid || null == filePath) {  
                    throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");  
                }  
  
                File file = new File(filePath);  
                if (!file.exists()) {  
                    throw new RuntimeException("附件" + filePath + "不存在！");  
                }  
  
                FileSystemResource fileResource = new FileSystemResource(file);  
                messageHelper.addAttachment(cid, fileResource);  
            }  
        }  
  
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put("mail.smtp.timeout", "25000");  

        
        Session session = Session.getDefaultInstance(prop);  
        senderImpl.setSession(session);  
  
        // 发送邮件  
        senderImpl.send(mailMessage);  
    }
    
}
