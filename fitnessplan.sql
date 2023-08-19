-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 12. Jul 2023 um 14:27
-- Server-Version: 10.4.27-MariaDB
-- PHP-Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `fitnessplan`
--
CREATE DATABASE IF NOT EXISTS `fitnessplan` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `fitnessplan`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `body_data`
--

CREATE TABLE `body_data` (
  `pk_id` int(11) NOT NULL,
  `fk_user_id` int(11) NOT NULL,
  `date` text NOT NULL,
  `weight` double NOT NULL,
  `fat` double NOT NULL,
  `muscle` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `body_data`
--

INSERT INTO `body_data` (`pk_id`, `fk_user_id`, `date`, `weight`, `fat`, `muscle`) VALUES
(2, 1, '2023-07-01', 73, 29, 39),
(3, 1, '2023-07-08', 73.2, 28, 40),
(5, 1, '2023-07-09', 73.6, 27, 40),
(7, 1, '2023-07-10', 73.1, 28, 41),
(12, 1, '2023-07-11', 73.5, 27, 41);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `exercise`
--

CREATE TABLE `exercise` (
  `pk_id` int(11) NOT NULL,
  `fk_exercise_list_id` int(11) DEFAULT NULL,
  `fk_plan_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `position` int(11) NOT NULL,
  `by_repeat` tinyint(1) NOT NULL,
  `sentence` int(11) NOT NULL,
  `weight` double NOT NULL,
  `repeat_exercise` int(11) NOT NULL,
  `time` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `exercise`
--

INSERT INTO `exercise` (`pk_id`, `fk_exercise_list_id`, `fk_plan_id`, `name`, `position`, `by_repeat`, `sentence`, `weight`, `repeat_exercise`, `time`) VALUES
(3, 5, 28, 'Nr. 12', 1, 1, 3, 40, 12, 3),
(4, 27, 28, 'Nr. 7', 2, 0, 0, 0, 0, 0),
(5, 13, 28, 'Nr.2', 3, 1, 0, 0, 0, 0),
(7, 25, 29, 'Durchstrecken', 1, 1, 3, 0, 13, 0),
(10, 19, 29, '', 2, 0, 0, 8, 0, 15);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `exercise_list`
--

CREATE TABLE `exercise_list` (
  `pk_id` int(11) NOT NULL,
  `fk_training_areas_id` int(11) DEFAULT NULL,
  `name` text NOT NULL,
  `img` text NOT NULL,
  `by_repeat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `exercise_list`
--

INSERT INTO `exercise_list` (`pk_id`, `fk_training_areas_id`, `name`, `img`, `by_repeat`) VALUES
(2, 1, 'Beinheben Schrägbank', '', 0),
(3, 1, 'Crunch', '', 0),
(4, 1, 'Crunch am Gerät', '', 1),
(5, 2, 'Abduktion - Maschine, sitzend', '', 1),
(6, 2, 'Abduktion am Boden', '', 0),
(7, 2, 'Abduktion am Kabel', '', 1),
(8, 2, 'Adduktoren am Kabel', '', 1),
(9, 3, 'Bizepscurls - SZ-Stange', '', 1),
(10, 3, 'Bizepscurls 21er', '', 1),
(11, 3, 'Bizepscurls am Kabelzug', '', 1),
(12, 3, 'Bizepscurls mit Kurzhantel', '', 1),
(13, 4, 'Bankdrücken', '', 1),
(14, 4, 'Bankdrücken - Trainingsgerät', '', 1),
(15, 4, 'Bankdrücken Kurzhantel, flach', '', 1),
(16, 4, 'Butterfly am Gerät', '', 1),
(17, 5, 'Crosstrainer', '', 0),
(18, 5, 'Fahrrad, liegend', '', 0),
(19, 5, 'Fahrrad, sitzend', '', 0),
(20, 5, 'Langlauf-Ski Ergometer', '', 0),
(21, 6, 'Bergsteiger', '', 0),
(22, 6, 'Burpee', '', 0),
(23, 6, 'Unterarmstütz - Planks', '', 0),
(24, 7, 'Good-Mornings', '', 0),
(25, 7, 'Hyperextensions', '', 1),
(26, 7, 'Hyperextensions - Maschine', '', 1),
(27, 7, 'Klimmzüge', '', 1),
(28, 8, 'Arnold Presse', '', 1),
(29, 8, 'Aufrechtes Rudern (Frontziehen) - Kurzhanteln', '', 1),
(30, 8, 'Aufrechtes Rudern (Frontziehen) - Langhanteln', '', 1),
(31, 8, 'Aufrechtes Rudern - Kabelzug', '', 1),
(32, 9, 'Dips', '', 1),
(33, 9, 'Dips an der Bank - Arnold-Dips', '', 1),
(34, 9, 'Dips an einem Stuhl', '', 1),
(35, 9, 'Enges Bankdrücken', '', 1),
(36, 10, 'Handgelenk-Beugen hinter dem Körper', '', 1),
(37, 10, 'Handgelenk-Beugen Kurzhantel', '', 1),
(38, 10, 'Handgelenk-Beugen Langhantel', '', 1),
(39, 10, 'Handgelenk-Strecken Kurzhantel', '', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `plan`
--

CREATE TABLE `plan` (
  `pk_id` int(11) NOT NULL,
  `fk_user_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `position` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `plan`
--

INSERT INTO `plan` (`pk_id`, `fk_user_id`, `name`, `position`, `active`) VALUES
(28, 1, 'Tag 1', 2, 1),
(29, 1, 'Tag 2', 1, 1),
(30, 1, 'Tag 3', 3, 1),
(34, 1, 'Tag 4', 4, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `training_areas`
--

CREATE TABLE `training_areas` (
  `pk_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `training_areas`
--

INSERT INTO `training_areas` (`pk_id`, `name`, `img`) VALUES
(1, 'Bauchmuskulatur', ''),
(2, 'Beine', ''),
(3, 'Bizeps', ''),
(4, 'Brustmuskulatur', ''),
(5, 'Cardio', ''),
(6, 'Ganzkörpertraining', ''),
(7, 'Rücken', ''),
(8, 'Schulter', ''),
(9, 'Trizeps', ''),
(10, 'Unterarm', '');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `pk_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`pk_id`, `name`, `email`, `password`) VALUES
(1, 'Mike', 'mike@gmail.com', '88888888');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `body_data`
--
ALTER TABLE `body_data`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `body_data_to_user` (`fk_user_id`);

--
-- Indizes für die Tabelle `exercise`
--
ALTER TABLE `exercise`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `exercise_to_plan` (`fk_plan_id`),
  ADD KEY `exercise_to_exercise_list` (`fk_exercise_list_id`);

--
-- Indizes für die Tabelle `exercise_list`
--
ALTER TABLE `exercise_list`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `exercise_to_training_areas` (`fk_training_areas_id`);

--
-- Indizes für die Tabelle `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`pk_id`),
  ADD KEY `plan_to_user` (`fk_user_id`);

--
-- Indizes für die Tabelle `training_areas`
--
ALTER TABLE `training_areas`
  ADD PRIMARY KEY (`pk_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`pk_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `body_data`
--
ALTER TABLE `body_data`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `exercise`
--
ALTER TABLE `exercise`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT für Tabelle `exercise_list`
--
ALTER TABLE `exercise_list`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT für Tabelle `plan`
--
ALTER TABLE `plan`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT für Tabelle `training_areas`
--
ALTER TABLE `training_areas`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `pk_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `body_data`
--
ALTER TABLE `body_data`
  ADD CONSTRAINT `body_data_to_user` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `exercise`
--
ALTER TABLE `exercise`
  ADD CONSTRAINT `exercise_to_exercise_list` FOREIGN KEY (`fk_exercise_list_id`) REFERENCES `exercise_list` (`pk_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `exercise_to_plan` FOREIGN KEY (`fk_plan_id`) REFERENCES `plan` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `exercise_list`
--
ALTER TABLE `exercise_list`
  ADD CONSTRAINT `exercise_to_training_areas` FOREIGN KEY (`fk_training_areas_id`) REFERENCES `training_areas` (`pk_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints der Tabelle `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_to_user` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`pk_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
