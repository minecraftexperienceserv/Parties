-- H2 database
CREATE TABLE IF NOT EXISTS `<prefix>parties` (
	`id`			CHAR(36) NOT NULL PRIMARY KEY,
	`name`			VARCHAR(255) NOT NULL,
	`tag`			VARCHAR(255),
	`leader`		CHAR(36),
	`description`	VARCHAR(255),
	`motd`			VARCHAR(500),
	`color`			VARCHAR(255),
	`kills`			INT DEFAULT 0,
	`password`		VARCHAR(255),
	`home`			VARCHAR(255),
	`protection`	INT DEFAULT 0,
	`experience`	REAL DEFAULT 0,
	`follow`		INT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS `<prefix>players` (
	`uuid`			CHAR(36) NOT NULL PRIMARY KEY,
	`party`			CHAR(36),
	`rank`			INT DEFAULT 0,
	`nickname`		VARCHAR(255),
	`chat`			INT DEFAULT 0,
	`spy`			INT DEFAULT 0,
	`mute`			INT DEFAULT 0
);