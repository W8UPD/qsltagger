# ---!Ups
create table users (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  user_id varchar(255) NOT NULL,
  provider varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  full_name varchar(255) NOT NULL,
  email varchar(255),
  profile_pic varchar(255),
  auth_method varchar(30),
  registered_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

# ---!Downs
DROP TABLE IF EXISTS users;
