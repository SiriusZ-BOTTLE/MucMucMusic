/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 10                       */
/* Created on:     2019/12/3 下午7:51:48                          */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_COMMENT__SONGHASCO_SONG') then
    alter table Comment_Muc
       delete foreign key FK_COMMENT__SONGHASCO_SONG
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COMMENT__WRITE_USER_MUC') then
    alter table Comment_Muc
       delete foreign key FK_COMMENT__WRITE_USER_MUC
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LYRICS_SONGHASLY_SONG') then
    alter table Lyrics
       delete foreign key FK_LYRICS_SONGHASLY_SONG
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MAP_SL_S_MAP_SL_S_SONGLIST') then
    alter table Map_SL_S
       delete foreign key FK_MAP_SL_S_MAP_SL_S_SONGLIST
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MAP_SL_S_MAP_SL_S2_SONG') then
    alter table Map_SL_S
       delete foreign key FK_MAP_SL_S_MAP_SL_S2_SONG
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MAP_S_T_MAP_S_T_SONG') then
    alter table Map_S_T
       delete foreign key FK_MAP_S_T_MAP_S_T_SONG
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_MAP_S_T_MAP_S_T2_TAG') then
    alter table Map_S_T
       delete foreign key FK_MAP_S_T_MAP_S_T2_TAG
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_RECORD_C_SOMECOMME_COMMENT_') then
    alter table Record_CommentsSquare
       delete foreign key FK_RECORD_C_SOMECOMME_COMMENT_
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SONGLIST_USERCREAT_USER_MUC') then
    alter table SongList
       delete foreign key FK_SONGLIST_USERCREAT_USER_MUC
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='SongHasComments_FK'
     and t.table_name='Comment_Muc'
) then
   drop index Comment_Muc.SongHasComments_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Write_FK'
     and t.table_name='Comment_Muc'
) then
   drop index Comment_Muc.Write_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Comment_Muc_PK'
     and t.table_name='Comment_Muc'
) then
   drop index Comment_Muc.Comment_Muc_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Comment_Muc'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Comment_Muc
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='SongHasLyrics_FK'
     and t.table_name='Lyrics'
) then
   drop index Lyrics.SongHasLyrics_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Lyrics_PK'
     and t.table_name='Lyrics'
) then
   drop index Lyrics.Lyrics_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Lyrics'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Lyrics
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_SL_S2_FK'
     and t.table_name='Map_SL_S'
) then
   drop index Map_SL_S.Map_SL_S2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_SL_S_FK'
     and t.table_name='Map_SL_S'
) then
   drop index Map_SL_S.Map_SL_S_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_SL_S_PK'
     and t.table_name='Map_SL_S'
) then
   drop index Map_SL_S.Map_SL_S_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Map_SL_S'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Map_SL_S
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_S_T2_FK'
     and t.table_name='Map_S_T'
) then
   drop index Map_S_T.Map_S_T2_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_S_T_FK'
     and t.table_name='Map_S_T'
) then
   drop index Map_S_T.Map_S_T_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Map_S_T_PK'
     and t.table_name='Map_S_T'
) then
   drop index Map_S_T.Map_S_T_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Map_S_T'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Map_S_T
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='SomeComments_FK'
     and t.table_name='Record_CommentsSquare'
) then
   drop index Record_CommentsSquare.SomeComments_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Record_CommentsSquare'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Record_CommentsSquare
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Song_PK'
     and t.table_name='Song'
) then
   drop index Song.Song_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Song'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Song
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='UserCreateSongList_FK'
     and t.table_name='SongList'
) then
   drop index SongList.UserCreateSongList_FK
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='SongList_PK'
     and t.table_name='SongList'
) then
   drop index SongList.SongList_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='SongList'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table SongList
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='Tag_PK'
     and t.table_name='Tag'
) then
   drop index Tag.Tag_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Tag'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Tag
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='User_Muc_PK'
     and t.table_name='User_Muc'
) then
   drop index User_Muc.User_Muc_PK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='User_Muc'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table User_Muc
end if;

/*==============================================================*/
/* Table: Comment_Muc                                           */
/*==============================================================*/
create table Comment_Muc 
(
   ID_Comment           integer                        not null,
   ID_Song              integer                        null,
   ID_User              varchar(24)                    null,
   ID_Reply             integer                        null,
   ReleaseTime_Comment  timestamp                      not null,
   Content_Comment      varchar(1500)                  not null,
   Likes_Comment        integer                        not null,
   Dislikes_Comment     integer                        not null,
   Score_Comment        integer                        not null,
   Nickname_User        varchar(30)                    null,
   Name_Song            varchar(90)                    null,
   constraint PK_COMMENT_MUC primary key (ID_Comment)
);

