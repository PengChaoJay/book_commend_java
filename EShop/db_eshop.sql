/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50137
Source Host           : localhost:3306
Source Database       : db_eshop

Target Server Type    : MYSQL
Target Server Version : 50137
File Encoding         : 65001

Date: 2013-11-29 17:29:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `AdminType` int(4) DEFAULT NULL,
  `AdminName` char(12) DEFAULT NULL,
  `LoginName` char(12) DEFAULT NULL,
  `LoginPwd` char(12) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '1', '商品管理员', 'admin1', 'admin1');
INSERT INTO `admin` VALUES ('2', '2', '订单管理员', 'admin2', 'admin2');
INSERT INTO `admin` VALUES ('3', '3', '会员管理员', 'admin3', 'admin3');
INSERT INTO `admin` VALUES ('4', '4', '系统管理员', 'admin', 'admin');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Money` decimal(9,2) DEFAULT NULL,
  `CartStatus` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('2', '8', '318.50', '1');
INSERT INTO `cart` VALUES ('3', '3', '353.35', '1');
INSERT INTO `cart` VALUES ('4', '3', '120.00', '0');
INSERT INTO `cart` VALUES ('5', '1', '160.00', '1');
INSERT INTO `cart` VALUES ('6', '2', '45.00', '1');
INSERT INTO `cart` VALUES ('7', '1', '756.00', '1');
INSERT INTO `cart` VALUES ('8', '1', '56.00', '0');

-- ----------------------------
-- Table structure for cartselectedmer
-- ----------------------------
DROP TABLE IF EXISTS `cartselectedmer`;
CREATE TABLE `cartselectedmer` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Cart` int(4) NOT NULL,
  `Merchandise` int(4) NOT NULL,
  `Number` int(4) NOT NULL DEFAULT '1',
  `Price` decimal(8,2) NOT NULL DEFAULT '0.00',
  `Money` decimal(9,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of cartselectedmer
-- ----------------------------
INSERT INTO `cartselectedmer` VALUES ('3', '2', '12', '3', '45.00', '135.00');
INSERT INTO `cartselectedmer` VALUES ('5', '2', '8', '2', '33.00', '66.00');
INSERT INTO `cartselectedmer` VALUES ('6', '2', '5', '1', '42.50', '42.50');
INSERT INTO `cartselectedmer` VALUES ('7', '2', '7', '1', '54.00', '54.00');
INSERT INTO `cartselectedmer` VALUES ('8', '3', '11', '1', '45.00', '45.00');
INSERT INTO `cartselectedmer` VALUES ('9', '3', '4', '2', '41.80', '83.60');
INSERT INTO `cartselectedmer` VALUES ('10', '3', '10', '3', '71.25', '213.75');
INSERT INTO `cartselectedmer` VALUES ('14', '4', '1', '1', '34.00', '34.00');
INSERT INTO `cartselectedmer` VALUES ('16', '4', '3', '2', '43.00', '86.00');
INSERT INTO `cartselectedmer` VALUES ('17', '5', '15', '1', '50.00', '50.00');
INSERT INTO `cartselectedmer` VALUES ('18', '5', '2', '2', '52.00', '104.00');
INSERT INTO `cartselectedmer` VALUES ('19', '6', '1', '1', '34.00', '34.00');
INSERT INTO `cartselectedmer` VALUES ('20', '7', '15', '15', '50.00', '750.00');
INSERT INTO `cartselectedmer` VALUES ('21', '8', '15', '1', '50.00', '50.00');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `CateName` char(40) DEFAULT NULL,
  `CateDesc` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '计算机类', '计算机相关的各类书籍');
INSERT INTO `category` VALUES ('2', '管理类', '管理相关的各类书籍');
INSERT INTO `category` VALUES ('3', '英语类', '英语相关的各类书籍');
INSERT INTO `category` VALUES ('4', '小说类', '各类小说');

-- ----------------------------
-- Table structure for leaveword
-- ----------------------------
DROP TABLE IF EXISTS `leaveword`;
CREATE TABLE `leaveword` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Admin` int(4) DEFAULT NULL,
  `Title` char(60) DEFAULT NULL,
  `Content` text,
  `LeaveDate` datetime DEFAULT NULL,
  `AnswerContent` text,
  `AnswerDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of leaveword
