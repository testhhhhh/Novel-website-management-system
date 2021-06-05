# Novel-website-management-system
Java课程作业 Java swing 小说网站管理系统 
## 实现功能
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/系统功能结构图.jpg)
## 系统部分截图
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/1.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/2.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/3.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/4.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/5.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/6.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/7.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/8.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/9.png)
![](https://github.com/testhhhhh/Novel-website-management-system/raw/master/img/10.png)
## 数据库设计
使用的是MySQL数据库<br><br>
### transaction表<br>
名称	代码	数据类型	长度	主键	外来键<br>
用户ID	userid	int		FALSE	TRUE<br>
小说ID	novelid	int		FALSE	TRUE<br>
交易ID	transactionid	int		TRUE	FALSE<br>

### classify表<br>
名称	代码	数据类型	长度	主键	外来键<br>
分类ID	classifyid	int		TRUE	FALSE<br>
分类名称	classifyname	varchar(12)	12	FALSE	FALSE<br>

### novel表<br>
名称	代码	数据类型	长度	主键	外来键<br>
小说ID	novelid	int		TRUE	FALSE<br>
分类ID	classifyid	int		FALSE	TRUE<br>
小说名称	novelname	varchar(20)	20	FALSE	FALSE<br>
小说梗概	novelmain	varchar(500)	500	FALSE	FALSE<br>
小说作者	novelwriter	varchar(20)	20	FALSE	FALSE<br>
小说图片	novelpicture	longblob		FALSE	FALSE<br>
小说点券数	novelmoney	float(8,2)	8	FALSE	FALSE<br>

### user表<br>
名称	代码	数据类型	长度	主键	外来键<br>
用户ID	userid	int		TRUE	FALSE<br>
用户名	username	varchar(20)	20	FALSE	FALSE<br>
密码	password	varchar(20)	20	FALSE	FALSE<br>
姓名	name	varchar(10)	10	FALSE	FALSE<br>
性别	sex	char(3)	3	FALSE	FALSE<br>
电话	telephone	char(11)	11	FALSE	FALSE<br>
用户状态	state	int		FALSE	FALSE<br>
激活码	code	varchar(6)	6	FALSE	FALSE<br>

### chapter表<br>
名称	代码	数据类型	长度	主键	外来键<br>
小说ID	novelid	int		TRUE	TRUE<br>
章节ID	chapterid	int		TRUE	FALSE<br>
章节名称	chaptername	varchar(20)	20	FALSE	FALSE<br>
章节内容	chaptercontent	varchar(21844)	21844	FALSE	FALSE<br>

### comment表<br>
名称	代码	数据类型	长度	主键	外来键<br>
小说ID	novelid	int		TRUE	TRUE<br>
用户ID	userid	int		TRUE	TRUE<br>
评论ID	commentid	int		TRUE	FALSE<br>
评论内容	comments	varchar(200)	200	FALSE	FALSE<br>
