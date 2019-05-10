CREATE TABLE EMAIL_TABLE(
		ID INT(30) PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
		SUBJECT VARCHAR(50) COMMENT '邮件主题',
		RECEIVER VARCHAR(2000) COMMENT '收件人',
		CC VARCHAR(2000) COMMENT '抄送人',
		ADDATTACHMENT VARCHAR(500) COMMENT '附件列表',
		SEND_USER   VARCHAR(30)  COMMENT '发送人',
		CREATED_DATE DATE  COMMENT '创建时间',
	)COMMENT = '邮件发送表';