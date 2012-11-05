-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 05, 2012 at 12:17 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

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
  `MAIN_IMAGE_URL` varchar(200) NOT NULL,
  `IMAGE1_URL` varchar(200) NOT NULL,
  `IMAGE2_URL` varchar(200) NOT NULL,
  `IMAGE3_URL` varchar(200) NOT NULL,
  `IMAGE4_URL` varchar(200) NOT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `QUANTITY` bigint(20) NOT NULL,
  `UNITPRICE` float NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `IS_FEATURED` tinyint(1) NOT NULL,
  `CREATED_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PRODUCT_ID`, `MAKE`, `MODEL`, `DESCRIPTION`, `MAIN_IMAGE_URL`, `IMAGE1_URL`, `IMAGE2_URL`, `IMAGE3_URL`, `IMAGE4_URL`, `STATUS`, `QUANTITY`, `UNITPRICE`, `CATEGORY_ID`, `IS_FEATURED`, `CREATED_DT`) VALUES
(6, 'Canon', 'VIXIA HF M500 2.07 Megapixel Camcorder', 'Canon VIXIA HF M500 2.07 Megapixel Camcorder', 'http://c773974.r74.cf2.rackcdn.com/0387643_380733.jpg', '', '', '', '', 'Out of Stock', 0, 149.99, 4, 1, '2012-10-28 23:21:46'),
(5, 'Acer', 'Aspire S3-391-9606 13.3" Ultrabook', 'Intel Core i7-3517U', 'http://c773974.r74.cf2.rackcdn.com/0395057_713032.jpg', '', '', '', '', 'In Stock', 10, 399.99, 8, 1, '2012-10-28 23:12:47'),
(4, 'Hewlett-Packard', 'Pavilion g7-1368dx 17.3" Laptop Computer', 'Intel® Core i7-3770', 'http://c773974.r74.cf2.rackcdn.com/0382344_181040.jpg', '', '', '', '', 'In Stock', 10, 599.99, 2, 1, '2012-10-28 23:12:47'),
(7, 'Apple', 'Mac Pro MD770LL/A Desktop Computer', 'A towering achievement in power. With more processing power, more graphics performance, and more storage options than any other Mac, the possibilites with Mac Pro are limitless.', 'http://c773974.r74.cf2.rackcdn.com/0394840_690875.jpg', '', '', '', '', 'In Stock', 22, 2499.99, 1, 1, '2012-11-03 21:09:36'),
(8, 'Apple', 'Mac Pro MD772LL/A Server', 'A towering achievement in power. With more processing power, more graphics performance, and more storage options than any other Mac, the possibilities with Mac Pro are limitless.', 'http://c773974.r74.cf2.rackcdn.com/0394842_690909.jpg', '', '', '', '', 'In Stock', 15, 2999.99, 1, 1, '2012-11-03 21:11:22'),
(9, 'Apple', 'MacBook Air MD223LL/A 11.6" Laptop Computer - Silver', 'acBook Air MD223LL/A laptop computer. Now it''s even faster than it looks. With the latest Intel processors and graphics and faster flash storage, the ultimate notebook is better than ever.', 'http://c773974.r74.cf2.rackcdn.com/0394831_691006.jpg', '', '', '', '', 'In Stock', 20, 999.99, 2, 1, '2012-11-03 21:12:43'),
(10, 'Apple', 'MacBook Pro with Retina Display MC975LL/A 15.4" Laptop', 'Introducing MacBook Pro with Retina display. It''s a whole new vision for the notebook.', 'http://c773974.r74.cf2.rackcdn.com/0394829_692327.jpg', '', '', '', '', 'In Stock', 10, 2199.99, 2, 1, '2012-11-03 21:14:05'),
(11, 'Apple', 'Apple MacBook Laptop Computer Refurbished', 'The sleek MacBook cuts a slim profile on any desk (or in any backpack or briefcase). It features a seamless, durable unibody enclosure with rounded contours that make it easy to pick up and slide into and out of your bag. It''s made of rugged polycarbonate that withstands the rough and tumble of everyday life at school, at work, or while traveling. And since the bottom of the MacBook is covered in a nonslip material, it always stays right where you put it - whether it''s on a desk, table, or airplane tray. MacBook portability doesn''t happen at the expense of key features. The keyboard is full size, with keys that are curved to contain your fingers and highly responsive to your touch. Its low-profile design integrates perfectly into the unibody enclosure, so you''ll get a comfortable experience whether you''re typing a quick email or writing a lengthy research report.', 'http://c773974.r74.cf2.rackcdn.com/0394829_692327.jpg', '', '', '', '', 'In Stock', 10, 379.99, 2, 1, '2012-11-03 21:15:31'),
(12, 'Apple', 'MacBook Pro MC374LL/A 13.3" Laptop Computer', 'The sleek MacBook cuts a slim profile on any desk (or in any backpack or briefcase). It features a seamless, durable unibody enclosure with rounded contours that make it easy to pick up and slide into and out of your bag. It''s made of rugged polycarbonate that withstands the rough and tumble of everyday life at school, at work, or while traveling. And since the bottom of the MacBook is covered in a nonslip material, it always stays right where you put it - whether it''s on a desk, table, or airplane tray. MacBook portability doesn''t happen at the expense of key features. The keyboard is full size, with keys that are curved to contain your fingers and highly responsive to your touch. Its low-profile design integrates perfectly into the unibody enclosure, so you''ll get a comfortable experience whether you''re typing a quick email or writing a lengthy research report.', 'http://c773974.r74.cf2.rackcdn.com/0394040_660845.jpg', '', '', '', '', 'In Stock', 10, 849.99, 2, 1, '2012-11-03 21:16:54'),
(13, 'Apple', 'MacBook Pro with Retina Display MC975LL/A 15.4" Laptop Computer', 'Introducing MacBook Pro with Retina display. It''s a whole new vision for the notebook.', 'http://c773974.r74.cf2.rackcdn.com/0394829_692327.jpg', '', '', '', '', 'In Stock', 10, 2199.99, 2, 1, '2012-11-03 21:17:52'),
(14, 'Apple', 'MacBook Pro with Retina Display MC975LL/A 15.4" Laptop Computer', 'Introducing the 15.4-inch MacBook Pro. The state-of-the-art quad-core Intel i7 processor delivers up to 2x faster performance (over the previous generation of MacBook Pro). New Thunderbolt technology lets you connect high-performance peripherals and high-resolution displays to a single port, and transfer files at lightning speeds. And with the built-in FaceTime HD camera, you can make astonishingly crisp HD video calls. With the revolutionary Thunderbolt technology, you''ll be able to achieve bi-directional channels with transfer speeds to peripherals like external hard drives and servers up to an amazing 10 Gbps--while also connecting to a DisplayPort-compatible high-resolution display. As with previous models, the MacBook Pro features a precision unibody enclosure crafted from a single block of aluminum, creating a thin and light mobile computing workhorse (less than 1 inch thin and just 5.6 pounds) that''s also highly durable. It includes a 15.4-inch, LED-backlit glass display as well as a glass trackpad that doesn''t include a button (for larger tracking area) that features Apple''s Multi-Touch technology. And it comes standard with automatic graphics switching that provides performance when you need it and energy efficiency when you don''t. You''ll also get up to 7 hours of battery life while on the go. This version of the 15.4-inch MacBook Pro (model MD385LL/A) features a second-generation 2.5 GHz Core i7 quad-core processor, 750 GB hard drive, and 4 GB of installed RAM. Other features include ultra-fast Wireless-N Wi-Fi networking, Bluetooth connectivity, an SDXC card slot, two USB 2.0 ports, and a FireWire 800 port. Every Mac comes with OS X Lion, the latest release of the world''s most advanced desktop operating system. With over 250 features including Multi-Touch gestures, Mission Control, full-screen apps, and Launchpad, OS X Lion takes the Mac further than ever.', 'http://c773974.r74.cf2.rackcdn.com/0395764_734962.jpg', '', '', '', '', 'In Stock', 10, 2099.99, 2, 1, '2012-11-03 21:19:03'),
(15, 'ASUS', 'X53E-RH71 15.6" Laptop Computer - Black', 'he X Series X53E-RH71 laptop computer offers an expressive combination of technology and style. It boasts battery savings feature ASUS Power4Gear and Palm-Proof technology that prevents accidental input. The highly durable premium aluminum textures retain their looks and quality even during extensive, prolonged use, allowing you to do more in complete confidence.', 'http://c773974.r74.cf2.rackcdn.com/0386797_340042.jpg', '', '', '', '', 'In Stock', 10, 649.99, 2, 1, '2012-11-03 21:22:29'),
(16, 'ASUS', 'ROG G75VW-RH71 17.3" Laptop Computer', 'Bringing the breathtaking performance of a desktop gaming PC to a compact notebook has been a challenge, but this is exactly what the ROG G75VW-RH71 laptop computer achieves. Featuring the 3rd generation Intel Core processor, the all-new Stealth Aircraft-Inspired ASUS ROG G75VW is the ultimate fighting machine.', 'http://c773974.r74.cf2.rackcdn.com/0401593_991034.jpg', '', '', '', '', 'In Stock', 10, 1499.99, 2, 1, '2012-11-03 21:25:25'),
(17, 'ASUS', 'U47A-RS51 14.1" Laptop Computer - Silver', 'Bringing the breathtaking performance of a desktop gaming PC to a compact notebook has been a challenge, but this is exactly what the ROG G75VW-RH71 laptop computer achieves. Featuring the 3rd generation Intel Core processor, the all-new Stealth Aircraft-Inspired ASUS ROG G75VW is the ultimate fighting machine.', 'http://c773974.r74.cf2.rackcdn.com/0389587_462721.jpg', '', '', '', '', 'In Stock', 0, 699.99, 2, 1, '2012-11-03 21:26:36'),
(18, 'ASUS', 'X54C-MS91 15.6" Laptop Computer - Black', 'The X54C-MS91 laptop computer offers an expressive combinationof technology and style. It boasts battery savings feature ASUS Power4Gearand Palm-Proof technology that prevents accidental input. The highly durablepremium aluminum textures retain their looks and quality even duringextensive, prolonged use, allowing you to do more in complete confidence', 'http://c773974.r74.cf2.rackcdn.com/0386796_380949.jpg', '', '', '', '', 'In Stock', 0, 429.99, 2, 1, '2012-11-04 00:02:45'),
(19, 'ASUS', 'CM1745-US007S Desktop Computer', 'CM1745 Desktop Computer; Your daily multimedia center.', 'http://c773974.r74.cf2.rackcdn.com/0403326_058677.jpg', '', '', '', '', NULL, 10, 499.99, 1, 1, '2012-11-04 01:00:24'),
(20, 'ASUS', 'CM1831-US-2AA Desktop Computer', 'CM1745 Desktop Computer; Your daily multimedia center.', 'http://c773974.r74.cf2.rackcdn.com/0403322_058719.jpg', '', '', '', '', NULL, 0, 529.99, 1, 1, '2012-11-04 01:10:23'),
(21, 'Hewlett-Packard', 'Pavilion p7-1226s Desktop', 'The refurbished HP Pavilion p7-1226s desktop computer has been beautifully redesigned to fit seamlessly in almost anyhome environment. Whether youre surfing the Web, editing photos, mixing your soundtracksor creating your own home videos, HP Pavilion p7 desktop PCs offer a variety of ways tomake your computing experience richer and more rewarding. As you would expect from theleader in consumer PCs, every HP Pavilion p7 PC includes many intuitive features that makecapturing and sharing your digital experiences easier than ever.', 'http://c773974.r74.cf2.rackcdn.com/0393287_637355.jpg', '', '', '', '', NULL, 30, 349.99, 1, 1, '2012-11-04 01:13:00'),
(22, 'Hewlett-Packard', 'Pavilion p2-1120 Desktop Computer', 'With responsive energy-efficient performance, the refurbished HPPavilion p2-1120 Desktop Computer is more than just a pretty tower.', 'http://c773974.r74.cf2.rackcdn.com/0398272_899625.jpg', '', '', '', '', NULL, 30, 259.99, 1, 1, '2012-11-04 01:13:46'),
(23, 'Hewlett-Packard', ' dc7700 Desktop Computer', 'HP dc7700 Business Desktop PCs offer technologies that help solve customers'' problems, quality that is built in to help keep your business up and running smoothly, and expertise to provide superior service and value.', 'http://c773974.r74.cf2.rackcdn.com/0396865_788448.jpg', '', '', '', '', NULL, 22, 155.99, 1, 1, '2012-11-04 01:15:44'),
(24, 'Hewlett-Packard', 'dc7800 Desktop Computer', 'Small and expandable, HP Compaq Business dc7800 desktop computer is a toolless chassis for mainstream user applications. For businesses that need a higher-level of identity, data and network security.', 'http://c773974.r74.cf2.rackcdn.com/0387810_386466.jpg', '', '', '', '', NULL, 0, 159.99, 1, 1, '2012-11-04 01:16:41'),
(25, 'Hewlett-Packard', 'Pavilion HPE h8-1212c Desktop', 'Get serious about computing with the refurbished HP Pavilion HPE h8-1212 desktop computer, a stylish,high-performance PC for your most demanding digital tasks. Premium performance levelsenable an optimized experience whether youre working, e-mailing, editing photos or videos,gaming or connecting with friends online. The HP Pavilion HPE h8-1212 PC''s expandabilitylets you add devices and features to support your growing needs. The elegant accented designwill attract attention in any home dcor.', 'http://c773974.r74.cf2.rackcdn.com/0393278_658286.jpg', '', '', '', '', NULL, 0, 599.99, 1, 1, '2012-11-04 01:21:47'),
(26, 'Dell', 'XPS One 27 Desktop Computer', 'Get serious about computing with the XPS One 27 Desktop Computer desktop computer, a stylish,high-performance PC for your most demanding digital tasks. Premium performance levelsenable an optimized experience whether youre working, e-mailing, editing photos or videos,gaming or connecting with friends online. The XPS One 27 Desktop PC''s dependability you add devices and features to support your growing needs. The elegant accented design will attract attention in any home decor.', 'http://c773974.r74.cf2.rackcdn.com/0392894_632539.jpg', '', '', '', '', NULL, 0, 1599.99, 1, 1, '2012-11-04 20:54:06'),
(27, 'Dell', 'Inspiron 660s Desktop Computer', 'The space-saving Inspiron 660s desktop computer comes standard with wireless card, USB 3.0 and HDMI to help you quickly connect to your camera, MP3 player, printer and other devices. Also includes Americas Best Support.', 'http://c773974.r74.cf2.rackcdn.com/0401343_968446.jpg', '', '', '', '', NULL, 20, 299.99, 1, 1, '2012-11-04 20:54:57'),
(28, 'Dell', 'X8500 Desktop Computer', 'rackcdn', 'http://c773974.r74.cf2.rackcdn.com/0400615_951798.jpg', '', '', '', '', NULL, 20, 699.99, 1, 1, '2012-11-04 21:07:37');

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
(4, 'Cameras & Camcorders'),
(5, 'Phone Accessories'),
(6, 'Cameras Accessories'),
(7, 'Cell Phones & PDA'),
(8, 'Phone Accessories');

-- --------------------------------------------------------

--
-- Table structure for table `product_specification_map`
--

CREATE TABLE IF NOT EXISTS `product_specification_map` (
  `SPEC_MAP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(10) NOT NULL,
  `SPEC_ID` int(11) NOT NULL,
  PRIMARY KEY (`SPEC_MAP_ID`),
  KEY `SPEC_ID` (`SPEC_ID`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `product_specification_master`
--

CREATE TABLE IF NOT EXISTS `product_specification_master` (
  `SPEC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SPEC_NAME` varchar(100) NOT NULL,
  `SPEC_DESC` varchar(200) NOT NULL,
  `SPEC_CATEGORY` varchar(100) NOT NULL,
  PRIMARY KEY (`SPEC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product_specification_map`
--
ALTER TABLE `product_specification_map`
  ADD CONSTRAINT `product_specification_map_ibfk_1` FOREIGN KEY (`SPEC_ID`) REFERENCES `product_specification_master` (`SPEC_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
