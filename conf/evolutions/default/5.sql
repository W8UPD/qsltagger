# ---!Ups
CREATE TABLE card_comments(
  id int NOT NULL AUTO_INCREMENT,
  card_id mediumint NOT NULL,
  user_id mediumint NOT NULL,
  comment longtext NOT NULL,
  commented_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE INDEX card_id_comments_idx ON card_comments (card_id);

# ---!Downs
DROP TABLE IF EXISTS card_comments;
DROP INDEX card_id_comments_idx ON card_comments;
