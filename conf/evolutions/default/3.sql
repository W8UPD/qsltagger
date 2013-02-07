# ---!Ups
create table cards (
  id mediumint NOT NULL AUTO_INCREMENT,
  tagger_user_id mediumint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

create table card_sides (
  id int NOT NULL AUTO_INCREMENT,
  card_id mediumint NOT NULL,
  filename varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

create table card_tags (
  id bigint NOT NULL AUTO_INCREMENT,
  card_id mediumint NOT NULL,
  `key` varchar(255) NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (id)
) ENGINE=MyISAM;

# ---!Downs
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS cards_sides;
DROP TABLE IF EXISTS cards_tags;
