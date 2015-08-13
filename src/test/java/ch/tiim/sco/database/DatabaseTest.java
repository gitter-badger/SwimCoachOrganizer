package ch.tiim.sco.database;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseTest {

    @Before
    public void setup() throws IOException {
        Files.deleteIfExists(Paths.get("temp.db"));
    }

    @Test
    public void testInMemory() throws SQLException, IOException {
        DatabaseController db = new DatabaseController(":memory:");
        db.close();
    }

    @Test
    public void testFile() throws SQLException, IOException {
        Path p = Paths.get("temp.db");
        DatabaseController db = new DatabaseController(p.toString());
        db.close();
        Files.deleteIfExists(p);
    }
}
