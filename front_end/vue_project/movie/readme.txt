技术架构：
前端：vue
后端：nodejs

项目来源：
《Vue.js项目开发实战》_张帆


数据库：
users:后端接口生成/users/register
movies:
db.movies.insert(
{
    'movieName': 'movieName1',
    'movieImg': 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580576139691&di=ac6924d16020a43f670240c3eb21431c&imgtype=0&src=http%3A%2F%2Fimg2.fengniao.com%2Fproduct%2F143%2F295%2Fce9AdwhxPVa6.jpg',
    'movieVideo': '',
    'movieDownload': '',
    'movieTime': '2019',
    'movieNumSuppose': 1,
    'movieNumDownload': 1,
    'movieMainPage': true,
}
);
comments:
db.comments.insert(
{
    'movie_id':'5e358de6f1da62fcb426bd9e',
    'username': 'name1',
    'context': 'good1',
    'check':true
}
);
recommends
db.recommends.insert(
{
    'recommendImg':'',
    'recommendSrc':'recommendSrc',
    'recommendTitle':'recommendTitle'
}
);
mails:
{
    fromUser: String,
    toUser: String,
    title: String,
    context: String
}
articles:
db.recommends.insert(
{
    articleTitle:String,
    articleContext:String,
    articleTime:String
}
);