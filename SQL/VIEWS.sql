/*
视图创建
*/

CREATE VIEW `tag_song`
AS (SELECT Tag.ID_Tag,Tag.Name_Tag,Song.* FROM Tag LEFT JOIN Map_S_T ON Tag.ID_Tag = Map_S_T.ID_Tag LEFT JOIN Song ON Map_S_T.ID_Song = Song.ID_Song);

CREATE VIEW `song_tag`
AS (SELECT Song.*,Tag.ID_Tag,Tag.Name_Tag FROM Song LEFT JOIN Map_S_T ON Song.ID_Song = Map_S_T.ID_Song LEFT JOIN Tag ON Map_S_T.ID_Tag = Tag.ID_Tag);

CREATE VIEW `sl_s`
AS (SELECT SL.ID_SL,SL.Name_SL,SL.Date_SL,Song.ID_Song,Song.Name_Song,Song.Singer_Song,Song.ReleaseDate_Song,Song.File_Song,Song.IconFile_Song,Song.Score_Song FROM SongList SL LEFT JOIN Map_SL_S ON Map_SL_S.ID_SL = SL.ID_SL LEFT JOIN Song ON Map_SL_S.ID_Song = Song.ID_Song);

CREATE VIEW `s_sl`
AS (SELECT Song.ID_Song,Song.Name_Song,Song.Singer_Song,Song.ReleaseDate_Song,SL.* FROM Song  LEFT JOIN Map_SL_S ON Map_SL_S.ID_Song = Song.ID_Song LEFT JOIN SongList SL ON Map_SL_S.ID_SL = SL.ID_SL)