# 纯JavaSE实现的房屋租赁系统

## 1.简介
#### 房屋租赁系统小项目的主要目的是让刚学完JavaSE的小伙伴对之前学的只是做一些运用，该项目应用的知识包括以下内容：
* **面向对象思想**
* **分成思想**
* **接口**
* **异常**
* **集合**
* **日期处理**
* **IO流**
* **反射**
* **泛型**
* **MySQL数据库**

#### 通过学习这个项目，可以巩固JavaSE的知识，本项目采用控制台控制数据的输入和展示，没有设计界面

### 1.1基本信息
#### 开发工具：IDEA
#### JDK版本：8
#### 项目编码：UTF-8
#### 默认登录用户和密码：admin

### 1.2使用技术
#### JavaSE的相关知识，该项目还使用了一些数据库的封装，采用德鲁伊数据库连接池，采用阿帕奇的DBUtils工具

### 1.3关联第三方jar包
#### 该项目引入了阿里巴巴的数据库连接池包德鲁伊Druid包，mysql的数据库连接包，阿帕奇的dbutils包

### 1.4项目概述
* **bean**：**存放实体类的包**
* **dao**：**存放用于实现对bean类操作各种增删改查的类的包，该包包括两种实现；一种实现是用文件对实体类bean实现存储的方式；另一种是通过数据库进行存储的方式**
* **service**：**调用dao层进行各种业务和服务处理**
* **view**：**接收用户输入并调用service层进行数据显示**
* **RentApp**: **主类**

