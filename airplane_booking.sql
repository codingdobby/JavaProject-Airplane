drop database test; /*test 테이블 지우기*/
create database test;/*test 테이블 생성하기*/
use test;/*test 테이블 사용하기*/

create table passenger(
	passid varchar(20) primary key,
    passpwd varchar(20),
    pwdck varchar(20),
    hname varchar(20),
    ename varchar(20),
    birth date,
    tel varchar(15),
    gender varchar(5),
    email varchar(50),
    address varchar(100));
  	


create table manager(
manid varchar(20),
manpwd varchar(20)
);


/**비행기정보***/
create table airtype(
 
airtypecode int AUTO_INCREMENT primary key,
airname varchar(20)


);


/**공항 이름***/
create table airport(
portname varchar(20)  primary key,      
portlocation varchar(20)
);


create table departure(
depcode int AUTO_INCREMENT primary key,
dep_fk varchar(20),
 FOREIGN KEY(dep_fk) REFERENCES airport(portname)

);




create table arrive(
arrcode int AUTO_INCREMENT primary key,
arr_fk varchar(20),
 FOREIGN KEY(arr_fk) REFERENCES airport(portname)

);



create table fares(

farecode int AUTO_INCREMENT,
depcode_fk int,
arrcode_fk int ,

PRIMARY KEY(farecode,arrcode_fk,depcode_fk),
 FOREIGN KEY(arrcode_fk) REFERENCES arrive(arrcode),
  FOREIGN KEY(depcode_fk) REFERENCES departure(depcode),
oneway varchar(20)

);


create table schedule(

scheduleid int AUTO_INCREMENT primary key,
flightnumber varchar(20),
depdate	date,
deptime time,
arrdate date,
arrtime time,

airtypecode_fk int,
depport_Code_fk int,
arrport_Code_fk int,

  FOREIGN KEY(airtypecode_fk) REFERENCES airtype(airtypecode),
   FOREIGN KEY(depport_Code_fk) REFERENCES departure(depcode),
  FOREIGN KEY(arrport_Code_fk) REFERENCES arrive(arrcode)

  
);



create table totalBook(
totBookid int AUTO_INCREMENT primary key,
bookid_fk varchar(20),
scheduleid_fk int,
bookdate Timestamp,
seat int,

Foreign key (bookid_fk) References passenger(passid),
Foreign key (scheduleid_fk) References schedule(scheduleid) 



);

