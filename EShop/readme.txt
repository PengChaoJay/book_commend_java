系统介绍：
jsp+JavaScript+struts+hibernate+css+mysql的网上书店（源码完整直接运行，高仿真数据库记录，身临其境网上购书吧！）
和国内最好的网上书店-------当当网的框架相同，包括顾客的购物车、订单管理和留言板，支持商品搜索；后台管理员的商品、
订单、会员、系统管理。界面优美，功能齐全，不可多得的优秀作品。

使用说明：

2、管理后台登陆地址：http://localhost:8080/EShop/Admin/adminLogin.jsp  
①登陆账号：admin，admin (系统管理员)
②登陆账号：admin1，admin1 (商品管理员)
③登陆账号：admin2，admin2 (订单管理员)
④登陆账号：admin3，admin3 (会员管理员)
备注：不能管理员对应的后台功能各不相同，不同管理员对应不同的权限。

3、系统前台登陆地址：http://localhost:8080/EShop，登陆测试账号：test，test

4、网站数据库名称:db_shop,数据库用户账号:root,密码为空。当然这个也可以在配置文件进行修改自己的账号匹配。
数据库配置文件路劲为：EShop/src/hibernate.config.xml

5、系统安装方法：
①将"db_eshop.sql"文件导入到mysql数据库中，导入方法为首先新建db_eshop这个数据库，然后运行"db_eshop.sql"文件即可。
   也可以新建查询，记事本方式打开db_eshop.sql这个文件，全选复制，新建查询中粘贴运行亦可。
②将EShop系统文件发布到tomcat服务器，并启动tomcat。
③在浏览器输入：http://localhost:8080/EShop，即可访问系统前台

备注：还有什么问题，可以联系管理员进行解决。管理员QQ：762757125，加QQ时说明：网上书店系统咨询。否则不予理睬。
