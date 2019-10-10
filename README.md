## 短信验证和邮箱验证

​		本项目为自用的验证服务，其中包含许多自定义变量需用户更改可自行修改代码。本服务包含3个依赖和2个开关，其核心配置文件存在于src/main/sources/中。

### 使用基本功能配置

​		首先需在**application-prod.properties**中配置`sms.rootSecret`，此为本项目的根密钥，需在每次请求过程中携带此密钥。

#### 1.使用短信服务

在**application-prod.properties**中将`app.smsService=true`,然后配置相关aliyun配置，本项目只实现了aliyun短信平台服务配置项为:

```properties
#阿里云的配置文件，包含自己账号的secret和模板代码，具体请参考aliyun短信服务调用
aliyun.AccessKeyID=
aliyun.AccessKeySecret=
aliyun.SignName=
aliyun.TemplateCode=
```

#### 2.使用邮箱服务

在**application-prod.properties**中将`app.emailService=true`,然后配置相关email配置，email采用thymeleaf模板发送，如需自定义模板请自己实现。使用email模板除了配置email常规配置外还需配置以下选项:

```properties
# 发送邮件发送者姓名，可自定义
sms.sendEmailName=
# 发送邮箱地址，填发送地址
sms.sendEmailAddr=
# 发送邮件主题，可自定义
sms.emailSubject=
# 项目名称，邮件模板中的title
sms.projectName=
```



### 使用依赖功能配置

#### 1. 使用redis动态配置权限验证

​		在**application-prod.properties**中将`app.usedRedis=true`,然后配置相关redis配置，如需配置redis连接池请在**application.properties**中修改相关变量。

​		redis的key在`top.mao196.sms.util.TokenUtil.REDIS_SECRET_LIST`中，默认为`SECRET`的list。在此list中添加能过通过认证的密钥便可实现动态权限认证。

#### 2. 使用activeMQ发送短信验证码和邮箱验证码

​		在**application-prod.properties**中将`app.usedMq=true`,然后配置相关activeMQ配置，如需配置activeMQ连接池请在**application.properties**中修改相关变量。

​		队列的名字在`top.mao196.sms.config.Consumer`中，默认为`email.queue`和`sms.queue`。在此队列中推送发送消息可异步实现发送验证码,其格式如下所示：

邮箱验证码格式

```json
{
   "email":"sendToEmail@example.com",
   "code": "513489"
}
```

短信验证码格式

```json
{
   "phone":"11111111111",
   "code": "513489"
}
```

#### 3.使用数据库持久化数据

​		在**application-prod.properties**中将`app.usedDatabase=true`,然后配置相关数据库配置，如需配置数据量连接池请在**application.properties**中修改相关变量，本项目只提供mysql初始化脚本，如果使用其他数据库请自己生成，mysql脚本存在于sql/shema.sql。

​		如果不使用数据库请将`spring.datasource.url=jdbc:h2:mem:testdb;`无法连接数据库会导致程序启动失败。

### 接口调用

​		所有接口调用需在请求头携带服务密钥，其具体的形式为：

```
Authorization：Bearer +（服务密钥）
```

如果需要多个密钥请参考配置redis动态权限验证

#### 1.短信发送接口

- url：http://localhost:7788/sms/sms

- method：POST

- formDate：

  | key   | 值类型 | 描述         |
  | ----- | ------ | ------------ |
  | phone | string | 手机号       |
  | code  | string | 4-10位的数字 |

  

#### 2.邮箱发送接口

- url：http://localhost:7788/sms/email

- method：POST

- formDate：

  | key   | 值类型 | 描述         |
  | ----- | ------ | ------------ |
  | email | string | 邮箱地址     |
  | code  | string | 4-10位的数字 |

  

#### 3.查询发送接口

- url：http://localhost:7788/sms/checkInfo

- method：GET

- formDate：

  | key      | 值类型 | 描述                       |
  | -------- | ------ | -------------------------- |
  | codeType | string | 查询类型（sms，或者email） |
  | value    | string | 查询的关键字               |

- return：

  | key      | 值类型 | 描述                   |
  | -------- | ------ | ---------------------- |
  | lastTime | int    | 最近一次验证码发送时间 |
  | resTime  | int    | 请求时间               |
  | code     | string | 验证码值               |
  | codeType | string | 同发送值               |
  | value    | string | 同发送值               |

  