comment on table Comment_Muc is 
'评论';

/*==============================================================*/
/* Index: Comment_Muc_PK                                        */
/*==============================================================*/
create unique index Comment_Muc_PK on Comment_Muc (
ID_Comment ASC
);

/*==============================================================*/
/* Index: Write_FK                                              */
/*==============================================================*/
create index Write_FK on Comment_Muc (
ID_User ASC
);

/*==============================================================*/
/* Index: SongHasComments_FK                                    */
/*==============================================================*/
create index SongHasComments_FK on Comment_Muc (
ID_Song ASC
);

/*==============================================================*/
/* Table: Lyrics                                                */
/*==============================================================*/
create table Lyrics 
(
   ID_Lyrics            integer                        not null,
   ID_Song              integer                        null,
   Content_Lyrics       varchar(2400)                  null,
   Flag_Pure_Lyrics     smallint                       null,
   constraint PK_LYRICS primary key (ID_Lyrics)
);

comment on table Lyrics is 
'歌词';

/*==============================================================*/
/* Index: Lyrics_PK                                             */
/*==============================================================*/
create unique index Lyrics_PK on Lyrics (
ID_Lyrics ASC
);

/*==============================================================*/
/* Index: SongHasLyrics_FK                                      */
/*==============================================================*/
create index SongHasLyrics_FK on Lyrics (
ID_Song ASC
);

/*==============================================================*/
/* Table: Map_SL_S                                              */
/*==============================================================*/
create table Map_SL_S 
(
   ID_SL                integer                        not null,
   ID_Song              integer                        not null,
   constraint PK_MAP_SL_S primary key clustered (ID_SL, ID_Song)
);

comment on table Map_SL_S is 
'映射-歌单包含歌曲';

/*==============================================================*/
/* Index: Map_SL_S_PK                                           */
/*==============================================================*/
create unique clustered index Map_SL_S_PK on Map_SL_S (
ID_SL ASC,
ID_Song ASC
);

/*==============================================================*/
/* Index: Map_SL_S_FK                                           */
/*==============================================================*/
create index Map_SL_S_FK on Map_SL_S (
ID_SL ASC
);

/*==============================================================*/
/* Index: Map_SL_S2_FK                                          */
/*==============================================================*/
create index Map_SL_S2_FK on Map_SL_S (
ID_Song ASC
);

/*==============================================================*/
/* Table: Map_S_T                                               */
/*==============================================================*/
create table Map_S_T 
(
   ID_Song              integer                        not null,
   ID_Tag               integer                        not null,
   constraint PK_MAP_S_T primary key clustered (ID_Song, ID_Tag)
);

comment on table Map_S_T is 
'映射-歌曲和标签';

/*==============================================================*/
/* Index: Map_S_T_PK                                            */
/*==============================================================*/
create unique clustered index Map_S_T_PK on Map_S_T (
ID_Song ASC,
ID_Tag ASC
);

/*==============================================================*/
/* Index: Map_S_T_FK                                            */
/*==============================================================*/
create index Map_S_T_FK on Map_S_T (
ID_Song ASC
);

/*==============================================================*/
/* Index: Map_S_T2_FK                                           */
/*==============================================================*/
create index Map_S_T2_FK on Map_S_T (
ID_Tag ASC
);

/*==============================================================*/
/* Table: Record_CommentsSquare                                 */
/*==============================================================*/
create table Record_CommentsSquare 
(
   ID_Comment           integer                        null
);

comment on table Record_CommentsSquare is 
'评论广场的评论记录';

/*==============================================================*/
/* Index: SomeComments_FK                                       */
/*==============================================================*/
create index SomeComments_FK on Record_CommentsSquare (
ID_Comment ASC
);

/*==============================================================*/
/* Table: Song                                                  */
/*==============================================================*/
create table Song 
(
   ID_Song              integer                        not null,
   Name_Song            varchar(90)                    null,
   Singer_Song          varchar(30)                    null,
   ReleaseDate_Song     date                           null,
   FileURL_Song         varchar(200)                   null,
   Score_Song           float                          null,
   constraint PK_SONG primary key (ID_Song)
);

comment on table Song is 
'歌曲';

/*==============================================================*/
/* Index: Song_PK                                               */
/*==============================================================*/
create unique index Song_PK on Song (
ID_Song ASC
);

/*==============================================================*/
/* Table: SongList                                              */
/*==============================================================*/
create table SongList 
(
   ID_SL                integer                        not null,
   ID_User              varchar(24)                    null,
   Name_SL              varchar(90)                    not null,
   Date_SL              date                           not null,
   Description_SL       varchar(900)                   null,
   Nickname_User        varchar(30)                    null,
   constraint PK_SONGLIST primary key (ID_SL)
);

