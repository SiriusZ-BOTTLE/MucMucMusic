/*
视图创建
*/

CREATE VIEW `tag_song`
AS (SELECT Tag.ID_Tag,Tag.Name_Tag,Song.* FROM Tag INNER JOIN Map_S_T ON Tag.ID_Tag = Map_S_T.ID_Tag LEFT JOIN Song ON Map_S_T.ID_Song = Song.ID_Song);


CREATE VIEW `sl_s`
AS (SELECT SL.*,Song.* FROM SongList SL INNER JOIN Map_SL_S ON Map_SL_S.ID_SL = SL.ID_SL LEFT JOIN Song ON Map_SL_S.ID_Song = Song.ID_Song);
