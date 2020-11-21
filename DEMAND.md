# 2.0版本重构

## 新版本计划：

1. 全面升级小说爬虫系统为小说管理系统
2. 使用微服务架构分离模块
3. 权限架构重新设计以满足新系统
4. 管理模块重新设计，新增读者、编辑、作家等角色
5. 使用mongoDB, redis等技术增运行效率

## 功能模块划分
- ndovel-api：接口模块
- ndovel-frame：框架模块，进行一些全局配置
- ndovel-gateway：网关模块，唯一对外提供服务接口
- ndovel-spider：爬虫模块
- ndovel-cn.graydove.server：服务器模块，执行业务逻辑