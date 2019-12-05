/*
*  这个是更改过的SQL, 因为PowerDesigner里面并没有mediummediumtext类型, 只有一个通用的mediumtext类型  
*  因此手动更改字段类型
*/

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/12/5 10:09:06                           */
/*==============================================================*/


drop table if exists Comment_Muc;

drop table if exists Lyrics;

drop table if exists Map_SL_S;

drop table if exists Map_S_T;

drop table if exists Record_CommentsSquare;

drop table if exists Song;

drop table if exists SongList;

drop table if exists Tag;

drop table if exists User_Muc;

/*==============================================================*/
/* Table: Comment_Muc                                           */
/*==============================================================*/
create table Comment_Muc
(
   ID_Comment           int not null auto_increment,
   ID_Song              int,
   ID_User              varchar(24),
   ID_Reply             int,
   ReleaseTime_Comment  datetime not null,
   Content_Comment      varchar(1500) not null,
   Likes_Comment        int not null,
   Dislikes_Comment     int not null,
   Score_Comment        int not null,
   primary key (ID_Comment)
);

alter table Comment_Muc comment 'ÆÀÂÛ';

/*==============================================================*/
/* Table: Lyrics                                                */
/*==============================================================*/
create table Lyrics
(
   ID_Lyrics            int not null auto_increment,
   ID_Song              int,
   Content_Lyrics       varchar(2400),
   Flag_Pure_Lyrics     bool,
   primary key (ID_Lyrics)
);

alter table Lyrics comment '¸è´Ê';

/*==============================================================*/
/* Table: Map_SL_S                                              */
/*==============================================================*/
create table Map_SL_S
(
   ID_SL                int not null,
   ID_Song              int not null,
   primary key (ID_SL, ID_Song)
);

alter table Map_SL_S comment 'Ó³Éä-¸èµ¥°üº¬¸èÇú';

/*==============================================================*/
/* Table: Map_S_T                                               */
/*==============================================================*/
create table Map_S_T
(
   ID_Song              int not null,
   ID_Tag               int not null,
   Num                  int,
   primary key (ID_Song, ID_Tag)
);

alter table Map_S_T comment 'Ó³Éä-¸èÇúºÍ±êÇ©';

/*==============================================================*/
/* Table: Record_CommentsSquare                                 */
/*==============================================================*/
create table Record_CommentsSquare
(
   ID_Comment           int not null,
   primary key (ID_Comment)
);

alter table Record_CommentsSquare comment 'ÆÀÂÛ¹ã³¡µÄÆÀÂÛ¼ÇÂ¼';

/*==============================================================*/
/* Table: Song                                                  */
/*==============================================================*/
create table Song
(
   ID_Song              int not null auto_increment,
   Name_Song            varchar(90),
   Singer_Song          varchar(30),
   ReleaseDate_Song     date,
   File_Song            mediumtext,
   IconFile_Song        mediumtext,
   Score_Song           float,
   primary key (ID_Song)
);

alter table Song comment '¸èÇú';

/*==============================================================*/
/* Table: SongList                                              */
/*==============================================================*/
create table SongList
(
   ID_SL                int not null auto_increment,
   ID_User              varchar(24),
   Name_SL              varchar(90) not null,
   Date_SL              date not null,
   Description_SL       varchar(900),
   primary key (ID_SL)
);

alter table SongList comment '¸èµ¥';

/*==============================================================*/
/* Table: Tag                                                   */
/*==============================================================*/
create table Tag
(
   ID_Tag               int not null auto_increment,
   Name_Tag             varchar(45) not null,
   primary key (ID_Tag)
);

alter table Tag comment '±êÇ©';

/*==============================================================*/
/* Table: User_Muc                                              */
/*==============================================================*/
create table User_Muc
(
   ID_User              varchar(24) not null,
   Password_User        varchar(16) not null,
   Nickname_User        varchar(30),
   IconFile_User        mediumtext,
   Idiograph_User       varchar(150),
   Gender_User          char(1) not null,
   Level_User           char(1) not null,
   State_User           char(1) not null,
   primary key (ID_User)
);

alter table User_Muc comment 'ÓÃ»§';

alter table Comment_Muc add constraint FK_SongHasComments foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Comment_Muc add constraint FK_Write foreign key (ID_User)
      references User_Muc (ID_User) on delete restrict on update restrict;

alter table Lyrics add constraint FK_SongHasLyrics foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Map_SL_S add constraint FK_Map_SL_S foreign key (ID_SL)
      references SongList (ID_SL) on delete restrict on update restrict;

alter table Map_SL_S add constraint FK_Map_SL_S2 foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Map_S_T add constraint FK_Map_S_T foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Map_S_T add constraint FK_Map_S_T2 foreign key (ID_Tag)
      references Tag (ID_Tag) on delete restrict on update restrict;

alter table Record_CommentsSquare add constraint FK_SomeComments foreign key (ID_Comment)
      references Comment_Muc (ID_Comment) on delete restrict on update restrict;

alter table SongList add constraint FK_UserCreateSongList foreign key (ID_User)
      references User_Muc (ID_User) on delete restrict on update restrict;

