## 小说下载器

 
用于下载部分不给下载链接的小说网站的小说

### 使用方法

- 修改**resources**下的**source-sample.json**为**source.json**
- 以下为各字段含义
```json
[
    {
        "firstUrl":"http://wap.nuancaiwenxue.com/book/5/5769/5076107.html",//小说的第一章的链接
        "file": "/home/ink/all/娱乐/novel/黑科技研发中心.txt",//保存的文件路径
        "downloader": "wep.nuancaiwenxue"//对应的下载器，规则为域名去掉后缀
    }
]
```


发现一款[app](https://github.com/gedoor/legado)更方便