## 2.项目功能
### 2.1主页面
#### 启动项目后，页面会提示用户登录页面或者直接看租房信息，登录页面是给管理员登录的，默认账户密码为：admin,直接看租房信息是给租客准备的功能，用户可以根据自己需要选择相应的功能，如下：
![ae806130e6c2ee64a0704196c055fdf8.png](en-resource://database/8547:1)
### 2.2登录页面，支持注册账号
![a58a09da73771012c5d0c055317120cc.png](en-resource://database/8549:1)
![5453fa4b53de075f7e2b2737c629e877.png](en-resource://database/8561:1)

### 2.3后台页面
![3399af9d38095f15e1bf562ecb7bc6f6.png](en-resource://database/8551:1)
### 2.4业主管理，支持增删改查
#### 删除业主时会先查看业主是否发布了信息，发布了无法删除，保持数据一致性
![60cc0a3c595170bfd092ea5cfb328381.png](en-resource://database/8553:1)
### 2.5房屋管理，支持增删改查
#### 删除房屋时会先查看房屋是否发布了学习，发布了无法删除，而且一个房屋只能发布一条信息
![73c26d1f483d54100469cb7ebb1bab3f.png](en-resource://database/8555:1)
### 2.6房屋信息管理
#### 支持发布房屋信息（业主与房屋进行绑定并发布租房信息）和删除查看等功能
![f6bbc03259831f3f9dc1eef4c36cb8c8.png](en-resource://database/8557:1)
### 2.7租客查看房屋信息，支持查看所有房屋信息和搜索功能
![97d4339ac318107cdc87d2f653da08cd.png](en-resource://database/8563:1)
### 2.8展示所以房屋信息
![8aa4c572c4bff65ff68a6c0f4adc82bb.png](en-resource://database/8565:1)
### 2.9搜索房屋信息
![b17fcc1ea07aab86ab7b57bf432634bd.png](en-resource://database/8567:1)

## 3.技术运用
### 3.1分层思想，该项目采用分层思想对代码进行逻辑上的划分，主要包括:
* **Bean(实体类)**
* **Dao(持久层）**
* **Service（业务层）**
* **View（界面层）**
#### 对dao层的使用，建立一个BasicDao类，使用泛型的方式接收各个实体类的，并传入路径对其进行增删改查操作。大大简化代码
### 3.2 Serializable接口
#### 一般使用文件处理时，即通过IO流传递对象时，需要实现此接口，此接口是一个标记接口，如果以后类可能加新的属性，记得加上serialVersionUID版本号，不然加上新的属性再通过IO流读取对象时会报错
### 3.3异常处理
#### 如果对文件进行处理，往往需要用到处理异常，可以将编译异常封装成运行时异常抛出，让调用者处理
### 3.4泛型
#### 对底层数据进行增删改查时，不同的类对象如果都写一个类进行增删改查，将要编写大量的代码，而且重复性很高，所以使用泛型将其封装成一个，减少代码量
### 3.5反射
#### 如果需要修改数据时，因为属性不确定，如果属性很多，一个个修改则需要写大量的代码，采用反射写一个工具类，对属性进行爆破赋值，减少代码量
## 4.程序实现
### 4.1采用分层实现代码布置
* **bean层**
![15317b52cd8022cfa0d29bcc2bbb4a56.png](en-resource://database/8569:1)
* **dao层（该层用接口管理，两种实现方法，一种数据库，一种文件流）**
![b510a0b74738fe99945eabfdc929ef8b.png](en-resource://database/8571:1)
* **service层**
![b4dac307438675f5e8788aeea207faf4.png](en-resource://database/8573:1)
* **view层**
![06882cedf078c1bc7f4b99ca01193cfb.png](en-resource://database/8575:1)
* **工具类**
![209ce1912e428f3c933d97e957525494.png](en-resource://database/8577:1)

### 4.2工具类实现实例
#### 代码实现请看源码，以做好相应注释

## 总结
### 该项目对刚学完JavaSE基础的同学非常适合，能运用到各种知识，大家也可以在此基础上进行扩展某个模块，比如租客下单、租客付款等功能，这涉及到数据一致性以及各种校验

## 欢迎各位大佬提出漏洞或者完善

### 备注：以下为数据库建表的语句，大家可以参考，如果采用数据库实现
```
CREATE DATABASE rentHouseSys

CREATE TABLE `host` (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL DEFAULT '',
phone VARCHAR(11) NOT NULL DEFAULT '',
`count` INT NOT NULL DEFAULT 0)
SELECT * FROM HOST

INSERT INTO `host` VALUES(NULL,'张伟荣','18392',0)
INSERT INTO HOST VALUES(NULL,'刘德才','13288',0)
UPDATE HOST SET NAME='黄伟荣',phone='18191' WHERE id =3
DELETE FROM HOST WHERE id=3

SHOW TABLE STATUS FROM renthousesys

CREATE TABLE house(
id INT PRIMARY KEY AUTO_INCREMENT,
address VARCHAR(10) NOT NULL DEFAULT '',
state VARCHAR(5) NOT NULL DEFAULT '',
rent INT NOT NULL DEFAULT 0,
isPublish BOOLEAN NOT NULL DEFAULT FALSE)

INSERT INTO house VALUES(NULL,'番禺区','未出租',500,FALSE)

SELECT * FROM house

CREATE TABLE `user` (
`name` VARCHAR(10) NOT NULL DEFAULT 'admin',
`password` VARCHAR(10) NOT NULL DEFAULT 'admin')

INSERT INTO USER VALUES('libin','libin')
INSERT INTO USER VALUES('admin','admin')

SELECT * FROM USER
SHOW TABLE STATUS FROM renthousesys

CREATE TABLE houseInfo(
id INT PRIMARY KEY AUTO_INCREMENT,
hostId INT ,
houseId INT ,
FOREIGN KEY (hostId) REFERENCES HOST(id),
FOREIGN KEY (houseId) REFERENCES house(id))
SELECT * FROM houseInfo

INSERT INTO houseInfo VALUES(NULL,2,1)
INSERT INTO houseInfo VALUES(NULL,3,2)

CREATE TABLE message(
id INT PRIMARY KEY AUTO_INCREMENT,
customerName VARCHAR(10),
phone VARCHAR(11),
message VARCHAR(255),
houseInfoId INT,
localDateTime DATETIME,
FOREIGN KEY (houseInfoId) REFERENCES houseInfo(id))

ALTER TABLE message ADD COLUMN isRead BOOLEAN DEFAULT FALSE AFTER localDatetime

ALTER TABLE message DROP COLUMN isRead

SELECT * FROM message


INSERT INTO message VALUES(NULL,'欧小欣','18199','有没有家电',1,NOW())
```
