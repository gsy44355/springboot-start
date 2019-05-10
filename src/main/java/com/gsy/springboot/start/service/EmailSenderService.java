package com.gsy.springboot.start.service;

import java.util.Map;

/**
 * Created By Gsy on 2019/5/3
 */
public interface EmailSenderService {
    /**
     * 前6个接口实现
     *
     */
    Boolean send(String receiver,String subject,String message);
    Boolean send(String[] receivers,String subject,String message);

    Boolean send(String receiver,String subject,String message,String cc);
    Boolean send(String[] receivers,String subject,String message,String cc);
    Boolean send(String[] receivers,String subject,String message,String[] ccs);
    Boolean send(String receiver,String subject,String message,String[] ccs);

    /**
     * 目前只需要实现基于本地文件的附件即可
     * @param receivers
     * @param subject
     * @param message
     * @param ccs
     * @param appendixFilePaths
     * @return
     */
    Boolean send(String[] receivers,String subject,String message,String[] ccs,String[] appendixFilePaths);

    Boolean send(String[] receivers,String subject,String message,String[] ccs,String[] appendixFilePaths,boolean isHtmlEmail);
    Boolean sendByTemplate(String receiver, String subject,String template,Map variables);
}