-- ----------------------------
INSERT INTO `leaveword` VALUES ('1', '2', null, '非常感谢', '非常感谢，很方便！', '2009-04-20 21:14:44', null, null);
INSERT INTO `leaveword` VALUES ('2', '2', null, '不错', '不错的电子商务网站！', '2009-04-20 21:15:40', null, null);
INSERT INTO `leaveword` VALUES ('3', '3', null, '支持', '支持电子商务！', '2009-04-20 22:38:58', null, null);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Memberlevel` int(4) NOT NULL,
  `LoginName` char(12) DEFAULT NULL,
  `LoginPwd` char(12) DEFAULT NULL,
  `MemberName` char(20) DEFAULT NULL,
  `Phone` char(15) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Zip` char(10) DEFAULT NULL,
  `RegDate` datetime DEFAULT NULL,
  `LastDate` datetime DEFAULT NULL,
  `LoginTimes` int(4) DEFAULT NULL,
  `EMail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', '4', 'test', 'test', '刘桥', '13971559323', '湖北省武汉市洪山区', '432200', '2009-04-20 18:40:30', '2013-11-29 16:51:50', '8', 'liuqiao1982@sina.com');
INSERT INTO `member` VALUES ('2', '1', 'jinwu', 'jinwu', '金武', '13456754323', '广东省深圳市', '435567', '2009-04-20 21:05:58', '2009-04-21 15:33:41', '2', 'jinwu@sina.com');
INSERT INTO `member` VALUES ('3', '1', 'hujie', 'hujie', '胡杰', '13456789067', '武汉市洪山区鲁磨路', '432254', '2009-04-20 21:17:08', '2009-04-21 15:32:36', '3', 'hujie@sohu.com');
INSERT INTO `member` VALUES ('8', '3', 'chenlin', 'chenlin', '陈林', '13456756789', '湖北省武汉市新洲区', '432543', '2009-04-20 22:44:30', '2009-04-21 15:33:08', '2', 'chenlin@sohu.com');
INSERT INTO `member` VALUES ('9', '1', 'ggg', 'ggg', '该干', '66771111111', 'ggg', 'ggg', '2009-04-24 15:44:50', '2009-04-24 15:44:50', '0', 'gg@ff.cn');
INSERT INTO `member` VALUES ('10', '1', 'ggg1', '111', '该干', '66771111111', 'ggg', 'ggg', '2009-04-24 16:11:16', '2009-04-24 16:11:16', '0', 'gg@ff.cn');

-- ----------------------------
-- Table structure for memberlevel
-- ----------------------------
DROP TABLE IF EXISTS `memberlevel`;
CREATE TABLE `memberlevel` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `LevelName` char(20) DEFAULT NULL,
  `Favourable` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of memberlevel
-- ----------------------------
INSERT INTO `memberlevel` VALUES ('1', '普通会员', '95');
INSERT INTO `memberlevel` VALUES ('2', '黄金会员', '90');
INSERT INTO `memberlevel` VALUES ('3', '白金会员', '85');
INSERT INTO `memberlevel` VALUES ('4', '钻石会员', '80');

-- ----------------------------
-- Table structure for merchandise
-- ----------------------------
DROP TABLE IF EXISTS `merchandise`;
CREATE TABLE `merchandise` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Category` int(4) NOT NULL,
  `MerName` char(40) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `SPrice` decimal(8,2) DEFAULT NULL,
  `MerModel` char(40) DEFAULT NULL,
  `Picture` varchar(100) DEFAULT NULL,
  `MerDesc` text,
  `Manufacturer` char(60) DEFAULT NULL,
  `LeaveFactoryDate` datetime DEFAULT NULL,
  `Special` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of merchandise
