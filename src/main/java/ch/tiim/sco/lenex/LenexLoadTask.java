package ch.tiim.sco.lenex;

import ch.tiim.sco.lenex.model.Lenex;
import ch.tiim.sco.util.ByteCountingInputStream;
import javafx.concurrent.Task;

import java.nio.file.Path;

public class LenexLoadTask extends Task<Lenex> {

    private final Path path;

    public LenexLoadTask(Path path) {
        this.path = path;
    }

    @Override
    protected Lenex call() throws Exception {
        LenexParser parser = new LenexParser();
        updateMessage("Parsing LENEX");
        Lenex lenex = parser.read(path, new Updater());
        updateMessage("Done");
        return lenex;
    }

    private class Updater implements ByteCountingInputStream.Updater {
        private long max = 1;

        @Override
        public void update(long i) {
            updateProgress(i, max);
        }

        @Override
        public void setMax(long i) {
            max = i;
        }
    }
}
