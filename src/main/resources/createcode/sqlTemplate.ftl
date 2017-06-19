SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ${classNameUpper}
-- ----------------------------
DROP TABLE IF EXISTS `${classNameUpper}`;
CREATE TABLE `${classNameUpper}` (
`${classNameUpper}_ID` varchar(255) NOT NULL,
`b` int(11) DEFAULT NULL,
`c` double DEFAULT NULL,
`d` date DEFAULT NULL,
`e` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
`f` tinyint(1) DEFAULT NULL,
`g` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `te`;
CREATE TABLE `te` (
`a` varchar(255) DEFAULT NULL,
`b` int(11) DEFAULT NULL,
`c` double DEFAULT NULL,
`d` date DEFAULT NULL,
`e` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
`f` tinyint(1) DEFAULT NULL,
`g` varchar(255) NOT NULL,
`j` varchar(255) NOT NULL,
PRIMARY KEY (`j`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;