# Springboot-start
##  实现功能
+  发送邮件
+  文件上传下载 当做ftp
+  多线程爬虫，记录本地表，可以断点重爬。
## 使用方式
+  运行sql/random_recommend.sql,创建表
+  修改application-druid.properties中连接串
+  按照other文件夹下emailConfig.txt模板创建application-emailConfig.properties，配置自己的邮箱
+  不建议修改包名，因为需要修改的地方有点多，想拿去直接用的修改下artifactId就行了
+  已经可以使用通用mapper生成mapper xml 使用maven命令较优
