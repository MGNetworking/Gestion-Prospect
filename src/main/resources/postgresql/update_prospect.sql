update prospect
set date_prospection = to_date(?, 'YYYY-MM-DD'),
    interet          = ?
where societe_id = ?;