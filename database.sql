-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 01, 2012 at 12:29 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ecom4j`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `EMAIL_ADDRESS` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `SECRET_QUESTION` varchar(500) NOT NULL,
  `SECRET_ANSWER` varchar(500) NOT NULL,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `ADDRESS2` varchar(100) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `STATE` varchar(20) NOT NULL,
  `ZIP_CODE` varchar(20) NOT NULL,
  `PHONE_NUMBER` varchar(20) NOT NULL,
  PRIMARY KEY (`EMAIL_ADDRESS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`EMAIL_ADDRESS`, `PASSWORD`, `SECRET_QUESTION`, `SECRET_ANSWER`, `FIRST_NAME`, `LAST_NAME`, `ADDRESS`, `ADDRESS2`, `CITY`, `STATE`, `ZIP_CODE`, `PHONE_NUMBER`) VALUES
('test@test.com', '6a1fb9c95ef656d8cf1b243a856a3c61', 'What was your childhood nickname?', '980ac217c6b51e7dc41040bec1edfec8', 'Test', 'Test', 'Test', 'test', 'test', 'Ohio', '43224', '1111111111'),
('test1@test.com', '6a1fb9c95ef656d8cf1b243a856a3c61', 'What was your childhood nickname?', '980ac217c6b51e7dc41040bec1edfec8', 'Test', 'Test', 'Test', 'test', 'test', 'Alaska', '43224', '1111111111'),
('test2@test.com', '6a1fb9c95ef656d8cf1b243a856a3c61', 'What was your childhood nickname?', '980ac217c6b51e7dc41040bec1edfec8', 'Test', 'Test', 'Test', '', 'test', 'Iowa', '43224', '1111111111'),
('test3@test.com', '6a1fb9c95ef656d8cf1b243a856a3c61', 'What was your childhood nickname?', '318b2739ddc2c16c97b33c9b04b79f3e', 'Test', 'tester', '1111 test Rd', 'ee', 'Columbus', 'Alabama', '43224', '6142540199'),
('test0@test.com', '77980f0ffba5044a9cde21cb07d7d08c', 'What school did you attend for sixth grade?', '47f555c26175453cb83e448f25cec713', 'Oussama', 'M Billah', '4519 Karl Rd ', 'Apt D', 'Columbus', 'Ohio', '43224', '614-333-3333'),
('test123@test.com', '4bbde07660e5eff90873642cfae9a8dd', 'What was your childhood nickname?', 'a0526f0f00044823d7d31b731d95d871', 'Oussama', 'Billah', '4519 Karl Rd #B', '', 'Columbus', 'Ohio', '43224', '');

-- --------------------------------------------------------

--
-- Table structure for table `customerorder`
--

CREATE TABLE IF NOT EXISTS `customerorder` (
  `ORDERID` bigint(20) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `STATUS` varchar(50) NOT NULL,
  `TOTALCOST` float NOT NULL,
  `ORDERDATE` date NOT NULL,
  PRIMARY KEY (`ORDERID`),
  KEY `USERNAME` (`USERNAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `orderitem`
--

CREATE TABLE IF NOT EXISTS `orderitem` (
  `ITEMID` bigint(20) NOT NULL,
  `PRODUCTID` varchar(50) NOT NULL,
  `ORDERID` bigint(20) NOT NULL,
  `QUANTITY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ITEMID`),
  KEY `PRODUCTID` (`PRODUCTID`),
  KEY `ORDERID` (`ORDERID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `PRODUCT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MAKE` varchar(300) DEFAULT NULL,
  `MODEL` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(2000) NOT NULL,
  `SRC` varchar(100) DEFAULT NULL,
  `CATEGORY` varchar(50) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `UNITPRICE` float NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `IS_FEATURED` tinyint(1) NOT NULL,
  `CREATED_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PRODUCT_ID`, `MAKE`, `MODEL`, `DESCRIPTION`, `SRC`, `CATEGORY`, `STATUS`, `QUANTITY`, `UNITPRICE`, `CATEGORY_ID`, `IS_FEATURED`, `CREATED_DT`) VALUES
(5, 'Acer', 'Aspire S3-391-9606 13.3" Ultrabook', 'Intel Core i7-3517U', 'http://c773974.r74.cf2.rackcdn.com/0395057_713032.jpg', NULL, 'In Stock', 10, 399.99, 2, 2, '2012-10-28 19:12:47'),
(4, 'Hewlett-Packard', 'Pavilion g7-1368dx 17.3" Laptop Computer', 'Intel® Core i7-3770', 'http://c773974.r74.cf2.rackcdn.com/0382344_181040.jpg', NULL, 'In Stock', 10, 599.99, 2, 1, '2012-10-28 19:12:47'),
(6, 'Canon', 'VIXIA HF M500 2.07 Megapixel Camcorder', 'Canon VIXIA HF M500 2.07 Megapixel Camcorder', 'http://c773974.r74.cf2.rackcdn.com/0387643_380733.jpg', NULL, 'Out of Stock', 0, 149.99, 4, 2, '2012-10-28 19:21:46');

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

CREATE TABLE IF NOT EXISTS `product_category` (
  `CATEGORY_ID` int(5) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`CATEGORY_ID`, `CATEGORY_NAME`) VALUES
(1, 'Desktops'),
(2, 'Laptops Notebooks'),
(3, 'Computer Accessories'),
(4, 'Digital Cameras & Camcorders'),
(5, 'Phone Accessories'),
(6, 'Cameras Accessories'),
(7, 'Cell Phones & PDA'),
(8, 'Phone Accessories');
