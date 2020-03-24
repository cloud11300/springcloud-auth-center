# snapshot
#cheryev-auth-center
微服务框架认证授权中心

项目采用spring cloud、oauth2、spring security

# 依赖环境
JDK8、 Maven、 Mysql、 Redis 、nacos


注册中心采用阿里巴巴 nacos

缓存使用的是redis

oauth2数据存储在数据库中

username: test  password: 123456
phone: 13100000000 verifyCode: 1000

//数据库使用的是mysql5.7.23
执行sql语句

修改配置数据库密码

//数据库密码使用druid加密 *表示密码
java -cp druid-1.1.10.jar com.alibaba.druid.filter.config.ConfigTools ******

//nacos安装请查看官网资料


授权码：
http://localhost:9001/auth/oauth/authorize?response_type=code&client_id=test&redirect_uri=https://www.ddd.com

密码模式：
post
http://localhost:9001/auth/oauth/token?grant_type=password&client_id=test&client_secret=123456&username=test&password=123456

客户端：
post
http://localhost:9001/auth/oauth/token?grant_type=client_credentials&client_id=test&client_secret=123456


//token非对称加密
keytool -genkeypair -alias mytest -keyalg RSA -keypass mypass -keystore keystore.jks -storepass mypass





