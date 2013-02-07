# ---!Ups
CREATE FULLTEXT INDEX value_fulltext_idx ON card_tags (`value`);
CREATE INDEX filename_idx ON card_sides (filename);
CREATE INDEX user_id_provider_idx ON users (user_id, provider);

# ---!Downs
DROP INDEX value_fulltext_idx ON card_tags;
DROP INDEX filename_idx ON card_sides;
DROP INDEX user_id_provider_idx ON users;
