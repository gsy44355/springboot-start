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

###PS:
我觉得这个爬虫开发的还不错，如果有兴趣可以下载下来试用一下，使用方式参照WeiboCrawlerServiceImpl.java，调用可以自己写controller调用，也可以直接在test里面调用，功能比较独立。需要配置resources/crawler目录下的properties作为header，以及自行定制爬虫具体实现。
crawlerBase 这个主要是提供Url的一个方法的封装。正常开发只需要专注于case的那一部分，通用模块都已经封装好了，包括Url获取，加锁，防重复等等。如有任何建议都可以评论告诉我，学习的路上需要他人的批评。