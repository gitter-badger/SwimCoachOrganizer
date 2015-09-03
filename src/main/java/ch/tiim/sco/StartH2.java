package ch.tiim.sco;

import java.io.IOException;
import java.sql.SQLException;

public class StartH2 {
    public static void main(String[] args) throws SQLException, IOException {
        org.h2.tools.Console.main(args);
    }
}
