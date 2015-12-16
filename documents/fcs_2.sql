-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2015 at 07:17 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fcs`
--

drop database fcs;

create database fcs;

use fcs;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (

  `GameID` int(11) NOT NULL AUTO_INCREMENT,

  `Player1` int(11) NOT NULL,

  `Player1UserName` varchar(20),

  `didPlayer1Fold` char(3),

  `Player1FinalHandRanking` varchar(40),

  `Player1Card1` char(30) NOT NULL,

  `Player1Card2` char(30) NOT NULL,
  `Player1BetCard2` DECIMAL(4,2) NOT NULL,

  `Player1Card3` char(30) NOT NULL,
  `Player1BetCard3` DECIMAL(4,2) NOT NULL,

  `Player1Card4` char(30) NOT NULL,
  `Player1BetCard4` DECIMAL(4,2) NOT NULL,

  `Player1Card5` char(30) NOT NULL,
  `Player1BetCard5` DECIMAL(4,2) NOT NULL,

  `TotalBetForWinner` DECIMAL(6,2) NOT NULL,

  `WinnerUserName` varchar(20) NOT NULL,


	

  `Player2` int(11) NOT NULL,

  `Player2UserName` varchar(20),

  `didPlayer2Fold` char(3),

  `Player2FinalHandRanking` varchar(40),

  `Player2Card1` char(30) NOT NULL,

  `Player2Card2` char(30) NOT NULL,
  `Player2BetCard2` DECIMAL(4,2) NOT NULL,

  `Player2Card3` char(30) NOT NULL,
  `Player2BetCard3` DECIMAL(4,2) NOT NULL,

  `Player2Card4` char(30) NOT NULL,
  `Player2BetCard4` DECIMAL(4,2) NOT NULL,

  `Player2Card5` char(30) NOT NULL,
  `Player2BetCard5` DECIMAL(4,2) NOT NULL,



  `GameStatus` char(30) ,

--   `SameState` blob,

  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GameID`),

  KEY `WinnerUserName` (`WinnerUserName`),

  KEY `Player1` (`Player1`),
  KEY `Player2` (`Player2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE IF NOT EXISTS `session` (
  `SessionID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(25) NOT NULL,
  `Token` char(36) NOT NULL,
  `Rand` bigint(11) NOT NULL,
  `ActionCount` int(11) NOT NULL,
  `LoginTime` datetime NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SessionID`),
  UNIQUE KEY `Token` (`Token`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `session`
--


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(20) NOT NULL,
  `Password` char(60) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `Password`) VALUES
(1, 'b1wolt', '$2a$12$BW5zGtaLGIbp.a13JusvZ.zQGqbBy8cW..02nCQT6wggregArnOhe');

INSERT INTO `users` (`UserID`, `UserName`, `Password`) VALUES
(2, 'LFreeman', '$2a$12$BW5zGtaLGIbp.a13JusvZ.zQGqbBy8cW..02nCQT6wggregArnOhe');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`Player1`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `game_ibfk_2` FOREIGN KEY (`Player2`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;


drop user 'fcs_user'@'localhost';

CREATE USER 'fcs_user'@'localhost' IDENTIFIED BY '7yXw8dDaNMBNBbW5';


GRANT SELECT ON fcs.* TO 'fcs_user'@'localhost';
GRANT INSERT ON fcs.* TO 'fcs_user'@'localhost';
GRANT DELETE ON fcs.* TO 'fcs_user'@'localhost';
GRANT UPDATE ON fcs.* TO 'fcs_user'@'localhost';

FLUSH PRIVILEGES;
