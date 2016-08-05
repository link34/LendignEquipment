 create database LendingEquipment ;

-- -----------------------------------------------------
-- Table `LendingEquipment`.`department`
-- -----------------------------------------------------
CREATE TABLE department (
  departmentid CHAR(8) NOT NULL,
  departmentname CHAR(15) NULL,
  PRIMARY KEY (departmentid));

-- -----------------------------------------------------
-- Table `LendingEquipment`.`category`
-- -----------------------------------------------------
CREATE TABLE category (
  categoryid CHAR(8) NOT NULL,
  categoryname CHAR(15) NULL,
  PRIMARY KEY (categoryid));

-- -----------------------------------------------------
-- Table `LendingEquipment`.`member`
-- -----------------------------------------------------
CREATE TABLE member (
  memberid CHAR(8) NOT NULL,
  pass CHAR(25) NULL,
  membername CHAR(15) NULL,
  iskaiinn boolean NULL,
  departmentid CHAR(8) NOT NULL,
  PRIMARY KEY (memberid),
  CONSTRAINT departmentid
    FOREIGN KEY (departmentid)
    REFERENCES department (departmentid)
    ON DELETE cascade
    ON UPDATE cascade);

-- -----------------------------------------------------
-- Table `LendingEquipment`.`Equipment`
-- -----------------------------------------------------
CREATE TABLE Equipment (
  equipmemtid CHAR(8) NOT NULL,
  equipmemtname CHAR(15) NULL,
  status CHAR(8) NULL,
  categoryid CHAR(8) NOT NULL,
  PRIMARY KEY (equipmemtid), 
  CONSTRAINT categoryid
    FOREIGN KEY (categoryid)
    REFERENCES category (categoryid)
    ON DELETE cascade
    ON UPDATE cascade);
-- -----------------------------------------------------
-- Table `LendingEquipment`.`borrowEquipment`
-- -----------------------------------------------------
CREATE TABLE borrowEquipment (
  borrowid serial NOT NULL,
  memberid CHAR(8) NOT NULL,
  equipmemtid CHAR(8) NOT NULL,
  borrowdate DATE NULL,
  returndate DATE NULL,
  comment VARCHAR(100) NULL,
  PRIMARY KEY (borrowid),  
  CONSTRAINT memberid
    FOREIGN KEY (memberid)
    REFERENCES member (memberid)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT equipmentid
    FOREIGN KEY (equipmemtid)
    REFERENCES Equipment (equipmemtid)
    ON DELETE cascade
    ON UPDATE cascade);

Insert into member(memberid,pass,membername,iskaiinn,departmentid) values ('EM-001','12345678','桜',false,'DE-003');
Insert into member(memberid,pass,membername,iskaiinn,departmentid) values ('EM-002','12345678','サボ',false,'DE-004');
Insert into member(memberid,pass,membername,iskaiinn,departmentid) values ('EM-003','12345678','田中',false,'DE-005');
Insert into member(memberid,pass,membername,iskaiinn,departmentid) values ('EM-004','12345678','真一',false,'DE-001');
Insert into member(memberid,pass,membername,iskaiinn,departmentid) values ('AD-001','12345678','山田',true,'DE-002');

Insert into department(departmentid,departmentname) values ('DE-001','営業本部');
Insert into department(departmentid,departmentname) values ('DE-002','管理本部');
Insert into department(departmentid,departmentname) values ('DE-003','ソリューション事業部');
Insert into department(departmentid,departmentname) values ('DE-004','ＩＣＴサビスセンター');
Insert into department(departmentid,departmentname) values ('DE-005','事業統制本部');

Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-001','ASPサービス','未使用','CT-004');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-002','WAS','未使用','CT-002');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-003','プリンタ','未使用','CT-001');
Insert into Equipment(equipmentid,equipmemtname,status,categoryid) values('EQ-004','UNIXサーバ','未使用','CT-003');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-005','ネットワーク仮想化','貸出中','CT-004');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-006','ロードバランサ','貸出中','CT-003');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-007','ブログサービス','貸出中','CT-001');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-008','UPS','廃棄','CT-001');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-009','CMS','貸出中','CT-002');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-010','リッチクライアント','廃棄','CT-002');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-011','デスクトップ仮想化','廃棄','CT-004');
Insert into Equipment(equipmemtid,equipmemtname,status,categoryid) values('EQ-012','サーバラック','廃棄','CT-003');

