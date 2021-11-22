/*create  database gl;*/
create table user (
ID int auto_increment primary key,
email varchar(50),
password varchar  (50),
typeuser varchar(20)
);
insert into user(email,password,typeuser) values ('lazalokmane@gmail.com','0000','admin');
insert into user(email,password,typeuser) values ('Aimen@gmail.com','0000','dounneur');
create table donneur(
ID int primary key,
nom varchar(20),
prenom varchar(20),
adresse varchar(20),
numtel varchar(20),
genre varchar(20),
grpsn varchar(20),
remarque varchar(20),
age int,
foreign key (ID) references user(ID)
);
insert into donneur values(2,'Aimen','Hammani',
'birkhadem','06555522','Homme','A+','',19);

create table RDV (
IDRDV int auto_increment primary key,
IDonneur int ,
typededons varchar(20),
grpsn varchar(20),
datedon date,
foreign key (IDonneur) references user(ID)
);
create table evenement(
    IDEVNT int auto_increment primary key,
    type varchar(20),
    nom varchar(30),
    dateEv Date);
create table don(
    IDDon int auto_increment primary key,
    IDdonnateur int,
    hospital varchar(20),
    groupeSanguin varchar(20),
    infermierMedcin varchar(20),
    typeDon varchar(20),
    passe bool,
    pasDeSacDeSang bool,
    dateDeCollect Date,
    dateDexpiration Date,
    foreign key (IDdonnateur) references donneur(ID)
);
 alter table don add(maladies varchar(50) ,instock bool);
 
 create table demdon(
        ID int auto_increment primary key,
        groupeSanguin varchar(20),
        objectif varchar(20),
        remarque varchar(50),
        typeDon varchar(20),
        etat varchar(20),
        IDdonneur int,
        recepteur varchar(50),
        pasDeSacs boolean,
        dateDemmande Date,
        foreign key (IDdonneur) references donneur (ID)
        );
	create table transaction(
    IDtransaction int auto_increment primary key,
    IDdemmande int,
    typeDon varchar(20),
    IDdonneur int,
	recepteur varchar(50),
    dateTransaction Date,
    foreign key (IDdonneur) references demdon (IDdonneur),
	foreign key (IDdemmande) references demdon (ID)
);
create view stock as select IDDon,IDdonnateur,hospital,groupeSanguin,typeDon,dateDeCollect,dateDexpiration from don where instock=true ;
