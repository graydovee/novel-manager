# 小说爬虫及后台

## 新版目标：



### 已完成新功能：

后台管理系统

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
