create database fcs;
use fcs;
source C:\ISA681_Poker2\documents\fcs.sql
CREATE USER 'fcs_user'@'localhost' IDENTIFIED BY '7yXw8dDaNMBNBbW5';
use fcs;
GRANT SELECT ON fcs.* TO 'fcs_user'@'localhost';
GRANT INSERT ON fcs.* TO 'fcs_user'@'localhost';
GRANT DELETE ON fcs.* TO 'fcs_user'@'localhost';
FLUSH PRIVILEGES;