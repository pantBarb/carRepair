insert into user (email,password,user_type) values("ioannis@ioannou.gr","$2a$10$/1vpOmGiECBAdjklH8sfYexhlxWWrp5KA.wA9E4K2YjVf8ZI5asqe",false);
insert into user (email,password,user_type) values("georgios@georgiou.gr","$2a$10$/1vpOmGiECBAdjklH8sfYexhlxWWrp5KA.wA9E4K2YjVf8ZI5asqe",false);
insert into user (email,password,user_type) values("akis@akis.gr","$2a$10$/1vpOmGiECBAdjklH8sfYexhlxWWrp5KA.wA9E4K2YjVf8ZI5asqe",true);
insert into user (email,password,user_type) values("tsapas@gmail.gr","$2a$10$/1vpOmGiECBAdjklH8sfYexhlxWWrp5KA.wA9E4K2YjVf8ZI5asqe",true);
insert into member (address,firstname,lastname,vat,user_id)  values('Karpenisiou 3','loannis','Ioannou',123234345,1);
insert into member (address,firstname,lastname,vat,user_id)  values('Kavalas 12','Georgios','Georgiou',123345789,2);
insert into member (address,firstname,lastname,vat,user_id)  values('Athinas 8','Akis','Dimopoulos',456852397,3);

insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description1', '0', '11:45:00', false, '1');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description2', '1', '10:00:00', true, '2');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description3', '2', '08:45:00', false, '3');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description4', '0', '17:00:00', true, '1');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description5', '1', '19:15:00', false, '3');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description6', '2', '12:45:00', true, '2');
insert into repair ( date, description, status, time, type, user_id) VALUES ('2017-10-19', 'This is a description7', '0', '12:30:00', false, '1');