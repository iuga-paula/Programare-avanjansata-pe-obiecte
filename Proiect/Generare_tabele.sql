/*test sa ca vad daca merge conexiunea*/

CREATE TABLE Test(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				elem varchar(20) NOT NULL);
                
INSERT INTO  Test(elem) values ("creion");
INSERT INTO  Test(elem) values ("caiet");

SELECT * FROM Test;

/*tabelele pt FoodDelivery*/
drop tables soferi,adrese;
drop tables localuri, bauturi, deserturi, felurip;

CREATE TABLE Soferi(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				nume varchar(20) NOT NULL,
                prenume varchar(20) NOT NULL,
                tipContract varchar(30),
                salariu double(8,3),
                telefon varchar(10),
                unique(nume,prenume));

Delete from Soferi s where s.nume = 'Miahi';
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Ilie", "Casian Iulian", "part-time", 1500.00, "0772207605");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Antonescu", "Marius", "full-time", 1700.00, "0773467605");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Popa", "Gabriel", "part-time", 1600.50, "0774205909");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Dusescu", "Adrian", "full-time", 1678.30, "0772807699");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Ieftimie", "Antonio", "practica", 1000.00, "0787207005");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Dumitru", "Iustin", "part-time", 1300.00, "0772206020");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Georgescu", "Alex Andrei", "full-time", 1600.00, "0772707115");
Insert into Soferi(nume,prenume,tipContract,salariu,telefon) values ("Mantarosie", "Iulian", "full-time", 1698.90, "0777709105");
select * from Soferi;

CREATE TABLE Adrese(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				strada varchar(50) NOT NULL,
                nr int(3),
                bloc int(3),
                scara varchar(2),
                apartament int(3),
                localitate varchar(20) NOT NULL,
                judet varchar(20) NOT NULL,
                sector int(1),
                codPostal varchar(10));
  

Insert into Adrese(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal) values ("Bul. Dristor", 10, null, null, null, "Bucuresti", "Bucuresti" , 3, "302900");
Insert into Adrese(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal) values ("Bul. Tineretului", 15, null, null, null, "Bucuresti", "Bucuresti", 6, "302988");
Insert into Adrese(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal) values ("Str. Alexandru Vlahuta", 34, 2, "A", 1, "Bucuresti" , "Bucuresti", 1, "102995");
Insert into Adrese(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal) values ("Str. Centrului", 13, 3, "C", 22, "Bucuresti", "Bucuresti", 2, 306780);
select max(id) from adrese;
select * from adrese;

CREATE TABLE Localuri(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				denumire varchar(20) NOT NULL,
                timp_executie Time,
                timp_livrare Time,
                nrAngajati long,
                idAdresa int(6));

insert into Localuri(denumire, timp_executie, timp_livrare, nrAngajati, idAdresa) values("PuertoCafe", "00:30:15","00:45:00", 35, 1);
insert into Localuri(denumire, timp_executie, timp_livrare, nrAngajati, idAdresa) values("SalonGolescu","01:00:00","00:55:00", 45, 2);
insert into Localuri(denumire, timp_executie, timp_livrare, nrAngajati, idAdresa) values("CasaDori", "00:36:00", "00:30:00", 20, 3);
insert into Localuri(denumire, timp_executie, timp_livrare, nrAngajati, idAdresa) values("CremeriaEmille", "00:25:00", "00:30:00", 15, 4);
select * from localuri;
delete from localuri where id =12;

alter table localuri drop nrAngajati;
ALTER TABLE localuri
ADD CONSTRAINT un_l_name UNIQUE (denumire);



CREATE TABLE Bauturi(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				denumire varchar(20) NOT NULL,
                pret double(5,2),
                alcool boolean,
                carbogazificata boolean);


insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Aperol", 10.00, true, true);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Sampanie rose", 15.59, true, true);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Vin rosu", 11.49, true, true);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Dorna", 5.00, false, false);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Dorna", 5.50, false, true);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Vin alb", 10.00, true, false);
insert into Bauturi(denumire, pret, alcool, carbogazificata) values("Cappy", 6.50, false, false);
select * from bauturi;
delete from bauturi where id >=17;
update bauturi set carbogazificata = false where denumire = "Vin rosu";


