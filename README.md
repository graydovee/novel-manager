# 小说爬虫及后台

### 新特性：

7. 阅读量统计
2. 小说封面、介绍

## api文档

### 响应体基本格式
```json
{
    "code": 200,
    "message": "success",
    "data": {}
}
```
## 接口

### 小说封面地址

* /cover/{小说id}

### 获取小说
* URL: /book
* METHOD: GET
* 参数：

| 参数名 | 类型 | 描述|
|-|-|-|
| name | String | 搜索参数，可省略，省略为查询全部 |
| index | Number | 页数（从0开始） |
| size | Number | 每页显示数据数 |

* example

```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "content": [
            {
                "id": 1,
                "name": "小说名",
                "author": {
                    "id": 2,
                    "name": "作者名"
                },
                "type": [ ],
                "firstChapter": 3,
                "createTime": "2019-10-03 21:08:36"
            }
        ],
        "totalPages": 100,
        "totalElements": 100,
        "numberOfElements": 1,
        "size": 1,
        "number": 0
    }
}
```



### 获取指定ID的一本小说

* URL: /find
* METHOD: GET
* 参数：

| 参数名 | 类型   | 描述            |
| ------ | ------ | --------------- |
| id     | Number | 小说ID          |
| index  | Number | 页数（从0开始） |
| size   | Number | 每页显示数据数  |

* example

```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "id": 1,
        "name": "小说名",
        "author": {
            "id": 2,
            "name": "作者名"
        },
        "type": [ ],
        "firstChapter": 3,
        "introduce": "小说介绍",
        "createTime": "2019-10-03 21:08:36"
    }
}
```

### 通过书的ID获取章节信息

* URL: /chapter
* METHOD: GET
* 参数：

| 参数名    | 类型   | 描述            |
| --------- | ------ | --------------- |
| chapterId | Number | 章节ID          |
| index     | Number | 页数（从0开始） |
| size      | Number | 每页显示数据数  |

* example

```json
{
    "code": 200,
    "message": "OK",
    "data": {
    	"content": [
            {
                "id": 8,
                "bookId": 6,
                "nextChapterId": 11,
                "contentId": 7,
                "title": "第1章 这个世界，有点不一样",
                "createTime": "2019-10-03 21:08:39"
        	}
        ],
        "totalPages": 100,
        "totalElements": 100,
        "numberOfElements": 1,
        "size": 1,
        "number": 0
    }
}
```



### 通过章节id获取小说正文

* URL: /content
* METHOD: GET
* 参数：

| 参数名 | 类型   | 描述   |
| ------ | ------ | ------ |
| id     | Number | 章节ID |

* example

```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "id": 3,
        "info": "这章小说具体的正文部分"
    }
}
```



### 全网搜索小说

* URL: /spider/search
* METHOD: POST
* 参数：

| 参数名 | 类型   | 描述         |
| ------ | ------ | ------------ |
| name   | String | 搜索的关键词 |

* example

```json
{
    "code": 200,
    "message": "OK",
    "data": [
        {
            "title": "书名1",
            "url": "小说在目标网站的URL1"
        },
        {
            "title": "书名2",
            "url": "小说在目标网站的URL2"
        }
    ]
}
```

### 全网搜索小说章节

* URL: /spider/search
* METHOD: POST
* 参数：

| 参数名 | 类型   | 描述                |
| ------ | ------ | ------------------- |
| url    | String | 小说在目标网站的URL |

* example

```json
{
    "code":200,
    "message":"OK",
    "data":[
        {
            "title":"小说标题1",
            "url":"小说内容在目标网站的URL1"
        },
        {
            "title":"小说标题2",
            "url":"小说内容在目标网站的URL2"
        }
    ]
}
```

### 全网搜索小说章节正文

* URL: /spider/content
* METHOD: POST
* 参数：

| 参数名     | 类型   | 描述                             |
| ---------- | ------ | -------------------------------- |
| url        | String | 小说内容在目标网站的URL          |
| matchRexId | Number | 爬取规则ID, 省略则使用第一个规则 |

* example

```json
{
    "code":200,
    "message":"OK",
    "data":{
        "title":"章节标题",
        "content":"  该章节小说的正文部分",
        "url":"下一章链接"
    }
}
```



## [后台管理系统](https://github.com/graydovee/vue-ebook-admin)
## [前端展示页面](https://github.com/graydovee/vue-ebook)