Insert into category(categoryid,categoryname) values('CT-001','IT機器');
Insert into category(categoryid,categoryname) values('CT-002','インターネット');
Insert into category(categoryid,categoryname) values('CT-003','サーバ・ストレージ');
Insert into category(categoryid,categoryname) values('CT-004','情報システム');

select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status
from Equipment join category on Equipment.categoryid = category.categoryid
where equipmemtid='EQ-011';

select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status
from Equipment join category on Equipment.categoryid = category.categoryid;


select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status
from Equipment join category on Equipment.categoryid = category.categoryid 
where equipmemtname Like '%プリン%';

select equi.equipmemtid,equi.equipmemtname,category.categoryid,category.categoryname,
equi.status,equi.borrowdate,equi.comment 
from (select borrowEquipment.equipmemtid,Equipment.equipmemtname,Equipment.status,Equipment.categoryid,
        borrowdate,comment from Equipment 
        join borrowEquipment 
        on borrowEquipment.equipmemtid = Equipment.equipmemtid) as equi 
join category 
on equi.categoryid = category.categoryid;

INSERT INTO borrowequipment(memberid,equipmemtid,borrowdate) VALUES('EM-001','EQ-011','2016-07-10');

Update borrowEquipment
set comment='使えてない'
where borrowEquipment.equipmemtid='EQ-001';

Update Equipment
set status='未使用',
where Equipment.equipmemtid='EQ-001';

Equipment.status='未使用',

select equi.equipmemtid,equi.equipmemtname,category.categoryid,
                    category.categoryname,equi.status,equi.borrowdate,equi.comment,equi.memberid,equi.borrowId 
                    from (select borrowEquipment.borrowId,borrowEquipment.memberId,borrowEquipment.equipmemtid,
                    Equipment.equipmemtname,
                    Equipment.status,Equipment.categoryid,borrowdate,comment 
                    from Equipment join borrowEquipment 
                    on borrowEquipment.equipmemtid = Equipment.equipmemtid) as equi 
                    join category on equi.categoryid = category.categoryid where equi.borrowdate ='2016-07-14' order by equi.borrowId asc;             

select equi.equipmemtid,equi.equipmemtname,category.categoryid,
                    category.categoryname,equi.status,equi.borrowdate,equi.comment,equi.memberid,equi.borrowId 
                    from (select borrowEquipment.borrowId,borrowEquipment.memberId,borrowEquipment.equipmemtid,
                    Equipment.equipmemtname,
                    Equipment.status,Equipment.categoryid,borrowdate,comment 
                    from Equipment join borrowEquipment 
                    on borrowEquipment.equipmemtid = Equipment.equipmemtid) as equi 
                    join category on equi.categoryid = category.categoryid where equi.equipmemtname Like '%U%' order by equi.borrowId asc;             

select equi.equipmemtid,equi.equipmemtname,category.categoryid,category.categoryname,equi.status,
equi.borrowdate,equi.comment,equi.memberid,equi.borrowId 
from (select borrowEquipment.borrowId,borrowEquipment.memberId,borrowEquipment.equipmemtid,
  Equipment.equipmemtname,Equipment.status,Equipment.categoryid,borrowdate,comment 
  from Equipment join borrowEquipment on borrowEquipment.equipmemtid = Equipment.equipmemtid) 
as equi join category on equi.categoryid = category.categoryid where equi.equipmemtname 
LIKE '%C%' order by equi.borrowId asc;

select equi.equipmemtid,equi.equipmemtname,equi.categoryid,equi.categoryname,equi.status,borrowdate,comment 
from borrowequipment inner join
(select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status from equipment
inner join category on equipment.categoryid = category.categoryid) as equi on borrowequipment.equipmemtid = equi.equipmemtid 
where equipmemtname LIKE '%AS%';


select equipmemtid,equipmemtname,category.categoryid,category.categoryname,status from equipment inner
join category on equipment.categoryid = category.categoryid 
where equipmemtname LIKE '%AS%';