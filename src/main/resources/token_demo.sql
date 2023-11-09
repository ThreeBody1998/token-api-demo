

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '假删除的标志',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人ID',
  `update_user` int(11) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`, `account`, `password`, `is_delete`, `create_time`, `update_time`, `create_user`, `update_user`) VALUES (1, '张三', '10', 'pwd1', 0, '2023-10-13 16:46:50', '2023-10-13 16:46:50', 1, 1);
INSERT INTO `user` (`id`, `name`, `account`, `password`, `is_delete`, `create_time`, `update_time`, `create_user`, `update_user`) VALUES (2, '李四', '11', 'pwd2', 0, '2023-10-13 16:46:50', '2023-10-13 16:46:50', 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
