CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '16167d0ed0bd86c6fc41266aa0abe921');
CREATE TABLE IF NOT EXISTS `MetaEntity` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`));
.import --csv app/schemas/MetaEntity.csv MetaEntity
CREATE VIRTUAL TABLE IF NOT EXISTS `WarmongerEntity` USING FTS4(`cyrillicName` TEXT NOT NULL, `name` TEXT NOT NULL, `notes` TEXT NOT NULL, tokenize=unicode61);
.import --csv app/schemas/WarmongerEntity.csv WarmongerEntity
