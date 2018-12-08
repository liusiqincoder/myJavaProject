--数据库名称  Sold  SID:Sold
--创建管理员 manager  密码  123456
create user manager identified by 123456;
grant create session,dba to manager;

--创建表
CREATE TABLE ShopKeeper(
  id VARCHAR2(100) PRIMARY KEY ,
  count VARCHAR2(20) NOT NULL ,
  password VARCHAR2(20) NOT NULL ,
  name VARCHAR2(20) NOT NULL ,
  picture VARCHAR2(200) DEFAULT 'C:\Users\Administrator.PC-201807161523\Desktop\
                 服装销售系统\服装销售系统\src\Resource\Picture\Shopkeeper\default.png'
);
CREATE TABLE Manager(
  id VARCHAR2(100) PRIMARY KEY ,
  count VARCHAR2(20) NOT NULL ,
  password VARCHAR2(20) NOT NULL ,
  name VARCHAR2(20) NOT NULL ,
  picture VARCHAR2(200) DEFAULT 'C:\Users\Administrator.PC-201807161523\Desktop\
                 服装销售系统\服装销售系统\src\Resource\Picture\Manager\default.png'
);
CREATE TABLE Solder(
  id VARCHAR2(100) PRIMARY KEY ,
  count VARCHAR2(20) NOT NULL ,
  password VARCHAR2(20) NOT NULL ,
  name VARCHAR2(20) NOT NULL ,
  picture VARCHAR2(200) DEFAULT 'C:\Users\Administrator.PC-201807161523\Desktop\
                 服装销售系统\服装销售系统\src\Resource\Picture\Solder\default.png'
);
CREATE TABLE Good(
  id VARCHAR2(100) PRIMARY KEY ,
  name VARCHAR2(20) NOT NULL ,
  price NUMBER(5,2) DEFAULT 0.00,
  procedureDate DATE,
  picture VARCHAR2(200) DEFAULT 'C:\Users\Administrator.PC-201807161523\Desktop\
                 服装销售系统\服装销售系统\src\Resource\Picture\Good\default.png'
);
CREATE TABLE Good_Stock (
  stock_id VARCHAR2(100) PRIMARY KEY ,
  good_id  VARCHAR2(100) REFERENCES Good(id) ON DELETE CASCADE ,
  num INT DEFAULT 0 CHECK (num>=0)
);
CREATE TABLE Good_Sold (
  --售卖编号
  goodSold_id VARCHAR2(100) PRIMARY KEY ,
  --销售员ID
  sold_id  VARCHAR2(100) REFERENCES Solder(id) ON DELETE CASCADE ,
  --商品ID
  good_id VARCHAR2(100) REFERENCES Good(id) ON DELETE CASCADE ,
  --销售数量
  num INT DEFAULT 0 CHECK (num>=0),
  --销售时间
  sold_date DATE DEFAULT SYSDATE
);

CREATE TABLE users(
  --用户id
  id VARCHAR2(100) PRIMARY KEY ,
  --用户职位
  job VARCHAR2(20) CHECK (job IN ('Manager','ShopKeeper','Solder')),
  --用户账号
  count VARCHAR2(20)
);

CREATE TABLE user_defy(
  --主键
  userDefy_id VARCHAR2(100) PRIMARY KEY ,
  --用户id
  user_id VARCHAR2(100) REFERENCES users(id) ON DELETE CASCADE ,
  --用户拒绝id为defy_id的来信
  defy_id VARCHAR2(100) REFERENCES users(id) ON DELETE CASCADE
);

-- DROP TABLE system_log;
--暂时没啥用
CREATE TABLE system_log(
  --日志编号
  log_id VARCHAR2(100) PRIMARY KEY ,
  --事件的发起人的职位
  job VARCHAR2(20) CHECK (job IN ('Manager','ShopKeeper','Solder')),
  --事件的发起人
  userId VARCHAR2(100) REFERENCES users(id) ON DELETE CASCADE ,
  --事件内容
  thing VARCHAR2(500),
  --事件完成时间
  log_date TIMESTAMP DEFAULT SYSDATE,
  --事件完成状态  0代表失败，1代表成功
  status SMALLINT DEFAULT 0
);

CREATE TABLE letter(
  --信件编号
  letter_id VARCHAR2(100) PRIMARY KEY ,
  --信件发出者
  from_id VARCHAR2(100) REFERENCES users(id) ON DELETE CASCADE ,
  --信件接收者
  to_id VARCHAR2(100) REFERENCES users(id) ON DELETE CASCADE ,
  --信件内容
  message VARCHAR2(500),
  --信件发出时间
  send_date DATE DEFAULT SYSDATE
);
