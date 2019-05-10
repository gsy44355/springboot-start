package com.gsy.springboot.start.serviceImpl;

import com.gsy.springboot.start.pojo.TEmailSend;
import com.gsy.springboot.start.service.EmailSendRecordService;
import com.gsy.springboot.start.service.EmailSenderService;
import com.gsy.springboot.start.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

/**
 * Created By Gsy on 2019/5/3
 */
@Service()
public class EmailSenderServiceImpl implements EmailSenderService {
    private JavaMailSender javaMailSender;
    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender, EmailSendRecordService emailSendRecordService, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.emailSendRecordService = emailSendRecordService;
        this.templateEngine = templateEngine;
    }
    private final EmailSendRecordService emailSendRecordService;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    String sender;
    @Value("${spring.mail.senderName}")
    String senderName;
    @Override
    public Boolean send(String receiver, String subject, String message) {
        return send(new String[]{receiver},subject,message);
    }

    @Override
    public Boolean send(String[] receiver, String subject, String message) {
        return send(receiver,subject,message,null,null);
    }

    @Override
    public Boolean send(String receiver, String subject, String message, String cc) {
        return send(new String[]{receiver},subject,message,cc);
    }

    @Override
    public Boolean send(String[] receiver, String subject, String message, String cc) {
        return send(receiver,subject,message,new String[]{cc});
    }

    @Override
    public Boolean send(String receiver, String subject, String message, String[] cc) {
        return send(new String[]{receiver},subject,message,cc);
    }

    @Override
    public Boolean send(String[] receiver, String subject, String message, String[] cc) {
        return send(receiver,subject,message,cc,null);
    }

    @Override
    public Boolean send(String[] receiver, String subject, String message, String[] cc, String[] appendixFilePath) {
        return send(receiver,subject,message,cc,appendixFilePath,false);
    }

    @Override
    public Boolean send(String[] receiver, String subject, String message, String[] cc, String[] appendixFilePath, boolean isHtmlEmail) {
        boolean sendSuccessFlag = true;
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper;
            if(appendixFilePath != null){
                messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            }else{
                messageHelper = new MimeMessageHelper(mimeMessage,false,"UTF-8");

            }
            messageHelper.setFrom(sender,senderName);
            messageHelper.setTo(receiver);
            messageHelper.setSubject(subject);
            messageHelper.setText(message,isHtmlEmail);
            if (cc != null && cc.length != 0) {
                messageHelper.setCc(cc);
            }
            if (appendixFilePath != null){
                for(String fileName : appendixFilePath){
                    File file = new File(fileName);
                    messageHelper.addAttachment(file.getName(),file);
                }
            }
        };
        try {
            javaMailSender.send(messagePreparator);
            sendSuccessFlag = true;
            return true;
        } catch (MailException e) {
//            e.printStackTrace();
            LogUtil.error(this.getClass(),"发送邮件出错",e);
            sendSuccessFlag = false;
            return false;
        }finally {
            try{
                TEmailSend tEmailSend = new TEmailSend();
                tEmailSend.setSubject(subject);
                tEmailSend.setReceivers(String.join(";",receiver));
                tEmailSend.setCc(cc==null?"":String.join(";",cc));
                tEmailSend.setAddattachment(appendixFilePath==null?"":String.join(";",appendixFilePath));
                tEmailSend.setSendSuccess(sendSuccessFlag?"1":"0");
                emailSendRecordService.recordEmailSend(tEmailSend);
            }catch (Exception e){
                LogUtil.error(this.getClass(),"记录发送邮件出错",e);
            }

        }
    }

    @Override
    public Boolean sendByTemplate(String receiver, String subject, String template, Map variables) {
        String message = "";
        try{
            Context context = new Context();
            context.setVariables(variables);
            message = templateEngine.process(template,context);
        }catch (Exception e){
            LogUtil.error(this.getClass(),"邮件模板解析错误",e);
            return false;
        }
        return send(new String[]{receiver},subject,message,null,null,true);
    }


}
