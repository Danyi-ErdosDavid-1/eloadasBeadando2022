CREATE DATABASE IF NOT EXISTS `erettsegi` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `erettsegi`;

CREATE TABLE `vizsgazo` (
  `azon` int(11) NOT NULL auto_increment,
  `nev` varchar(50) NOT NULL,
  `osztaly` varchar(50) NOT NULL,
  PRIMARY KEY  (`azon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vizsgatargy` (
  `azon` int(11) NOT NULL auto_increment,
  `nev` varchar(50) NOT NULL,
  `szomax` int(11) NOT NULL,
  `irmax` int(11) NOT NULL,
  PRIMARY KEY  (`azon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `vizsga` (
  `vizsgazoaz` int(11) NOT NULL,
  `vizsgatargyaz` int(11) NOT NULL,
  `szobeli` int(11) NOT NULL,
  `irasbeli` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- az első 30 vizsgázót illesztjük be a vizsgazo táblába - a feladat megengedte, hogy ha nagyon sok az adat, akkor dolgozhatunk annak csak egy részével
INSERT INTO `vizsgazo` (`nev`, `osztaly`) VALUES
('Borbás Ferenc', '11/B'),
('Sima Dezső', '11/C'),
('Lajos Lajos', '11/C'),
('Lant János', '12/D'),
('Fogó Róbert', '11/C'),
('Pisty Mihály', '12/D'),
('Déri Béla', '11/B'),
('Nikk Gábor', '12/B'),
('Budai Tamás', '12/A'),
('Bog Aladár', '12/A'),
('Bálint Gábor', '11/B'),
('Fekete Zsolt', '12/E'),
('Kisó Ábel', '10/D'),
('Hámori Frigyes', '10/C'),
('Kosztolányi András', '11/B'),
('Sebes Vilmos', '11/A'),
('Pölös Ágost', '12/A'),
('Szűcs Lóránt', '12/D'),
('Weisz Richárd', '11/B'),
('Tóth Zoltán', '10/E'),
('Pók Béla', '12/E'),
('Stabb Ottó', '9/A'),
('Füst Szabolcs', '12/C'),
('Budai Ferenc', '9/A'),
('Szósz György', '9/A'),
('Fenyves Attila', '12/B'),
('Ilkei Béla', '11/B'),
('Eszes Győző', '12/C'),
('Izsó Gusztáv', '12/C'),
('Mind Ákos', '9/C');

-- beillesztjük az összes vizsgatárgyat a vizsgatargy táblába (nem sok van belőlük, ezért mindet)
INSERT INTO `vizsgatargy` (`nev`, `szomax`, `irmax`) VALUES
('Magyar nyelv és irodalom', 50, 100),
('Történelem',	60, 90),
('Matematika', 35, 115),
('Informatika',	30, 120),
('Fizika', 50, 100),
('Kémia', 50, 100),
('Angol', 30, 120),
('Német', 30, 120),
('Földrajz', 50, 100),
('Biológia', 50, 100);

-- az első 30 vizsgázóhoz tartozó vizsgaadatokat illesztjük be a vizsga táblába, ami 36 rekordot jelent, mivel több diák is több tárgyból vizsgázott
INSERT INTO `vizsga` (`vizsgazoaz`, `vizsgatargyaz`, `szobeli`, `irasbeli`) VALUES
(1,	10,	26,	87),
(2,	6, 20, 23),
(3,	2, 27, 29),
(4,	3, 4, 15),
(5,	2, 8, 78),
(6,	1, 18, 8),
(7,	2, 53, 49),
(8,	5, 24, 57),
(9,	9, 5, 51),
(10, 8, 14, 21),
(11, 5, 6, 32),
(12, 6, 16, 70),
(13, 4, 4, 55),
(14, 1, 7, 62),
(15, 8, 17, 56),
(16, 9, 5, 79),
(17, 6, 18, 32),
(18, 2, 28, 39),
(19, 5, 39, 56),
(20, 7, 25, 12),
(21, 3, 22, 35),
(22, 2, 27, 82),
(23, 6, 41, 60),
(24, 6, 18, 34),
(25, 1, 19, 70),
(25, 7, 12, 92),
(26, 4, 17, 92),
(26, 8, 5, 8),
(27, 7, 25, 28),
(27, 10, 41, 93),
(28, 9, 33, 6),
(28, 3, 17, 54),
(29, 7, 16, 99),
(29, 6, 14, 13),
(30, 10, 34, 42),
(30, 9, 20, 7);
