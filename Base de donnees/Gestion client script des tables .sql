/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  17/02/2020 14:56:57                      */
/*==============================================================*/

-- create schema gestionClient;
-- drop schema if exists gestionClient;

SET foreign_key_checks = 0;

drop table if exists ADRESSE;
drop table if exists CLIENT;
drop table if exists CONTRAT;
drop table if exists CONTRAT_CLIENT;
drop table if exists SOCIETE_ADRESSE;
drop table if exists PROSPECT;
drop table if exists SOCIETE;

SET foreign_key_checks = 1;

/*==============================================================*/
/* Table : CONTRAT                                              */
/*==============================================================*/
create table CONTRAT
(
   ID_CONTRAT           bigint not null,
   TITRE                char(50) not null,
   DESCRIPTION          char(50) not null,
   DATEDEBUT            date not null,
   DATEFIN              date,

   constraint PK_CONTRAT primary key (ID_CONTRAT)

);

/*==============================================================*/
/* Table : ADRESSE                                              */
/*==============================================================*/
create table ADRESSE
(
   ID_ADRESSE           int not null,
   NUMERO               varchar(14) not null,
   RUE                  varchar(50) not null,
   CODEPOSTALE          varchar(20) not null,
   VILLE                varchar(50) not null,

   constraint PK_ADRESSE primary key (ID_ADRESSE)
);

/*==============================================================*/
/* Table : SOCIETE                                              */
/*==============================================================*/
create table SOCIETE
(
   ID_SOCIETE           int not null,
   RAISONSOCIALE        varchar(50) not null,
   DOMAIN               char(6) not null,
   TELEPHONE            int not null,
   EMAIL                varchar(50) not null,

   constraint FK_SOCIETE primary key (ID_SOCIETE)
);


/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT
(
   ID_CLIENT            int not null,
   ID_SOCIETE           int not null,
   CHIFFREAFFAIRE       int not null,
   NOMBREEMPLOYER       int not null,

   constraint PK_CLIENT primary key (ID_CLIENT),

   constraint FK_CLIENT_SOCIETE foreign key(ID_SOCIETE) 
         references SOCIETE(ID_SOCIETE) 
);

/*==============================================================*/
/* Table : PROSPECT                                             */
/*==============================================================*/
create table PROSPECT
(
   ID_PROSPECT          int not null,
   ID_SOCIETE           int not null,
   DATEPROSPECTION      date not null,
   INTERET              char(3) not null,

   constraint PK_PROSPECT primary key (ID_PROSPECT),

   constraint FK_PROSPECT_SOCIETE foreign key (ID_SOCIETE)
         references SOCIETE (ID_SOCIETE)
);

/*==============================================================*/
/* Table : CONTRAT_CLIENT                                       */
/*==============================================================*/
create table CONTRAT_CLIENT
(
   ID_CLIENT            int not null,
   ID_CONTRAT           bigint not null,

   constraint PK_CONTRAT_CLIENT primary key (ID_CLIENT, ID_CONTRAT),

   constraint FK_CONTRAT_CLIENT_CONTRAT foreign key (ID_CONTRAT)
         references CONTRAT (ID_CONTRAT),

   constraint FKCONTRAT_CLIENT_CLIENT foreign key (ID_CLIENT)
         references CLIENT (ID_CLIENT)

);

/*==============================================================*/
/* Table : SOCIETE_ADRESSE                                              */
/*==============================================================*/
create table SOCIETE_ADRESSE
(
   ID_SOCIETE           int not null,
   ID_ADRESSE           int not null,

   constraint PK_SOCIETE_ADRESSE primary key (ID_SOCIETE, ID_ADRESSE),

   constraint FK_SOCIETE_ADRESSE_ID_SOCIETE foreign key (ID_SOCIETE)
         references SOCIETE (ID_SOCIETE),
   
   constraint FK_SOCIETE_ADRESSE_ID_ADRESSE foreign key (ID_ADRESSE)
         references ADRESSE (ID_ADRESSE)


);