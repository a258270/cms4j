SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `DIC_ID` varchar(255) NOT NULL COMMENT '字典id',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '父级id',
  `CODE` varchar(255) NOT NULL COMMENT '标识码',
  `SORT` int(11) NOT NULL COMMENT '排序参数',
  `LEVEL` int(11) NOT NULL COMMENT '层级',
  PRIMARY KEY (`DIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------
INSERT INTO `sys_dictionaries` VALUES ('1', '性别', null, 'SEX', '1', '1');
INSERT INTO `sys_dictionaries` VALUES ('11', '男', '1', 'SEX_1', '1', '2');
INSERT INTO `sys_dictionaries` VALUES ('12', '女', '1', 'SEX_2', '1', '2');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `ICON` varchar(255) NOT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `SORT` int(11) NOT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', 'xe63c', null, '1');
INSERT INTO `sys_menu` VALUES ('2', '系统设置', '/admin/setting/index', 'xe62e', '1', '1');
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '/admin/role/index', 'xe611', '1', '3');
INSERT INTO `sys_menu` VALUES ('4', '用户管理', '/admin/user/index', 'xe62c', '1', '4');
INSERT INTO `sys_menu` VALUES ('5', '字典管理', '/admin/dictionary/index', 'xe720', '1', '5');
INSERT INTO `sys_menu` VALUES ('6', '菜单管理', '/admin/menu/index', 'xe681', '1', '6');
INSERT INTO `sys_menu` VALUES ('7', '系统监控', '/druid/index.html', 'xe725', '1', '2');
INSERT INTO `sys_menu` VALUES ('8', '在线管理', '/admin/user/online/index', 'xe62b', '1', '7');
INSERT INTO `sys_menu` VALUES ('9', '代码生成', '/admin/createcode/index', 'xe720', '1', '8');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色id',
  `ROLENAME` varchar(255) NOT NULL COMMENT '角色名称',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '父级id',
  `ADD_QX` varchar(255) NOT NULL DEFAULT '0',
  `DEL_QX` varchar(255) NOT NULL DEFAULT '0',
  `EDIT_QX` varchar(255) NOT NULL DEFAULT '0',
  `QUERY_QX` varchar(255) NOT NULL DEFAULT '0',
  `SORT` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', null, '0', '0', '0', '254', '1');
INSERT INTO `sys_role` VALUES ('suadmin', '超级管理员', '1', '0', '254', '0', '254', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(255) NOT NULL COMMENT '用户id',
  `USERNAME` varchar(255) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '密码',
  `NAME` varchar(255) NOT NULL COMMENT '姓名',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '所属角色',
  `LAST_LOGIN` datetime DEFAULT NULL COMMENT '最后登录时间',
  `IP` varchar(15) DEFAULT NULL COMMENT '最后登录ip地址',
  `STATUS` tinyint(1) NOT NULL COMMENT '用户状态',
  `PHONE` varchar(255) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '系统管理员', 'suadmin', '2017-06-29 10:03:46', '0:0:0:0:0:0:0:1', '1', null);
