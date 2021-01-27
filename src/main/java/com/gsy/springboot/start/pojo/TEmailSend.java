package com.gsy.springboot.start.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_email_send")
public class TEmailSend {
    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 收件人
     */
    private String receivers;

    /**
     * 抄送人
     */
    private String cc;

    /**
     * 附件列表
     */
    private String addattachment;

    /**
     * 发送人
     */
    @Column(name = "send_user")
    private String sendUser;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 是否发送成功
     */
    @Column(name = "send_success")
    private String sendSuccess;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取邮件主题
     *
     * @return subject - 邮件主题
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置邮件主题
     *
     * @param subject 邮件主题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取收件人
     *
     * @return receivers - 收件人
     */
    public String getReceivers() {
        return receivers;
    }

    /**
     * 设置收件人
     *
     * @param receivers 收件人
     */
    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    /**
     * 获取抄送人
     *
     * @return cc - 抄送人
     */
    public String getCc() {
        return cc;
    }

    /**
     * 设置抄送人
     *
     * @param cc 抄送人
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * 获取附件列表
     *
     * @return addattachment - 附件列表
     */
    public String getAddattachment() {
        return addattachment;
    }

    /**
     * 设置附件列表
     *
     * @param addattachment 附件列表
     */
    public void setAddattachment(String addattachment) {
        this.addattachment = addattachment;
    }

    /**
     * 获取发送人
     *
     * @return send_user - 发送人
     */
    public String getSendUser() {
        return sendUser;
    }

    /**
     * 设置发送人
     *
     * @param sendUser 发送人
     */
    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * 获取创建时间
     *
     * @return created_date - 创建时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建时间
     *
     * @param createdDate 创建时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取是否发送成功
     *
     * @return send_success - 是否发送成功
     */
    public String getSendSuccess() {
        return sendSuccess;
    }

    /**
     * 设置是否发送成功
     *
     * @param sendSuccess 是否发送成功
     */
    public void setSendSuccess(String sendSuccess) {
        this.sendSuccess = sendSuccess;
    }

    @Override
    public String toString() {
        return "TEmailSend{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", receivers='" + receivers + '\'' +
                ", cc='" + cc + '\'' +
                ", addattachment='" + addattachment + '\'' +
                ", sendUser='" + sendUser + '\'' +
                ", createdDate=" + createdDate +
                ", sendSuccess='" + sendSuccess + '\'' +
                '}';
    }
}