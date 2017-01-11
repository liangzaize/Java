# 《显卡佬》---JAVA服务端调试日志
[![Build Status](https://travis-ci.org/liangzaize/Java.svg?branch=master)](https://travis-ci.org/liangzaize/Java)
![Intellij IDEA 2016.3.2](https://img.shields.io/badge/Intellij IDEA-2016.3.2-orange.svg?style=flat)
![Tomcat 9.0.0.M15](https://img.shields.io/badge/Tomcat-9.0.0.M15-blue.svg?style=flat)
![email lichhero620@gmail.com](https://img.shields.io/badge/email-lichhero620@gmail.com-yellow.svg?style=flat)
####程序介绍
-《显卡佬》主要的服务领域在计算机硬件，本软件集成了包括各类电脑硬件的参数、电脑硬件方面新闻、用户论坛等功能，用户可以在程序中看到所有电脑硬件比如显卡、CPU等的参数，并且可以浏览最新的与电脑有关的新闻。
[跳至ios客户端仓库](https://github.com/liangzaize/IOS)
---
###11/Jan/2017
与客户端的新闻功能进行对接，新闻的正文使用jsp实现，利用标题和摘要进行查找对应的页面，返回给客户端的UIWebview
###6/Jan/2017
新增了更改头像图片的功能，图片会以base64的数据形式传过来，然后根据用户的名字创建一个同名的txt文件来存放，数据库中photo存放路径。~~重新定义了session~~，使其能够正确维持用户的访问，根据sessionid恢复对应的session对象。
###5/Jan/2017
完善了登录功能，并且使用了session来管理持久性连接
###3/Jan/2017
单纯增加了一些显卡的数据库，觉得这样每一次发送请求再去找数据效率太低了，占用了大量的时间，打算利用爬虫的原理隔一段时间自动配对好zol中个硬件的参数，然后存放在数据库中，用户要数据的时候直接拿数据库中的就可以了，不用每一次都要发出一次的网络请求，这个问题留待完成所有基本功能的时候再开始做
###2/Jan/2017
完善了抓取网页功能代码，并可以传送给客户端
