-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 21, 2022 at 10:20 AM
-- Server version: 5.7.18
-- PHP Version: 8.0.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `interactive-test`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `name` varchar(300) NOT NULL,
  `author` varchar(200) NOT NULL,
  `is_booked` varchar(20) DEFAULT NULL,
  `date_booked` date DEFAULT NULL,
  `date_booked_end` date DEFAULT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`name`, `author`, `is_booked`, `date_booked`, `date_booked_end`, `image`) VALUES
('The Book', 'M. Clifford', 'tester1', '2022-07-21', '2022-07-24', 'https://upload.wikimedia.org/wikipedia/commons/9/92/THE_BOOK_cover_image.png'),
('The Da Vinci Code', 'Dan Brown', NULL, NULL, NULL, 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/The-Da-Vinci-Code-book-cover.jpg?resize=500,896&quality=82&strip=all'),
('The Hobbit', 'J. R. R. Tolkien', NULL, NULL, NULL, 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/The-Hobbit-book-cover.jpg?resize=500,774&quality=82&strip=all'),
('The Little Prince', 'Antoine De Saint', NULL, NULL, NULL, 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/The-Little-Prince-book-cover.jpg?resize=500,706&quality=82&strip=all'),
('The Lord of The Rings', 'J. R. R. Tolkien', NULL, NULL, NULL, 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/Lord-of-the-Rings-cover.jpg?resize=500,752&quality=82&strip=all'),
('Don Quixote', 'Miguel De Cervantes', NULL, NULL, NULL, 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/Don-Quixote-cover.jpg?resize=500,766&quality=82&strip=all'),
('The Maidens', 'Alex Michaelides', NULL, NULL, NULL, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1608557750l/45300567.jpg'),
('The Greate Gatsby', 'F. Scott Fitzgerald', NULL, NULL, NULL, 'https://upload.wikimedia.org/wikipedia/commons/7/7a/The_Great_Gatsby_Cover_1925_Retouched.jpg'),
('The Ginger Man', 'J. P. Donleavy', 'tester1', '2022-07-21', '2022-07-24', 'https://bestlifeonline.com/wp-content/uploads/sites/3/2020/10/The-Ginger-Man-book-cover.jpg?resize=500,742&quality=82&strip=all'),
('Book of Night', 'Holly Black', NULL, NULL, NULL, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1639163872l/58293924.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('tester1', 'tester1'),
('tester2', 'tester2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
