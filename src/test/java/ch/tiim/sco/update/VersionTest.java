package ch.tiim.sco.update;

import org.junit.Test;

import static org.junit.Assert.*;

public class VersionTest {

    @Test
    public void parseSimpleVersion() {
        Version v = new Version("v1.2.3");
        assertEquals(new Version(1, 2, 3), v);
    }

    @Test
    public void parseGitInformation() {
        Version v = new Version("v2.3.4-testbranch:123abc");
        assertEquals(new Version(2, 3, 4, "testbranch", "123abc"), v);
    }

    @Test
    public void parsePartialGitInfo() {
        Version v = new Version("v2.3.4-:234bc");
        assertEquals(new Version(2, 3, 4, null, "234bc"), v);
    }

    @Test
    public void parsePartialGitInfo2() {
        Version v = new Version("v2.3.4-branch:");
        assertEquals(new Version(2, 3, 4, "branch", null), v);
    }

    @Test
    public void compareDevVersion() {
        Version v = new Version(); //Dev Version
        assertFalse(v.newerThan(new Version(0, 1, 0)));
    }

    @Test
    public void compareNormal() {
        assertTrue("v1.2.3 is newer than v1.2.2",
                new Version(1, 2, 3).newerThan(new Version(1, 2, 2)));
    }

    @Test
    public void compareWithGitInfo() {
        assertTrue("v1.2.3-master:123 is neither newer nor older than v1.2.3-branch:234",
                new Version(1, 2, 3, "master", "123").compareTo(
                        new Version(1, 2, 3, "branch", "234")) == 0);
    }


    @Test
    public void deployedDevVersion() {
        assertFalse("Empty version indicates dev version",
                new Version().isDeployed());
    }

    @Test
    public void deployedDevVersionWithGitInfo() {
        assertFalse("Empty version indicates dev version",
                new Version(0, 0, 0, "master", "123ads").isDeployed());
    }

    @Test
    public void deployedNormalVersion() {
        assertTrue("Normal version is always deployed",
                new Version("v1.2.3").isDeployed());
    }
}
