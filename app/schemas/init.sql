CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b8bb5e1f5cbce85118a62d7a7ce20bb4');
CREATE VIRTUAL TABLE IF NOT EXISTS `WarmongerEntity` USING FTS4(`cyrillicName` TEXT NOT NULL, `name` TEXT NOT NULL, `notes` TEXT NOT NULL, `tags` TEXT NOT NULL, tokenize=unicode61);
.mode csv
.import app/schemas/WarmongerEntity.csv WarmongerEntity
CREATE TABLE IF NOT EXISTS `TagEntity` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `ruName` TEXT NOT NULL, PRIMARY KEY(`id`));
.mode csv
.import app/schemas/TagEntity.csv TagEntity
CREATE TABLE IF NOT EXISTS `IndexEntity` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`));
.mode csv
.import app/schemas/IndexEntity.csv IndexEntity
