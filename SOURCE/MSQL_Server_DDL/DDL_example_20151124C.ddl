USE ISA681Poker;
CREATE TABLE ISA681_USERS (
  userPrimaryKey int(10) unsigned NOT NULL auto_increment,
  USERNAME varchar(20) NOT NULL ,
 PASSWORD varchar(10) NOT NULL ,
    PRIMARY KEY  (userPrimaryKey)
);