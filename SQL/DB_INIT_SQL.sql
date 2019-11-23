/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/23 21:41:22                          */
/*==============================================================*/


drop table if exists Admin;

drop table if exists Comment;

drop table if exists CommentsSetion;

drop table if exists Contain;

drop table if exists Kind;

drop table if exists Lyrics;

drop table if exists Record_CommentsSquare;

drop table if exists Song;

drop table if exists SongList;

drop table if exists Tag;

drop table if exists User;

/*==============================================================*/
/* Table: Admin                                                 */
/*==============================================================*/
create table Admin
(
   ID_Admin             varchar(24) not null,
   Password_Admin       varchar(16),
   Level_Admin          char(1)
);

alter table Admin comment '管理员(舍弃)';

/*==============================================================*/
/* Table: Comment                                               */
/*==============================================================*/
create table Comment
(
   ID_Comment           int not null auto_increment,
   ID_Song              int,
   ID_User              varchar(24),
   ID_Reply             int,
   Content              varchar(1500),
   Time_Release         datetime,
   Likes                int,
   Dislikes             int,
   Score                int,
   primary key (ID_Comment)
);

alter table Comment comment '评论区';

/*==============================================================*/
/* Table: CommentsSetion                                        */
/*==============================================================*/
create table CommentsSetion
(
   ID_CS                int not null,
   primary key (ID_CS)
);

alter table CommentsSetion comment '评论区';

/*==============================================================*/
/* Table: Contain                                               */
/*==============================================================*/
create table Contain
(
   ID_SL                int not null,
   ID_Song              int not null,
   primary key (ID_SL, ID_Song)
);

alter table Contain comment '歌单包含歌曲';

/*==============================================================*/
/* Table: Kind                                                  */
/*==============================================================*/
create table Kind
(
   ID_Song              int not null,
   ID_Tag               int not null,
   Num                  int,
   primary key (ID_Song, ID_Tag)
);

/*==============================================================*/
/* Table: Lyrics                                                */
/*==============================================================*/
create table Lyrics
(
   ID_Lyrics            int not null auto_increment,
   ID_Song              int,
   Content              varchar(1500),
   Flag_Pure            bool,
   primary key (ID_Lyrics)
);

alter table Lyrics comment '歌词';

/*==============================================================*/
/* Table: Record_CommentsSquare                                 */
/*==============================================================*/
create table Record_CommentsSquare
(
   ID_Comment           int not null,
   primary key (ID_Comment)
);

alter table Record_CommentsSquare comment '评论广场的评论记录';

/*==============================================================*/
/* Table: Song                                                  */
/*==============================================================*/
create table Song
(
   ID_Song              int not null auto_increment,
   Name_Song            varchar(90),
   Singer               varchar(30),
   Date_Release         date,
   primary key (ID_Song)
);

alter table Song comment '歌曲';

/*==============================================================*/
/* Table: SongList                                              */
/*==============================================================*/
create table SongList
(
   ID_SL                int not null auto_increment,
   ID_User              varchar(24),
   Name_SL              varchar(90),
   Date_SL              date,
   Description_SL       varchar(900),
   primary key (ID_SL)
);

alter table SongList comment '歌单';

/*==============================================================*/
/* Table: Tag                                                   */
/*==============================================================*/
create table Tag
(
   ID_Tag               int not null auto_increment,
   Name_Tag             varchar(45),
   primary key (ID_Tag)
);

alter table Tag comment '标签';

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   ID_User              varchar(24) not null,
   Password_User        varchar(16),
   Nickname_User        varchar(30),
   Icon_User            longblob,
   Idiograph_User       varchar(150),
   Gender_User          char(1),
   Level_User           char(1),
   State_User           char(1),
   primary key (ID_User)
);

alter table User comment '用户';

alter table Comment add constraint FK_SongHasComments foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Comment add constraint FK_Write foreign key (ID_User)
      references User (ID_User) on delete restrict on update restrict;

alter table Contain add constraint FK_Contain foreign key (ID_SL)
      references SongList (ID_SL) on delete restrict on update restrict;

alter table Contain add constraint FK_Contain2 foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Kind add constraint FK_Kind foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Kind add constraint FK_Kind2 foreign key (ID_Tag)
      references Tag (ID_Tag) on delete restrict on update restrict;

alter table Lyrics add constraint FK_SongHasLyrics foreign key (ID_Song)
      references Song (ID_Song) on delete restrict on update restrict;

alter table Record_CommentsSquare add constraint FK_SomeComments foreign key (ID_Comment)
      references Comment (ID_Comment) on delete restrict on update restrict;

alter table SongList add constraint FK_UserCreateSongList foreign key (ID_User)
      references User (ID_User) on delete restrict on update restrict;

