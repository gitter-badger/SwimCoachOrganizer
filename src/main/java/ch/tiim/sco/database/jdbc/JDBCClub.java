package ch.tiim.sco.database.jdbc;

import ch.tiim.sco.database.DatabaseController;
import ch.tiim.sco.database.model.Club;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class JDBCClub extends Table implements ch.tiim.sco.database.TableClub {


    private String addStmt;
    private String deleteStmt;
    private String updateStmt;
    private String getAllStmt;


    public JDBCClub(DatabaseController db) {
        super(db);
    }

    @Override
    protected void loadStatements() {
        addStmt = db.getSqlLoader().getValue("Club", "add");
        deleteStmt = db.getSqlLoader().getValue("Club", "delete");
        updateStmt = db.getSqlLoader().getValue("Club", "update");
        getAllStmt = db.getSqlLoader().getValue("Club", "get_all");
    }

    @Override
    public void addClub(Club c) {
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new BeanPropertySqlParameterSource(c);
        db.getJdbc().update(addStmt, ps, kh);
        c.setId((Integer) kh.getKey());
    }

    @Override
    public void deleteClub(Club c) {
        BeanPropertySqlParameterSource bs = new BeanPropertySqlParameterSource(c);
        db.getJdbc().update(deleteStmt, bs);
    }

    @Override
    public void updateClub(Club c) {
        BeanPropertySqlParameterSource bs = new BeanPropertySqlParameterSource(c);
        db.getJdbc().update(updateStmt, bs);
    }

    @Override
    public List<Club> getAll() {
        return db.getJdbc().query(getAllStmt, new BeanPropertyRowMapper<>(Club.class));
    }
}