CREATE TABLE Deserturi(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				denumire varchar(20) NOT NULL,
                pret double(5,2),
                vegan boolean);
insert into Deserturi(denumire, pret, vegan) values("Lava cake",12.00, false);
insert into Deserturi(denumire, pret, vegan) values("Lava cake",12.50, true);
insert into Deserturi(denumire, pret, vegan) values("Salam de biscuiti",10.00, true);
insert into Deserturi(denumire, pret, vegan) values("Clatite cu fructe", 9.50, true);
insert into Deserturi(denumire, pret, vegan) values("CheeseCake", 11.00, false);
select * from deserturi;

 CREATE TABLE FeluriP(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
					denumire varchar(70) NOT NULL,
					pret double(5,2),
                    garnitura varchar(50),
                    salata varchar(50));

insert into FeluriP(denumire, pret, garnitura,salata) values("Tochitura", 45.50, null, "de muraturi");
insert into FeluriP(denumire, pret, garnitura,salata) values("Curry de pui", 25.50, "orez", null);
insert into FeluriP(denumire, pret, garnitura,salata) values("Somon", 45.90, "cartofi copti",  "de cruditati");
insert into FeluriP(denumire, pret, garnitura,salata) values("Paella cu Fructe de mare", 35.00, "orez", null);
insert into FeluriP(denumire, pret, garnitura,salata) values("Caracatita", 40.00, "legume la gratar", null); 
insert into FeluriP(denumire, pret, garnitura,salata) values("Paella Vegetariana", 30.75, "orez", null);
insert into FeluriP(denumire, pret, garnitura,salata) values("Ficatei de pui", 25.00, "mamaliga", "de varza");
select * from felurip;
delete from felurip where id >=15;
                    
/*tabel asociativ pentru meniu -> produs cu resp local*/
CREATE TABLE Bauturi_Local(idLocal INT(6), 
					       idProdus INT(6),
                           primary key(idLocal, idProdus));
 
 insert into Bauturi_Local values(3,1); /*adaug bautura Aperol la Casa Dori*/
 insert into Bauturi_Local values(2,1); /*adaug bautura Aperol la Puerto Cafe*/
 insert into Bauturi_Local values(1,2); 
insert into Bauturi_Local values(1,3);
insert into Bauturi_Local values(2,3);
insert into Bauturi_Local values(3,3);
insert into Bauturi_Local values(3,4);
insert into Bauturi_Local values(4,4);
insert into Bauturi_Local values(1,5);
insert into Bauturi_Local values(2,5);
insert into Bauturi_Local values(3,5);
insert into Bauturi_Local values(1,6);
insert into Bauturi_Local values(3,7);
insert into Bauturi_Local values(4,7);
select * from bauturi_local;
CREATE TABLE Deserturi_Local(idLocal INT(6), 
					       idProdus INT(6),
                           primary key(idLocal, idProdus));
                           
insert into Deserturi_local values(2,1);
insert into Deserturi_local values(4,1);
insert into Deserturi_local values(4,2);
insert into Deserturi_local values(2,3);
insert into Deserturi_local values(3,3);
insert into Deserturi_local values(1,4);            
insert into Deserturi_local values(1,5);   
select * from deserturi_local;

CREATE TABLE FeluriP_Local(idLocal INT(6), 
					       idProdus INT(6),
                           primary key(idLocal, idProdus));   
 insert into felurip_local values(3,1);
insert into felurip_local values(1,2);
insert into felurip_local values(1,3);
insert into felurip_local values(1,4);
insert into felurip_local values(2,4);    
insert into felurip_local values(1,5);
insert into felurip_local values(1,6);
insert into felurip_local values(2,6);   
 insert into felurip_local values(3,7);
 select * from felurip_local;
 
 
SELECT table_name FROM information_schema.tables
WHERE table_schema = 'foodapp';

select count(*) from localuri where id = 2;
select count(*) from localuri where id = 100;

CREATE TABLE Comenzi(id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
				username varchar(20) NOT NULL,
                data_comanda DATE,
				total Double,
                discount Double,
                idLocal int(6),
                idSofer int(6));
 
 select * from Comenzi;
 
SELECT idProdus FROM Bauturi_Local b WHERE b.idLocal = 6;
