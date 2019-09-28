# 小说爬虫及后台

## 新版目标：

后台管理系统


### 已完成新功能：

1. 数据库结构优化
2. 爬虫优化，可自定义爬取的网站
3. 每天自动更新未连载完的小说
4. 使用缓存加快查询效率
5. 爬虫板块新增用户认证功能、只有管理员才能使用爬虫爬取新书

## api文档

### 响应体基本格式
```json
{
    code: 200,
    message: "success",
    data: XXX
}
```

接口url | 请求方法 | 权限 | 参数 | 说明
-|-|-|-|-
/book | get | - | - | 获取所有小说
/spider | get | - | url, matchRexId | 爬取一章（不保存）
/find | get | - | id | 获取指定的一本小说
/find | post | - | name | 获取所有符合“作者名或书名含有参数”的小说
/chapter| get | - | bookId | 获取指定小说的章节目录
/content| get | - | id | 根据contentid获取指定章节的内容
/admin/book | post| admin | bookName, authorName, url, matchRexId | 爬取小说
/admin/book | delete | admin | id | 删除小说
/admin/rex | get | admin | - | 获取所有匹配规则
/admin/rex | post | admin | matchRex对象内的属性 | 新增或修改匹配规则
/admin/rex | delete | admin | id | 删除匹配规则
/root/register | post | root | username, password | 注册用户
/root/user | put | root | userId, username, password | 更改用户信息，用户名、密码可以为空若为空则不修改
/root/role | get | root |  - | 查询所有权限角色
/root/role | post | root | name | 新增角色
/root/role | put | root |  userId, roleId | 为指定用户增加角色
