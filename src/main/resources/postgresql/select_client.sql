select s.societe_id,
       raison_sociale,
       domainst,
       telephone,
       email,
       numero,
       rue,
       cd_postale,
       ville,
       chiffre_affaire,
       employer_nb,
       commentaire
  from societe s
         inner join adresse a on s.societe_id = a.societe_id
         inner join client c on c.societe_id = s.societe_id;