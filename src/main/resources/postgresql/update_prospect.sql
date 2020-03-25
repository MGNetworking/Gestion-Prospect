update prospect
  set date_prospection = to_date(?, 'DD/MM/YYYY'),
    interet          = ?
  where societe_id = ?;