UPDATE training_content
SET indx = (
    SELECT SUM(indx)
    FROM training_content
    WHERE indx = ? OR indx = ?
    ) - indx

WHERE indx = ? OR indx = ?;