/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : mucmuc

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-11-21 12:45:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID_Admin` varchar(24) NOT NULL,
  `Password_Admin` varchar(16) DEFAULT NULL,
  `Level_Admin` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID_Admin`),
  UNIQUE KEY `Admin_PK` (`ID_Admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for belong
-- ----------------------------
DROP TABLE IF EXISTS `belong`;
CREATE TABLE `belong` (
  `ID_SongList` int(11) NOT NULL,
  `ID_Song` int(11) NOT NULL,
  PRIMARY KEY (`ID_SongList`,`ID_Song`),
  KEY `belong_FK` (`ID_SongList`),
  KEY `belong2_FK` (`ID_Song`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of belong
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `ID_Comment` int(11) NOT NULL,
  `ID_CommentSquare` int(11) DEFAULT NULL,
  `ID_User` varchar(24) DEFAULT NULL,
  `ID_Commentsetion` int(11) DEFAULT NULL,
  `Infomation` varchar(1000) DEFAULT NULL,
  `Time_Comment` timestamp NULL DEFAULT NULL,
  `Likes` int(11) DEFAULT NULL,
  `Score` float DEFAULT NULL,
  PRIMARY KEY (`ID_Comment`),
  UNIQUE KEY `Comment_PK` (`ID_Comment`),
  KEY `comments_FK` (`ID_Commentsetion`),
  KEY `write_FK` (`ID_User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for commentssetion
-- ----------------------------
DROP TABLE IF EXISTS `commentssetion`;
CREATE TABLE `commentssetion` (
  `ID_Commentsetion` int(11) NOT NULL,
  `ID_Song` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Commentsetion`),
  UNIQUE KEY `CommentsSetion_PK` (`ID_Commentsetion`),
  KEY `have4_FK` (`ID_Song`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentssetion
-- ----------------------------

-- ----------------------------
-- Table structure for commentssquare
-- ----------------------------
DROP TABLE IF EXISTS `commentssquare`;
CREATE TABLE `commentssquare` (
  `ID_CommentSquare` int(11) NOT NULL,
  PRIMARY KEY (`ID_CommentSquare`),
  UNIQUE KEY `CommentsSquare_PK` (`ID_CommentSquare`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentssquare
-- ----------------------------

-- ----------------------------
-- Table structure for kind
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `ID_Song` int(11) NOT NULL,
  `ID_Tag` int(11) NOT NULL,
  PRIMARY KEY (`ID_Song`,`ID_Tag`),
  KEY `kind_FK` (`ID_Song`),
  KEY `kind2_FK` (`ID_Tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------

-- ----------------------------
-- Table structure for lyrics
-- ----------------------------
DROP TABLE IF EXISTS `lyrics`;
CREATE TABLE `lyrics` (
  `ID_Lyrics` int(11) NOT NULL,
  `ID_Song` int(11) DEFAULT NULL,
  `Song_Lyrics` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_Lyrics`),
  UNIQUE KEY `lyrics_PK` (`ID_Lyrics`),
  KEY `have_FK` (`ID_Song`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lyrics
-- ----------------------------

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `ID_Song` int(11) NOT NULL,
  `ID_Commentsetion` int(11) DEFAULT NULL,
  `ID_Lyrics` int(11) DEFAULT NULL,
  `Name_Song` varchar(24) DEFAULT NULL,
  `Singer` varchar(24) DEFAULT NULL,
  `Date_Song` date DEFAULT NULL,
  `Language` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_Song`),
  UNIQUE KEY `Song_PK` (`ID_Song`),
  KEY `have2_FK` (`ID_Lyrics`),
  KEY `have3_FK` (`ID_Commentsetion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------

-- ----------------------------
-- Table structure for songlist
-- ----------------------------
DROP TABLE IF EXISTS `songlist`;
CREATE TABLE `songlist` (
  `ID_SongList` int(11) NOT NULL,
  `ID_User` varchar(24) DEFAULT NULL,
  `Name_SongList` varchar(20) DEFAULT NULL,
  `Date_SongList` date DEFAULT NULL,
  PRIMARY KEY (`ID_SongList`),
  UNIQUE KEY `SongList_PK` (`ID_SongList`),
  KEY `create_FK` (`ID_User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songlist
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `ID_Tag` int(11) NOT NULL,
  `Name_Tag` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_Tag`),
  UNIQUE KEY `Tag_PK` (`ID_Tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID_User` varchar(24) NOT NULL,
  `Password_User` varchar(16) DEFAULT NULL,
  `Nickname_User` varchar(30) DEFAULT NULL,
  `Icon_User` mediumtext CHARACTER SET utf8 COLLATE utf8_bin,
  `Idiograph_User` varchar(100) DEFAULT NULL,
  `Gender_User` char(1) DEFAULT NULL,
  `Level_User` char(1) DEFAULT NULL,
  `State_User` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID_User`),
  UNIQUE KEY `User_PK` (`ID_User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
