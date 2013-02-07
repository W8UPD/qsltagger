# ---!Ups
ALTER TABLE users ADD COLUMN ever_logged_in boolean default false;

# ---!Downs
ALTER TABLE users DROP COLUMN ever_logged_in;
