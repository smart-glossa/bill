<center><h1> SMARTGLOSSA BILL </h1> </center>
<p>SmartGlossa contributing this Project and its open sourced.</p>
<p> If you want to access this project online, go this link www.smartglossa.com/bill  </p>

<h2>Languages Used:</h2>
HTML, CSS, JS, JAVA and MySQL

<h2>Web Server:</h2>
Apache Tomcat

<h2>Framework:</h2>
1. Maven for Project Management
2. Jquery 

<h2>Requirements:</h2>
1. JDK 1.5 or above
2. Mysql
3. Eclipse with Tomcat Server configuration
4. Any Browser (e.g Chrome, FireFox, Safari, etc.,)

<h2>Installation Steps:</h2>

1. Create database "bill"
2. Update your Mysql configuration in BillConstants.java
3. Create the following tables in the "bill" database.

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `uname` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1

CREATE TABLE `image` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT,
  `image` mediumblob,
  `uname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`imageId`),
  UNIQUE KEY `username` (`uname`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`uname`) REFERENCES `user` (`uname`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1

CREATE TABLE `product` (
  `productId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `cost` float DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

CREATE TABLE `productImage` (
  `imageId` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `pimage` mediumblob,
  PRIMARY KEY (`imageId`),
  KEY `pid` (`pid`),
  CONSTRAINT `productimage_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`productId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1

