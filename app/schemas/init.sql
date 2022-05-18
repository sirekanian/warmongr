CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '67617e1b82bf6e5f548c1a717680e21b');
CREATE VIRTUAL TABLE IF NOT EXISTS `WarmongerEntity` USING FTS4(`cyrillicName` TEXT NOT NULL, `name` TEXT NOT NULL, `notes` TEXT NOT NULL, tokenize=unicode61);
.import --csv app/schemas/init.csv WarmongerEntity
