<center><h1> SMARTGLOSSA BILL </h1> </center>
<p>SmartGlossa contributing this Project and its open sourced.</p>
<p> If you want to access this project online, go this link <a href="http://www.smartglossa.com/bill" target="_blank">SmartGlossa Bill</a>  </p>

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
2. Add the following as system properties in Tomcat configuration
<p>i) bill.mysql=localhost</p>
<p>ii)bill.username=root</p>
<p>iii)bill.password=root</p>
<p>iv)bill.database=bill</p>
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


CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `name` varchar(100) default NULL,
  `address` varchar(100) default NULL,
  `phonenumber` bigint(20) default NULL,
  PRIMARY KEY  (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


 CREATE TABLE `salemetadata` (
  `saleId` int(11) NOT NULL,
  `billDate` varchar(50) default NULL,
  `vat` float default NULL,
  `discount` float default NULL,
  `billTotal` float default NULL,
  PRIMARY KEY  (`saleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |

 CREATE TABLE `salelineitems` (
  `saleLineId` int(11) NOT NULL auto_increment,
  `saleId` int(11) default NULL,
  `productId` int(11) default NULL,
  `quantity` float default NULL,
  `cost` float default NULL,
  PRIMARY KEY  (`saleLineId`),
  KEY `saleId` (`saleId`),
  KEY `productId` (`productId`),
  CONSTRAINT `salelineitems_ibfk_1` FOREIGN KEY (`saleId`) REFERENCES `salemetad
ata` (`saleId`) ON DELETE CASCADE,
  CONSTRAINT `salelineitems_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `produc
t` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 

CREATE TABLE `salepayment` (
  `payId` int(11) NOT NULL auto_increment,
  `saleId` int(11) default NULL,
  `payDate` varchar(50) default NULL,
  `paidAmount` float default NULL,
  PRIMARY KEY  (`payId`),
  KEY `saleId` (`saleId`),
  CONSTRAINT `salepayment_ibfk_1` FOREIGN KEY (`saleId`) REFERENCES `salemetadat
a` (`saleId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 

 purchaseLineItem | CREATE TABLE purchaseLineItem ( purchaseId int(11) DEFAULT NULL, productId int(11) DEFAULT NULL, purchaseLineId int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (purchaseLineId), KEY purchaseId (purchaseId), KEY productId (productId), CONSTRAINT purchaseLineItem_ibfk_1 FOREIGN KEY (purchaseId) REFERENCES purchaseMetaData (purchaseId) ON DELETE CASCADE, CONSTRAINT purchaseLineItem_ibfk_2 FOREIGN KEY (productId) REFERENCES product (productId) ON DELETE CASCADE
 
 purchaseMetaData | CREATE TABLE purchaseMetaData ( purchaseId int(11) NOT NULL, billDate date DEFAULT NULL, vat float DEFAULT NULL, discount float DEFAULT NULL, billTotal float DEFAULT NULL, PRIMARY KEY (purchaseId) )
 
 3.purchasePayment | CREATE TABLE purchasePayment ( purchaseId int(11) DEFAULT NULL, payId int(11) NOT NULL AUTO_INCREMENT, payDate date DEFAULT NULL, paidAmount float DEFAULT NULL, PRIMARY KEY (payId), KEY purchaseId (purchaseId), CONSTRAINT purchasePayment_ibfk_1 FOREIGN KEY (purchaseId) REFERENCES purchaseMetaData (purchaseId) ON DELETE CASCADE )
 
 
 
CREATE TABLE `expense` (
  `catid` int(11) NOT NULL,
  `catname` varchar(100) default NULL,
  PRIMARY KEY  USING BTREE (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
