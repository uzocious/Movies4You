-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 12, 2022 at 05:07 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies4you`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Email`, `Password`) VALUES
('uzocious@uu.com', '111');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `Email` varchar(50) NOT NULL,
  `DisplayName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Email`, `DisplayName`, `Password`) VALUES
('uzoc@gtm.com', 'uzocious', '•0ðà®#…ËhÔÁÄò');

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
CREATE TABLE IF NOT EXISTS `genre` (
  `Genre` varchar(50) NOT NULL,
  PRIMARY KEY (`Genre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`Genre`) VALUES
('Action'),
('Adventure'),
('Animation'),
('Comedy'),
('Crime'),
('Horror'),
('Romance'),
('Sci-Fi'),
('Sport'),
('Thriller');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `MovieID` varchar(50) NOT NULL,
  `Title` varchar(250) NOT NULL,
  `Genre` varchar(50) NOT NULL,
  `Description` varchar(2000) NOT NULL,
  `Year` varchar(25) NOT NULL,
  `Quality` varchar(25) NOT NULL,
  `Rating` varchar(10) NOT NULL,
  `Duration` varchar(10) NOT NULL,
  `RentPrice` varchar(10) NOT NULL,
  `BuyPrice` varchar(10) NOT NULL,
  `Trailer` varchar(500) NOT NULL,
  PRIMARY KEY (`MovieID`),
  KEY `Genre` (`Genre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`MovieID`, `Title`, `Genre`, `Description`, `Year`, `Quality`, `Rating`, `Duration`, `RentPrice`, `BuyPrice`, `Trailer`) VALUES
('img1', 'Bad Boys for Life', 'Action', 'Bad Boys for Life is a 2020 American action comedy film directed by Adil El Arbi and Bilall Fallah and written by Chris Bremner, Peter Craig and Joe Carnahan, from a story by Craig and Carnahan. The sequel to Bad Boys II (2003), it is the third installment in the Bad Boys franchise, and stars Will Smith, Martin Lawrence, Vanessa Hudgens, Alexander Ludwig, Charles Melton, Paola Núñez, Kate del Castillo, Nicky Jam, and Joe Pantoliano. Smith co-produced the film with Jerry Bruckheimer and Doug Belgrad. In Bad Boys for Life, Miami detectives Mike Lowrey and Marcus Burnett investigate a string of murders tied to Lowrey\'s troubled past.', '2020', 'HD-4K', '7.2', '2h 4m', '5.49', '9.99', 'https://www.youtube.com/watch?v=jKCj3XuPG8M'),
('img2', 'John Wick', 'Action', 'The story focuses on John Wick (Reeves) searching for the men who broke into his home, stole his vintage car and killed his puppy, which were a last gift to him from his recently deceased wife (Moynahan). Stahelski and David Leitch directed the film together, though only Stahelski was credited.', '2014', 'HD-4K', '7.5', '1h 41m', '3.49', '9.99', 'https://www.youtube.com/watch?v=2AUmvWm5ZDQ'),
('img3', 'Shrek the Third', 'Adventure', 'Shrek the Third (2007) When his new father-in-law, King Harold falls ill, Shrek is looked at as the heir to the land of Far, Far Away. Not one to give up his beloved swamp, Shrek recruits his friends Donkey and Puss in Boots to install the rebellious Artie as the new king.', '2007', 'HD-4K', '6.1', '1h 33m', '2.49', '7.99', 'https://www.youtube.com/watch?v=_MoIr7811Bs'),
('img4', 'Avengers: Infinity War', 'Adventure', 'A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment, the fate of Earth and existence has never been more uncertain.', '2018', 'HD-4K', '8.7', '2h 40m', '2.49', '9.99', 'https://www.youtube.com/watch?v=6ZfuNTqbHE8'),
('img5', 'Deadpool', 'Comedy', 'Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life. Wade Wilson is a small-time mercenary. He meets Vanessa and falls in love. ... Things look bleak but a man appears who says he can be cured, through a treatment that gives him superhero powers.', '2016', 'HD-4K', '8.3', '1h 49m', '3.49', '9.99', 'https://www.youtube.com/watch?v=Xithigfg7dA'),
('img6', 'Uncle Drew', 'Comedy', 'UNCLE DREW is a Summit Entertainment release produced by Temple Hill in association with PepsiCo\'s Creators League Studios. Director Charles Stone III\'s anti-ageist basketball comedy revolves around a young black man (Lil Rel Howery) and his dream of winning the Rucker Classic street ball tournament in Harlem.', '2018', 'HD-4K', '5.7', '1h 44m', '2.49', '9.99', 'https://www.youtube.com/watch?v=9H2SSvQ8ihA'),
('img7', 'Joker', 'Crime', 'In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.', '2019', 'HD-4K', '8.9', '2h 2m', '5.49', '12.99', 'https://www.youtube.com/watch?v=zAGVQLHvwOY'),
('img8', 'Hancock', 'Crime', 'Hancock is a 2008 American superhero film directed by Peter Berg and starring Will Smith, Charlize Theron, and Jason Bateman. It tells the story of a vigilante superhero, John Hancock (Smith) from Los Angeles whose reckless actions routinely cost the city millions of dollars.', '2008', 'HD-4K', '6.4', '1h 42m', '1.49', '7.99', 'https://www.youtube.com/watch?v=SkX1VuXLRSc'),
('img9', 'Big Hero 6', 'Animation', 'Big Hero 6 is a 2014 American 3D computer animated superhero film produced by Walt Disney Animation Studios and released by Walt Disney Pictures. ... Directed by Don Hall and Chris Williams, the film tells the story of Hiro Hamada, a young robotics prodigy who forms a superhero team to combat a masked villain.', '2014', 'HD-4K', '8.1', '1h 48m', '3.49', '9.99', 'https://www.youtube.com/watch?v=z3biFxZIJOQ'),
('img10', 'Toy Story 4', 'Animation', 'It is the fourth installment in Pixar\'s Toy Story series and the sequel to Toy Story 3 (2010). ... They are joined by Forky, a spork that Bonnie has made into a toy, and embark on a road trip adventure and run into an old friend from Andy\'s house.', '2019', 'HD-4K', '8.2', '1h 40m', '4.49', '9.99', 'https://www.youtube.com/watch?v=wmiIUN-7qhE'),
('img11', 'Yip Man', 'Sport', 'Ip Man is a 2008 biographical martial arts film based on the life of Ip Man, a grandmaster of the martial art Wing Chun and teacher of Bruce Lee. The film focuses on events in Ip\'s life that supposedly took place in the city of Foshan during the Sino-Japanese War.', '2008', 'HD-4K', '8.1', '1h 48m', '0.99', '5.99', 'https://www.youtube.com/watch?v=djQ68oa-mvc'),
('img12', 'Creed II', 'Sport', 'Under the tutelage of Rocky Balboa, newly crowned heavyweight champion Adonis Creed faces off against Viktor Drago, the son of Ivan Drago. Years after Adonis Creed made a name for himself under Rocky Balboa\'s mentorship, the young boxer becomes the Heavyweight Champion of the World.', '2018', 'HD-4K', '7.7', '2h 10m', '6.99', '9.99', 'https://www.youtube.com/watch?v=cPNVNqn4T9I'),
('img13', 'Uncut Gems', 'Thriller', 'The film stars Adam Sandler as Howard Ratner, a Jewish jeweler and gambling addict in New York City\'s Diamond District, who must retrieve an expensive gem he purchased to pay off his debts.', '2019', 'HD-4K', '8.1', '2h 40m', '5.99', '12.99', 'https://www.youtube.com/watch?v=vTfJp2Ts9X8'),
('img14', 'Casino Royale', 'Thriller', 'Casino Royale is a 2006 spy film, the twenty-first in the Eon Productions James Bond series, and the third screen adaptation of Ian Fleming\'s 1953 novel of the same name. ... Casino Royale takes place at the beginning of Bond\'s career as Agent 007, as he is earning his licence to kill.', '2006', 'HD-4K', '8.0', '2h 25m', '2.49', '7.99', 'https://www.youtube.com/watch?v=36mnx8dBbGE'),
('img15', 'The Hunger Games', 'Sci-Fi', 'The story takes place in a dystopian post-apocalyptic future in the nation of Panem, where a boy and a girl from each of the nation\'s 12 Districts are chosen annually as \"tributes\" and forced to compete in The Hunger Games, an elaborate televised fight to the death.', '2012', 'HD-4K', '7.3', '2h 22m', '2.49', '5.99', 'https://www.youtube.com/watch?v=EAzGXqJSDJ8'),
('img16', 'Jurassic World: Fallen Kingdom', 'Sci-Fi', 'Intent on rescuing the remaining rampant dinosaurs that now roam free on Isla Nublar\'s vast landscapes, Owen Grady and Claire Dearing return to the now-ruined Jurassic World theme park, against the backdrop of an imminent and utterly devastating volcanic activity.', '2018', 'HD-4K', '6.4', '2h 4m', '2.49', '7.99', 'https://www.youtube.com/watch?v=vn9mMeWcgoM'),
('img17', 'Fifty Shades of Grey', 'Romance', 'Fifty Shades of Grey is the story of a 21-year-old college student, Anastasia, who begins a relationship with a 27-year-old successful, powerful businessman named Christian Grey. They meet when she interviews him for her college newspaper.', '2015', 'HD-4K', '4.2', '2h 9m', '2.49', '7.99', 'https://www.youtube.com/watch?v=SfZWFDs0LxA'),
('img18', 'Titanic', 'Romance', 'Titanic is a 1997 American epic romantic disaster movie. It was directed, written, and co-produced by James Cameron. The movie is about the 1912 sinking of the RMS Titanic. ... They fall in love after meeting aboard the ship, but it was not good for a rich girl to fall in love with a poor boy in 1912.', '1997', 'HD-4K', '7.7', '3h 15m', '3.49', '9.99', 'https://www.youtube.com/watch?v=kVrqfYjkTdQ'),
('img19', 'Raw', 'Horror', 'Raw (French: Grave) is a 2016 French-Belgian horror drama film written and directed by Julia Ducournau, and starring Garance Marillier. The plot follows a young vegetarian\'s first year at veterinary school when she tastes meat for the first time and develops a craving for flesh.', '2016', 'HD-4K', '7.0', '1h 39m', '2.49', '5.99', 'https://www.youtube.com/watch?v=gFlXVX2af_Y'),
('img20', 'Wolfen', 'Horror', 'Wolfen is a 1981 American crime horror film directed by Michael Wadleigh, and based on Whitley Strieber\'s 1978 novel The Wolfen. It stars Albert Finney, Diane Venora, Gregory Hines and Edward James Olmos. The film follows a city cop who has been assigned to uncover what is behind a series of vicious murders.', '1981', 'HD-4K', '6.3', '1h 55m', '3.49', '9.99', 'https://www.youtube.com/watch?v=L46RneepoxQ');

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE IF NOT EXISTS `purchase` (
  `PurchaseID` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Price` varchar(10) NOT NULL,
  `Date` varchar(10) NOT NULL,
  `MovieID` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  PRIMARY KEY (`PurchaseID`),
  KEY `MovieID` (`MovieID`),
  KEY `Email` (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
