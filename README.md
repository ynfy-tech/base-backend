# Before You Start

Open source project, any bright idea would be welcomed.  
开源项目, 欢迎您加入到本项目

We reserves the right to ensure accountability of any commercial use without our authorization.  
任何非授权的商用行为, 我们保留追责的权利

# Introduce

本项目致力于实现一个简易、轻量的开发框架, 方便您能够快速开发任意一个 java 后端单体应用。

本项目在架构设计上, 力求实现高效、简洁、低耦合、高聚合, 易于拆分和重组。所有与核心业务无关的拓展模块, 均以 backend-tool-xxx 命名, 与核心代码隔离, 灵活集成。

本架构包含以下功能:

+ 基础框架 Spring Boot
  + 本地文件路径映射
  + 自定义拦截 - 重复提交验证
  + 数据序列化配置
  + 跨域问题处理
  + 上下文丢失问题处理
  + XSS 攻击防范
  + 全局异常捕获
  + 配置文件动态加载(@profiles.active@)
+ 线程池
  + 基础线程池配置
  + 定长线程池配置
  + 异步线程池配置
+ 缓存 Redis
  + 初始化配置
  + 数据序列化配置
  + 基于 Redis 的 Spring Cache 实现
+ Restful API - Spring-Doc Open API 3.0
  + 初始化配置
  + API 分组
+ 多数据源 Druid
+ 持久层 Mybatis Plus
  + 初始化配置
  + 自动注入 createdBy, createdAt, updatedBy, updatedAt
  + 基础 PO 定义(雪花id, createdBy, createdAt, updatedBy, updatedAt)
+ 登录
  + 密码验证: MD5 + 随机盐
  + 登录状态: JWT + Redis
  + Token 续约
+ 权限分配
  + 基于 RBAC 实现资源的权限分配
+ 文件管理
+ 数据字典
  + 初始化
  + 注解
  + 性能优化
+ 日志 Logback
  + 初始化配置
  + 控制台输出彩色日志
  + 轻量日志框架 plumelog
+ 代码生成工具
+ 常用工具类
  + SpringUtil: 封装常用的 Spring Context 信息
  + RedisUtil: 封装常用的 Redis 操作
  + HttpUtil: 封装常用的 HttpUtil 操作
+ 其他工具推荐
  + [weapp-taro-templates: 本项目的前端代码, 配套使用更香哦](https://github.com/ynfy-tech/weapp-taro-templates)
  + [ynfy-tool-easypoi: 增强型 Poi , 在 EasyPoi 的基础上, 实现多 sheet 和复杂样式的 excel 导出, 适用于各种复杂场景](https://github.com/ynfy-tech/ynfy-tool-easypoi)
  + [ynfy-tool-httpconnect: 基于 HttpURLConnection 实现的 HTTP 工具, 用于后端自动化测试](https://github.com/ynfy-tech/ynfy-tool-httpconnect)
  + ynfy-tool-pay: 微信支付/支付宝支付工具包, 抽象实现支付功能和回调, 便于灵活调用
  + ynfy-tool-encrypt: 常用的加解密工具包, 包含前后端常用的加解密工具类


# Tech Stack

# What Can We Do

# Tutorials
