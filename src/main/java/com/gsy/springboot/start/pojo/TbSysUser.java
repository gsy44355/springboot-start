package com.gsy.springboot.start.pojo;

import javax.persistence.*;

@Table(name = "tb_sys_user")
public class TbSysUser {
    /**
     * 唯一ID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * shiro盐值
     */
    @Column(name = "user_salt")
    private String userSalt;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 获取唯一ID
     *
     * @return id - 唯一ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一ID
     *
     * @param id 唯一ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取密码
     *
     * @return user_password - 密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置密码
     *
     * @param userPassword 密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取shiro盐值
     *
     * @return user_salt - shiro盐值
     */
    public String getUserSalt() {
        return userSalt;
    }

    /**
     * 设置shiro盐值
     *
     * @param userSalt shiro盐值
     */
    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    /**
     * 获取用户邮箱
     *
     * @return user_email - 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     *
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取昵称
     *
     * @return user_nickname - 昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置昵称
     *
     * @param userNickname 昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}