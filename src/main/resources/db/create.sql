CREATE TABLE IF NOT EXISTS `CATEGORIES`
(
  `ID`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `NAME`        VARCHAR(60) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `unique` (`NAME` ASC)
);

CREATE TABLE IF NOT EXISTS `TV_SHOWS`
(
  `ID`          	BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `NAME` 			VARCHAR(256)  NOT NULL,
  `SHORT_DESC` 		VARCHAR(256)  NULL DEFAULT NULL,
  `LONG_DESC` 		VARCHAR(2048) NULL DEFAULT NULL,
  `YEAR` 			YEAR(4) 	  NOT NULL,
  `RECOMMENDED_AGE` TINYINT 	  NOT NULL,
#  `CATEGORY_ID` 	BIGINT(20)    NOT NULL,
  `ADVERTISING` 	VARCHAR(256)  NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
 # CONSTRAINT `FK_TV_SHOWS_CATEGORY_ID`
 #  FOREIGN KEY (CATEGORY_ID) REFERENCES `CATEGORIES` (ID)
);

CREATE TABLE IF NOT EXISTS `SEASONS`
(
  `ID`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `NUMBER` 		TINYINT 	 NOT NULL,
  `NAME` 		VARCHAR(256) NOT NULL,
  `TV_SHOWS_ID`  BIGINT(20)   NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_SEASONS_TV_SHOW_ID`
    FOREIGN KEY (TV_SHOWS_ID) REFERENCES `tv_shows` (ID)
);

CREATE TABLE IF NOT EXISTS `CHAPTERS`
(
  `ID`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `NUMBER` 		TINYINT 	 NOT NULL,
  `NAME` 		VARCHAR(256) NOT NULL,
  `DURATION` 	TINYINT 	 NOT NULL,
  `SEASON_ID`  BIGINT(20)   NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_CHAPTERS_SEASON_ID`
    FOREIGN KEY (SEASON_ID) REFERENCES `SEASONS` (ID)
);