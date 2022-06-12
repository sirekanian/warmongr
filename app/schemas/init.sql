CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '71fabb860e0ba05f19f248efc40ff07d');
CREATE TABLE IF NOT EXISTS `MetaEntity` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`));
.import --csv app/schemas/MetaEntity.csv MetaEntity
CREATE VIRTUAL TABLE IF NOT EXISTS `WarmongerEntity` USING FTS4(`cyrillicName` TEXT NOT NULL, `name` TEXT NOT NULL, `notes` TEXT NOT NULL, `tags` TEXT NOT NULL, tokenize=unicode61);
.import --csv app/schemas/WarmongerEntity.csv WarmongerEntity
CREATE TABLE IF NOT EXISTS `TagEntity` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `ruName` TEXT NOT NULL, PRIMARY KEY(`id`));
.import --csv app/schemas/TagEntity.csv TagEntity
CREATE TABLE IF NOT EXISTS `IndexEntity` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`));
.import --csv app/schemas/IndexEntity.csv IndexEntity
