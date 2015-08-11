package ch.tiim.trainingmanager.lenex;

import ch.tiim.log.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class LenexParser {
    private static final Log LOGGER = new Log(LenexParser.class);
    private static final PathMatcher LENEX_XML = FileSystems.getDefault().getPathMatcher("glob:*.lef");
    private static final PathMatcher LENEX_ZIP = FileSystems.getDefault().getPathMatcher("glob:*.lxf");

    public Lenex read(Path p) throws IOException {
        InputStream is = null;
        try {
            if (LENEX_ZIP.matches(p)) {
                ZipFile zf = new ZipFile(p.toFile());
                ZipEntry e = zf.entries().nextElement();
                is = zf.getInputStream(e);
            } else {
                is = new FileInputStream(p.toFile());
            }
            JAXBContext context = JAXBContext.newInstance(Lenex.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Lenex lenex = (Lenex) unmarshaller.unmarshal(is);
            System.out.println(lenex);
            return lenex;
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //noinspection EmptyTryBlock
            try (InputStream iss = is) {
                // input stream gets closed.
            }
        }
        return null;
    }
}