comment on table SongList is 
'歌单';

/*==============================================================*/
/* Index: SongList_PK                                           */
/*==============================================================*/
create unique index SongList_PK on SongList (
ID_SL ASC
);

/*==============================================================*/
/* Index: UserCreateSongList_FK                                 */
/*==============================================================*/
create index UserCreateSongList_FK on SongList (
ID_User ASC
);

/*==============================================================*/
/* Table: Tag                                                   */
/*==============================================================*/
create table Tag 
(
   ID_Tag               integer                        not null,
   Name_Tag             varchar(45)                    not null,
   constraint PK_TAG primary key (ID_Tag)
);

comment on table Tag is 
'标签';

/*==============================================================*/
/* Index: Tag_PK                                                */
/*==============================================================*/
create unique index Tag_PK on Tag (
ID_Tag ASC
);

/*==============================================================*/
/* Table: User_Muc                                              */
/*==============================================================*/
create table User_Muc 
(
   ID_User              varchar(24)                    not null,
   Password_User        varchar(16)                    not null,
   Nickname_User        varchar(30)                    null,
   IconFileURL_User     varchar(200)                   null,
   Idiograph_User       varchar(150)                   null,
   Gender_User          char(1)                        not null,
   Level_User           char(1)                        not null,
   State_User           char(1)                        not null,
   constraint PK_USER_MUC primary key (ID_User)
);

comment on table User_Muc is 
'用户';

/*==============================================================*/
/* Index: User_Muc_PK                                           */
/*==============================================================*/
create unique index User_Muc_PK on User_Muc (
ID_User ASC
);

alter table Comment_Muc
   add constraint FK_COMMENT__SONGHASCO_SONG foreign key (ID_Song)
      references Song (ID_Song)
      on update restrict
      on delete restrict;

comment on foreign key Comment_Muc.FK_COMMENT__SONGHASCO_SONG is 
'歌曲具有许多评论';

alter table Comment_Muc
   add constraint FK_COMMENT__WRITE_USER_MUC foreign key (ID_User)
      references User_Muc (ID_User)
      on update restrict
      on delete restrict;

comment on foreign key Comment_Muc.FK_COMMENT__WRITE_USER_MUC is 
'用户写评论';

alter table Lyrics
   add constraint FK_LYRICS_SONGHASLY_SONG foreign key (ID_Song)
      references Song (ID_Song)
      on update restrict
      on delete restrict;

comment on foreign key Lyrics.FK_LYRICS_SONGHASLY_SONG is 
'一首歌有一副歌词';

alter table Map_SL_S
   add constraint FK_MAP_SL_S_MAP_SL_S_SONGLIST foreign key (ID_SL)
      references SongList (ID_SL)
      on update restrict
      on delete restrict;

comment on foreign key Map_SL_S.FK_MAP_SL_S_MAP_SL_S_SONGLIST is 
'映射-歌单包含歌曲';

alter table Map_SL_S
   add constraint FK_MAP_SL_S_MAP_SL_S2_SONG foreign key (ID_Song)
      references Song (ID_Song)
      on update restrict
      on delete restrict;

comment on foreign key Map_SL_S.FK_MAP_SL_S_MAP_SL_S2_SONG is 
'映射-歌单包含歌曲';

alter table Map_S_T
   add constraint FK_MAP_S_T_MAP_S_T_SONG foreign key (ID_Song)
      references Song (ID_Song)
      on update restrict
      on delete restrict;

comment on foreign key Map_S_T.FK_MAP_S_T_MAP_S_T_SONG is 
'映射-歌曲和标签';

alter table Map_S_T
   add constraint FK_MAP_S_T_MAP_S_T2_TAG foreign key (ID_Tag)
      references Tag (ID_Tag)
      on update restrict
      on delete restrict;

comment on foreign key Map_S_T.FK_MAP_S_T_MAP_S_T2_TAG is 
'映射-歌曲和标签';

alter table Record_CommentsSquare
   add constraint FK_RECORD_C_SOMECOMME_COMMENT_ foreign key (ID_Comment)
      references Comment_Muc (ID_Comment)
      on update restrict
      on delete restrict;

alter table SongList
   add constraint FK_SONGLIST_USERCREAT_USER_MUC foreign key (ID_User)
      references User_Muc (ID_User)
      on update restrict
      on delete restrict;

comment on foreign key SongList.FK_SONGLIST_USERCREAT_USER_MUC is 
'用户创建歌单';

