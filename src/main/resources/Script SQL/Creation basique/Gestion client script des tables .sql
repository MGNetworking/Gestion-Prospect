/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de cr�ation :  29/02/2020 17:21:00                     */
/*==============================================================*/

-- drop schema if exists gestion cascade;
			
 --create schema gestion; 

drop table if exists adresse cascade;
drop table if exists client cascade;
drop table if exists prospect cascade;
drop table if exists societe cascade;

drop type if exists interetProsp;


/*==============================================================*/
/* Table : SOCIETE                                              */
/*==============================================================*/

create table societe
(
   societe_id           serial, 
   raison_sociale       varchar(50) not null,
   domainst             varchar(10) not null,
   telephone            varchar(15) not null,
   email                varchar(50) not null,
   commentaire			varchar(250) not null,

   constraint PK_SOCIETE primary key (societe_id)
    
);


/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table adresse
(
   adresse_id           serial,
   societe_id			integer not null,
   numero               integer not null,
   rue                  varchar(30) not null,
   cd_postale           varchar(5) not null,
   ville                varchar(30) not null,

   constraint PK_ADRESSE primary key (adresse_id),
   
      constraint FK_SOCIETE foreign key (societe_id) 
   			references societe (societe_id) on delete cascade
);


/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table client
(
   client_id            serial,
   societe_id           integer not null,
   chiffre_affaire      integer not null,
   employer_nb       	integer not null,

   constraint PK_CLIENT primary key (client_id),

   constraint FK_CLIENT_SOCIETE foreign key(societe_id) 
         	references societe(societe_id) on delete cascade
);

/*==============================================================*/
/* Table : PROSPECT                                             */
/*==============================================================*/

create table prospect
(
   prospect_id          serial,
   societe_id           integer not null,
   date_prospection     date not null,
   interet              varchar(3) not null,

   constraint PK_PROSPECT primary key (prospect_id),

   constraint FK_PROSPECT_SOCIETE foreign key (societe_id)
         references societe (societe_id) on delete cascade
);


