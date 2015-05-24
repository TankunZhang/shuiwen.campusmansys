/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : campusmandb

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2015-05-24 21:17:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` int(11) NOT NULL,
  `lastlogindate` varchar(255) default NULL,
  `registerdate` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin88', '1', '34235', '4534');
INSERT INTO `admin` VALUES ('2', 'Qikun', 'admin88', '1', null, null);
INSERT INTO `admin` VALUES ('3', '走着', '111111', '2', null, null);
INSERT INTO `admin` VALUES ('4', '好辣', '1234', '1', null, null);
INSERT INTO `admin` VALUES ('5', '伙计', '111111', '1', null, null);
INSERT INTO `admin` VALUES ('6', 'hkhg', '111111', '1', null, null);
INSERT INTO `admin` VALUES ('7', 'sfaf', '1111', '1', null, null);
INSERT INTO `admin` VALUES ('8', 'asfasf', '1111', '1', null, null);
INSERT INTO `admin` VALUES ('9', 'qqqq', '1111', '1', null, null);
INSERT INTO `admin` VALUES ('10', 'wwww', '1111', '1', null, null);
INSERT INTO `admin` VALUES ('11', 'rrrr', '1111', '1', null, null);

-- ----------------------------
-- Table structure for `dankeyouhui`
-- ----------------------------
DROP TABLE IF EXISTS `dankeyouhui`;
CREATE TABLE `dankeyouhui` (
  `id` int(11) NOT NULL auto_increment,
  `kemuid` int(11) NOT NULL,
  `xueqishu` int(11) NOT NULL,
  `keshi` int(11) default '0',
  `danjia` int(11) default '0',
  `zongjia` float default '0',
  `zhekoujia` float default '0',
  `zhekou` float NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dankeyouhui
-- ----------------------------
INSERT INTO `dankeyouhui` VALUES ('39', '23', '1', '18', '180', '11880', '11300', '95');
INSERT INTO `dankeyouhui` VALUES ('38', '23', '2', '18', '160', '11880', '8500', '98');
INSERT INTO `dankeyouhui` VALUES ('37', '23', '3', '18', '160', '11880', '5800', '100');
INSERT INTO `dankeyouhui` VALUES ('36', '23', '4', '18', '160', '11880', '2900', '100');
INSERT INTO `dankeyouhui` VALUES ('26', '24', '1', '17', '160', '2720', '2700', '100');
INSERT INTO `dankeyouhui` VALUES ('27', '24', '2', '17', '160', '5440', '5400', '100');
INSERT INTO `dankeyouhui` VALUES ('28', '24', '3', '17', '160', '8160', '8000', '98');
INSERT INTO `dankeyouhui` VALUES ('29', '24', '4', '17', '160', '10880', '10500', '96');
INSERT INTO `dankeyouhui` VALUES ('30', '24', '5', '17', '160', '13940', '13300', '95');
INSERT INTO `dankeyouhui` VALUES ('31', '24', '6', '17', '160', '16660', '15000', '90');

-- ----------------------------
-- Table structure for `duokeyouhui`
-- ----------------------------
DROP TABLE IF EXISTS `duokeyouhui`;
CREATE TABLE `duokeyouhui` (
  `id` int(11) NOT NULL auto_increment,
  `kemushu` int(11) NOT NULL default '0',
  `zhekou` float NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of duokeyouhui
-- ----------------------------

-- ----------------------------
-- Table structure for `guanliyuan`
-- ----------------------------
DROP TABLE IF EXISTS `guanliyuan`;
CREATE TABLE `guanliyuan` (
  `id` int(11) NOT NULL auto_increment,
  `gly_mingzi` varchar(255) default NULL,
  `mima` varchar(255) default NULL,
  `quanxian` int(11) default '0',
  `scdl_shijian` varchar(255) default NULL,
  `zc_shijian` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guanliyuan
-- ----------------------------
INSERT INTO `guanliyuan` VALUES ('1', 'admin', 'admin88', '1', null, null);

-- ----------------------------
-- Table structure for `jiaoshi`
-- ----------------------------
DROP TABLE IF EXISTS `jiaoshi`;
CREATE TABLE `jiaoshi` (
  `ID` int(11) NOT NULL,
  `xingming` varchar(255) default NULL,
  `zhiwu` varchar(255) default NULL,
  `shoukezhuanye` varchar(255) default NULL,
  `shoukebanji` varchar(255) default NULL,
  `keshibiaozhun` varchar(255) default NULL,
  `leixing` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jiaoshi
-- ----------------------------

-- ----------------------------
-- Table structure for `kecheng`
-- ----------------------------
DROP TABLE IF EXISTS `kecheng`;
CREATE TABLE `kecheng` (
  `id` int(11) NOT NULL auto_increment,
  `kemuid` int(11) NOT NULL default '0',
  `xueqi` varchar(255) default NULL,
  `bj_mingzi` varchar(255) default NULL,
  `sk_jiaoshi` varchar(255) default NULL,
  `sk_shijian` varchar(255) default NULL,
  `keshi` int(11) default '0',
  `kk_shijian` varchar(255) default NULL,
  `zhuangtai` varchar(255) default NULL,
  `zd_renshu` int(11) default '0',
  `dq_renshu` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kecheng
-- ----------------------------
INSERT INTO `kecheng` VALUES ('15', '23', '15B', '舞蹈15B1', '金某', '星期六', '18', '2015-09-20  13:00:00', '未开班', '30', '1');
INSERT INTO `kecheng` VALUES ('16', '23', '15B', '舞蹈15B2', '张某', '星期六', '18', '2015-09-12  13:00:00', '已开班', '27', '0');
INSERT INTO `kecheng` VALUES ('17', '24', '15B', '足球15B1', '王某', '星期六', '17', '2015-09-18  09:30:00', '已开班', '30', '2');

-- ----------------------------
-- Table structure for `kemu`
-- ----------------------------
DROP TABLE IF EXISTS `kemu`;
CREATE TABLE `kemu` (
  `id` int(11) NOT NULL auto_increment,
  `xiaoquid` int(11) NOT NULL,
  `km_mingzi` varchar(255) default NULL,
  `xueqizhi` int(11) default '0',
  `keshi` int(11) default '0',
  `danjia` float default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kemu
-- ----------------------------
INSERT INTO `kemu` VALUES ('23', '15', '舞蹈', '4', '18', '0');
INSERT INTO `kemu` VALUES ('24', '15', '足球', '6', '17', '0');

-- ----------------------------
-- Table structure for `kemucengci`
-- ----------------------------
DROP TABLE IF EXISTS `kemucengci`;
CREATE TABLE `kemucengci` (
  `id` int(11) NOT NULL auto_increment,
  `cc_mingzi` varchar(255) NOT NULL,
  `kemus` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kemucengci
-- ----------------------------

-- ----------------------------
-- Table structure for `xiaoqu`
-- ----------------------------
DROP TABLE IF EXISTS `xiaoqu`;
CREATE TABLE `xiaoqu` (
  `id` int(11) NOT NULL auto_increment,
  `xq_mingzi` varchar(255) default NULL,
  `suoxie` varchar(255) default NULL,
  `dizhi` varchar(255) default NULL,
  `dianhua` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoqu
-- ----------------------------
INSERT INTO `xiaoqu` VALUES ('1', '飞童凡响尚城校', '尚城', '松江区九亭镇涞寅路658弄39号绿庭尚城中心会所三楼', '021-67606466/18821200098');
INSERT INTO `xiaoqu` VALUES ('2', '飞童凡响浅水湾校', '浅水湾', '普陀区宜昌路179号浅水湾文化艺术中心五楼', '18821200600');
INSERT INTO `xiaoqu` VALUES ('3', '飞童凡响浅浦东碧云校', '碧云', '浦东新区碧云路333弄5号B102室中天碧云苑内', '18821200600');
INSERT INTO `xiaoqu` VALUES ('15', '临港新城', '海事', '海港大道', '');

-- ----------------------------
-- Table structure for `xuesheng`
-- ----------------------------
DROP TABLE IF EXISTS `xuesheng`;
CREATE TABLE `xuesheng` (
  `id` int(11) NOT NULL auto_increment,
  `xiaoquID` int(11) NOT NULL,
  `xingming` varchar(255) default NULL,
  `xingbie` varchar(255) default NULL,
  `cs_riqi` varchar(255) default NULL,
  `dizhi` varchar(255) default NULL,
  `gd_dianhua` varchar(255) default NULL,
  `mq_shouji` varchar(255) default NULL,
  `fq_shouji` varchar(255) default NULL,
  `youeryuan` varchar(255) default NULL,
  `zc_shijian` varchar(255) default NULL,
  `zhaopian` varchar(255) default NULL,
  `jingli` varchar(255) default NULL,
  `beizhu` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xuesheng
-- ----------------------------
INSERT INTO `xuesheng` VALUES ('16', '2', '史诗儿', '女', '1991-09-04', '绿庭45号601', '', '18602164489', '13671556616', '', null, null, '', '');
INSERT INTO `xuesheng` VALUES ('2', '1', '郑绍辉', '男', '2009-12-02', '绿庭17号2702', '67752338', '13817169438', '13564401728', null, null, null, '好样的', '');
INSERT INTO `xuesheng` VALUES ('3', '3', '起坤', '男', '1991-09-04', '海事大学', '', '18221105276', '', null, null, null, '很好', '');
INSERT INTO `xuesheng` VALUES ('19', '15', '起坤', '男', '1991-09-04', '临港新城海港大道', '', '', '', '海事', null, null, '', '');
INSERT INTO `xuesheng` VALUES ('20', '15', '陈军', '男', '1991-09-04', '临港新城1550', '18221105276', '', '', '海事幼儿园', null, null, '', '');
INSERT INTO `xuesheng` VALUES ('13', '2', '敦耀', '男', '1991-09-04', '临港新城', '', '', '', '', null, null, '', '');
INSERT INTO `xuesheng` VALUES ('18', '3', '陈军', '男', '1991-09-04', '临港新城', '178223443', '', '', '海事', null, null, '', '');

-- ----------------------------
-- Table structure for `xuesheng_kecheng`
-- ----------------------------
DROP TABLE IF EXISTS `xuesheng_kecheng`;
CREATE TABLE `xuesheng_kecheng` (
  `id` int(11) NOT NULL auto_increment,
  `xueshengid` int(11) default NULL,
  `kechengid` int(11) default NULL,
  `xuefei` float default '0',
  `shoukuanren` varchar(255) default NULL,
  `queqinliang` int(11) default '0',
  `qq_beizhu` varchar(500) default NULL,
  `pingjia` varchar(300) default NULL,
  `sy_xueqi` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xuesheng_kecheng
-- ----------------------------
INSERT INTO `xuesheng_kecheng` VALUES ('25', '20', '17', '0', '何某', '0', null, null, '0');
INSERT INTO `xuesheng_kecheng` VALUES ('26', '19', '17', '0', '\'酱油\'', '0', null, null, '0');
INSERT INTO `xuesheng_kecheng` VALUES ('27', '19', '15', '0', '\'酱油\'', '0', null, null, '0');