-- ----------------------------
INSERT INTO `merchandise` VALUES ('1', '1', 'JavaScript 高级程序设计', '45.00', '34.00', 'c1', '/Picture/9211839-1_b.jpg', 'JavaScript是目前Web客户端开发的主要编程语言，也是Ajax的核心技术之一。本书从最早期Netscape浏览器中的JavaScript开始讲起，直到当前它对XML和Web服务的具体支持，内容主要涉及JavaScript的语言特点、JavaScript与浏览器的交互、更高级的JavaScript技巧，以及与在Web应用程序中部署JavaScript解决方案有关的问题，如错误处理、调试、安全性、优化/混淆化、XML和Web服务，最后介绍应用所有这些知识来创建动态用户界面。', '人民邮电出版社', '2009-04-21 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('2', '1', 'Ajax实战', '65.00', '0.00', 'c2', '/Picture/9161326-1_b.jpg', '本书是目前Ajax领域最为全面深入的一本著作，其中不仅有对于基础知识的介绍，还有对于Ajax开发中重大的体系架构问题的深入探讨，总结了大量Ajax开发中的设计模式，并讨论了框架、安全性与性能等等。书中提供了几个典型的例子，兼顾各种开发平台，这些例子的代码稍作修改就可以直接应用于项目开发之中，代码源文件可以从图灵网站下载。\r\n本书内容广泛且深入，同时适用于各个层次的Web应用开发人员。', '人民邮电出版社', '2009-04-21 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('3', '1', 'CSS基础教程', '54.00', '43.00', 'c3', '/Picture/9211839-1_b.jpg', '本书是优秀的CSS 入门书，重点讲述了如何使用CSS 实现基于Web 标准的网站开发，实现网站内容和表现相分离。\r\n　　本书包括两部分。第一部分介绍了CSS 的基础知识和基本概念，再利用CSS 分别对网页创建中的一些基本元素加上样式，包括：文本、图像、列表、链接、表格、表单等。第二部分主要讨论了基于CSS 来实现网页基本布局的相关概念与技术，包括浮动、流体布局等。本书最后还给出了一个真实的案例，将本书的所有内容进行了综合讨论。\r\n　　本书适合于各层次Web 开发人员、设计人员和测试人员学习参考。', '机械工业出版社', '2002-04-09 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('4', '1', '精通CSS：高级Web标准解决方案', '44.00', '33.00', 'c3', '/Picture/9221944-1_b.jpg', '本书将最有用的CSS技术汇总在一起，在介绍基本的CSS概念和最佳实践之后，讨论了核心的CSS技术，例如图像、链接、列表操纵、表单设计、数据表格设计以及纯CSS布局。每一章内容由浅入深，直到建立比较复杂的示例。之后本书用两章讨论招数、过滤器、bug和bug修复，最后由Simon Collison和Cameron Moll两位杰出的CSS设计人员，将书中讨论的许多技术组合起来，给出了两个实例研究。本书还集中介绍了现实的浏览器问题，是弥补CSS知识欠缺不可或缺的参考书。\r\n本书适合具有(X)HTML和CSS基本知识的任何网页设计人员阅读。', '机械工业出版社', '2004-04-15 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('5', '1', '无懈可击的Web设计:利用XHTML和CSS提高网站的灵活性与适应性', '50.00', '0.00', 'c5', '/Picture/9232841-1_b.jpg', '有大量的传统table布局和现在的div布局进行比较。', '科学出版社', '2009-04-15 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('6', '3', '新编考研英语读真题记单词（2010年版）', '65.00', '0.00', 'e1', '/Picture/20511678-1_b.jpg', '本书对历年真题选项中的疑难词语也进行了逐一分析，并且根据大纲要求进行了注解，旨在保障考生在考试时不要输在题干和选项上，这就为考生彻底扫清了考试中的障碍词语。', '机械工业出版社', '2000-05-10 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('7', '3', '奥巴马演说集', '65.00', '54.00', 'e2', '/Picture/20525298-1_b.jpg', '本书中共收录奥巴马激情演说十四篇（包含就职演说），每篇演说后都附有详尽的背景注释与难词解析，是了解美国历史与现状、了解奥巴马心路历程的绝佳资料，也是学习英文及演讲技巧的最好范例。\r\n　　从夏威夷到印度尼西亚，从印度尼西亚到芝加哥，从芝加哥到白宫，巴拉克?奥巴马演绎了美国政治史上一段最不可思议、最令人着迷的传奇。自他决定参选之日起，权力之路就已经向他展开。他要用激情、智慧和口才去征服和感动全美国人民。作为一位非裔美国人、一位参议员、一位律师、一位大学讲师、一位父亲、一位天主教徒、一位持异见的政客，奥巴马在演说中叙述了个人的成长经历、价值取向、对伊拉克战争的态度，并就种族、信仰、社区、教育、税收等政治和社会方面的诸多问题提出了多项改革方案。其演讲逻辑缜密，激情澎湃，震撼人心。', '科学出版社', '2006-04-12 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('8', '4', '藏地密码6', '45.00', '33.00', 'n1', '/Picture/20516672-1_b.jpg', '从来没有一本小说，能像《藏地密码》这样，奇迹般地赢得专家、学者、名人、书店、媒体、全球最知名的出版机构以及成千上万普通读者的狂热追捧，《藏地密码》是当下中国数千万“西藏迷”了解西藏的首选读本，也是当下最畅销的华语小说，目前销量已达到惊人的200多万册。', '科学出版社', '2006-04-12 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('9', '4', '身份的证明', '55.00', '0.00', 'n2', '/Picture/20373098-1_b.jpg', '本书超越了一般意义的反特故事，它将跨度拉长到近半个世纪，把5个主要人物的命运由每个历史时期的案件串联起来，故事的终极意义在于通过人物过去的付出和现时的遭遇，再现国家发展过程中那些默默无闻的幕后英雄精神不屈的本质。\r\n全书讲述的是一个我党地下情报人员跨越47年的人生经历，书中通过几个特殊历史时期的变迁，用全新的视角再现了一个隐蔽战线上“用特殊材料制成的”共产党人，并通过一个人身份的证明，折射了共和国从新生走向成熟的历史光影。', '文艺出版社', '2009-04-08 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('10', '2', '巴菲特教你读财报', '75.00', '0.00', 'm4', '/Picture/20522936-1_b.jpg', '金融传奇天才巴菲特的前儿媳玛丽?巴菲特与研究巴菲特法则的权威人士戴维?克拉克探秘沃伦?巴菲特的思想精髓，在本书中深入浅出地介绍巴菲特分析公司财务报表的黄金法则。书中以巴菲特的投资实例告诉你：\r\n　　巴菲特在分析损益表和资产负债表时那些久经考验的黄金法则，巴菲特用以寻找具有持续性竞争优势公司的财务指标――据此，他可以获得丰厚的长期投资回报，巴菲特如何使用财务报表去评估一家公司的价值，巴菲特对哪类股价便宜的公司避而远之。\r\n　　毋庸置疑，巴菲特诠释公司报表的简单财务指标会帮你识别出在明天的商战中，哪些公司会大获全胜，哪些公司会惨败而终。学会像巴菲特一样分析公司财报，你一定能在股市中收获累累硕果！', '文艺出版社', '2002-03-20 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('11', '4', '货币战争', '56.00', '45.00', 'm9', '/Picture/9304959-1_b.jpg', '为什么你不知道美联储是私有的中央银行？\r\n为什么华尔街风险资本会选中希特勒作为“投资”对象？\r\n为什么美国总统遇刺的比例高于美军诺曼底登陆一线部队的伤亡率？\r\n自1694年英格兰银行成立以来的３００年间，几乎每一场世界重大变故背后，都能看到国际金融资本势力的身影。他们通过左右一国的经济命脉掌握国家的政治命运，通过煽动政治事件、诱发经济危机，控制着世界财富的流向与分配。可以说，一部世界金融史，就是一部谋求主宰人类财富的阴谋史。\r\n通过描摹国际金融集团及其代言人在世界金融史上翻云覆雨的过程，本书揭示了对金钱的角逐如何主导着西方历史的发展与国家财富的分配，通过再现统治世界的精英俱乐部在政治与经济领域不断掀起金融战役的手段与结果，本书旨在告诫人们警惕潜在的金融打击，为迎接一场“不流血”的战争做好准备。\r\n随着中国金融的全面开放，国际银行家将大举深入中国的金融腹地。昨天发生在西方的故事，今天会在中国重演吗？', '清华大学出版社', '2005-03-16 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('12', '4', '贫民窟的百万富翁', '66.00', '45.00', 'n6', '/Picture/20525341-1_b.jpg', '十八岁的酒吧服务员罗摩，生活在孟买的贫民窟里。他参加了一个名为《谁将赢得十个亿》的电视知识问答竞赛，竟然奇迹般地连续答对了十二个问题，一举赢得最高累积奖金――十亿卢比。罗摩是个穷困的孤儿，从未上过学，甚至从不读报，他又怎能知道这十二个有关天文，宗教、历史、体育、文学等问题的答案呢？警察以涉嫌作弊为由拘捕了罗摩，并严刑逼供。一个神秘女律师的出现解救了他，随着女律师的调查，罗摩充满悬念、挑战与苦难的人生旅程一一上演，印度社会各个层面的人物与生活也随之铺陈开来……', '文艺出版社', '2009-04-09 00:00:00', '1');
INSERT INTO `merchandise` VALUES ('13', '1', '士兵突击', '36.00', '0.00', 'n5', '/Picture/9255064-1_b.jpg', '这真的不是一部小说，它是哲学、是人生，是我们成长的历史。每一位读者都能在许三多身上找到自己的一些影子。许多三像是两个人，可根子里的他还是让你佩服、让你回味，甚至是他的“傻”，也足以让你去喜欢。你会觉得：“一本好书，能教会你怎样做人！”\r\n　　金戈铁马，斗志男儿，士兵的精神世界丰富和冷峻；一个有着性格缺点的普通农村孩子，他单纯而执着，在军人的世界里跌打滚爬。因为他的笨，让全人受累；因为他的认真，让全连队为之感动；因为他的执着，让全营战士为之骄傲。\r\n　　虽然他的家乡祖屋在爆炸声中变成一堆瓦砾，却无法阻止他坚毅的军人步伐；善良的怜悯，并未使他忘记军人的职责，枪杀毒犯……他在种种困厄和磨难中百炼成钢。\r\n　　他的名字叫――许三多。', '文艺出版社', '2009-04-09 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('14', '2', '领导力是什么', '66.00', '0.00', 'm5', '/Picture/9250656-1_b.jpg', '《领导力是什么》汇集了众多新文章，集中讨论如何领导变革、如何处理复杂局面和不确定因素、如何建立学习型组织、如何促进创新以及在当今国际性组织中管理知识型员工必备的新技能等问题。\r\n本书的撰稿者都是当今世界最受尊重的商业思想家和顾问中的佼佼者。他们涉猎的领域广泛深入，为营销和研发提供了新的领导方法，为促进下一代领导力的发展提供了新的技巧，为危机管理提供了新的领导方法，也为打造在竞争中获胜的灵活组织提供了新的路径。本书如同一本代表专业技术与智慧的名副其实的名人录，为我们描画了一幅21世纪的领导力蓝图。', '文艺出版社', '2009-04-09 00:00:00', '0');
INSERT INTO `merchandise` VALUES ('15', '2', '管理是什么', '56.00', '50.00', 'm8', '/Picture/8768781-1_b.jpg', '本书是过去１０年管理思想革新的简洁实用的总结。书中对各种新的管理理念做了详尽的介绍，并对如何将之运用到复杂的日常组织中进行了阐述。书中建议管理者从旧有的控制性思维中转变到弹性的、重视网络关系、充满活力并能持续学习的思维中。\r\n新时代的竞争形势对管理者提出了新的挑战，企业的学习潜力、反应速度和适应能力取代了传统的资产规模、市场份额和资产收益率指标。以客户为中心不再只是营销部门的主导思想，它现在主宰整个企业。\r\n本书不是要你抛弃传统的管理实践，它要做的是带领你在纷纭的商业环境中寻找最有效的管理思想，以实现企业绩效的重大突破。\r\n作者在欧洲和美国的成功管理实践保证了本书与实际管理生活的密切接近，为管理者们提供了现实的操作指南。', '商务出版社', '2009-01-14 00:00:00', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `Member` int(4) NOT NULL,
  `Cart` int(4) NOT NULL,
  `OrderNO` char(20) DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `OrderStatus` int(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2', '8', '2', '1240296879656', '2009-04-21 14:54:39', '1');
INSERT INTO `orders` VALUES ('3', '3', '3', '1240297000546', '2009-04-21 14:56:40', '2');
INSERT INTO `orders` VALUES ('4', '1', '5', '1240299012625', '2009-04-21 15:30:12', '3');
INSERT INTO `orders` VALUES ('5', '2', '6', '1240299319328', '2009-04-21 15:35:19', '1');
INSERT INTO `orders` VALUES ('6', '1', '7', '1385715129796', '2013-11-29 16:52:09', '1');
