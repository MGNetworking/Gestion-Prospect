select s.societe_id,
       raison_sociale,
       domainst,
       telephone,
       email,
       numero,
       rue,
       cd_postale,
       ville,
       date_prospection,
       interet,
       "commentaire"
from societe s
         inner join adresse a on s.societe_id = a.societe_id
         inner join "prospect" p on p.societe_id = s.societe_id;