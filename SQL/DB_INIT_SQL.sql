/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/27 15:58:00                          */
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
   ID_Comment           int not null,
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

alter table Comment_Muc comment '����';

/*==============================================================*/
/* Table: Lyrics                                                */
/*==============================================================*/
create table Lyrics
(
   ID_Lyrics            int not null,
   ID_Song              int,
   Content              varchar(1500),
   Flag_Pure            bool,
   primary key (ID_Lyrics)
);

alter table Lyrics comment '���';

/*==============================================================*/
/* Table: Map_SL_S                                              */
/*==============================================================*/
create table Map_SL_S
(
   ID_SL                int not null,
   ID_Song              int not null,
   primary key (ID_SL, ID_Song)
);

alter table Map_SL_S comment 'ӳ��-�赥��������';

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

alter table Map_S_T comment 'ӳ��-�����ͱ�ǩ';

/*==============================================================*/
/* Table: Record_CommentsSquare                                 */
/*==============================================================*/
create table Record_CommentsSquare
(
   ID_Comment           int not null,
   primary key (ID_Comment)
);

alter table Record_CommentsSquare comment '���۹㳡�����ۼ�¼';

/*==============================================================*/
/* Table: Song                                                  */
/*==============================================================*/
create table Song
(
   ID_Song              int not null,
   Name_Song            varchar(90),
   Singer               varchar(30),
   Date_Release         date,
   Content_Song         VBIN,
   primary key (ID_Song)
);

alter table Song comment '����';

/*==============================================================*/
/* Table: SongList                                              */
/*==============================================================*/
create table SongList
(
   ID_SL                int not null,
   ID_User              varchar(24),
   Name_SL              varchar(90),
   Date_SL              date,
   Description_SL       varchar(900),
   primary key (ID_SL)
);

alter table SongList comment '�赥';

/*==============================================================*/
/* Table: Tag                                                   */
/*==============================================================*/
create table Tag
(
   ID_Tag               int not null,
   Name_Tag             varchar(45),
   primary key (ID_Tag)
);

alter table Tag comment '��ǩ';

/*==============================================================*/
/* Table: User_Muc                                              */
/*==============================================================*/
create table User_Muc
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

alter table User_Muc comment '�û�';

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

