use fcs;
GRANT SELECT ON fcs.* TO 'fcs_user'@'localhost';
GRANT INSERT ON fcs.* TO 'fcs_user'@'localhost';
GRANT DELETE ON fcs.* TO 'fcs_user'@'localhost';
FLUSH PRIVILEGES;