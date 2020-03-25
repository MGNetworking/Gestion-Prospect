
insert into gestion.societe ( raison_sociale ,domainSt , telephone , email , commentaire) values
('AFPA','PUBLIC','06 07 98 30 27','afpa@gmail.com', 'commentaire Afpa'),
('CLEMESSY','PRIVE','03 22 98 75 12','clemessy@gmail.com', 'commentaire CLEMESSY'),
('DARTY','PRIVE','06 27 54 12 79','darty@gmail.com', 'commentaire DARTY'),
('CAREFOUR','PUBLIC','06 50 89 72 19','carefour@gmail.com', 'commentaire CAREFOUR');

INSERT into gestion.adresse (societe_id,  numero, rue , cd_postale , ville) values
(1, 50 ,'rue des boulangers','68000','Mulhouse'),
(2, 27 ,'rue d en haut', '80540', 'amiens'),
(3, 108,'rue le coq', '80080', 'amiens'),
(4, 6  ,'rue stendhal','75000', 'paris');

insert into gestion.prospect (societe_id , date_prospection , interet ) values
(1,to_date('05/10/2019', 'DD/MM/YYYY'),'OUI'),
(2,to_date('26/02/2020', 'DD/MM/YYYY'),'NON');

INSERT INTO gestion.client ( societe_id , chiffre_affaire , employer_nb ) values 
(3,100000,10),
(4,500000,100);


