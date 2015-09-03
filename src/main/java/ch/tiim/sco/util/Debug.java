package ch.tiim.sco.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Debug {

    public static String rs(ResultSet rs) {
        ResultSetMetaData rsmd = null;
        StringBuilder b = new StringBuilder().append("Result set").append(rs).append("\n");
        try {
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            b.append(" | ");
            for (int i = 1; i <= columnsNumber; i++) {
                b.append(rsmd.getColumnName(i)).append(" | ");
            }
            b.append("\n");
            while (rs.next()) {
                b.append(" | ");
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    b.append(columnValue).append(" | ");
                }
                b.append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b.toString();
    }

}
