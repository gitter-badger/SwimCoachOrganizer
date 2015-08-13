package ch.tiim.sco.update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Version implements Comparable {

    private static final Pattern VERSION_PATTERN = Pattern.compile("\\s*v(\\d+)\\.(\\d+)\\.(\\d+)(?:-(.*):(.*))?\\s*");

    private final int major;
    private final int minor;
    private final int patch;

    private String branch;
    private String gitHash;

    public Version() {
        this(0, 0, 0);
        System.out.println("DevBuild --> v0.0.0");
    }

    public Version(final int major, final int minor, final int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version(final String v) {
        if (v.equalsIgnoreCase("DevBuild")) {
            this.major = 0;
            this.minor = 0;
            this.patch = 0;
        } else {
            final Matcher m = VERSION_PATTERN.matcher(v);
            if (!m.matches()) {
                throw new IllegalArgumentException("Version '" + v + " must match the version regexp");
            }
            this.major = Integer.parseInt(m.group(1));
            this.minor = Integer.parseInt(m.group(2));
            this.patch = Integer.parseInt(m.group(3));
            this.branch = m.group(4);
            this.gitHash = m.group(5);
        }
    }

    @Override
    public String toString() {
        if (isDeployed()) {
            String s = String.format("v%d.%d.%d", major, minor, patch);
            if (branch != null) {
                s += "-" + branch + ":";
                if (gitHash != null) {
                    s += gitHash;
                }
            }
            return s;
        } else {
            return "DevBuild";
        }
    }

    @Override
    public int compareTo(final Object o) {
        if (!(o instanceof Version)) {
            return 0;
        }
        final Version v = (Version) o;
        //Just in case
        if (v.major != major) {
            return (major - v.major) * 100000;
        }
        if (v.minor != minor) {
            return (minor - v.minor) * 1000;
        }
        if (v.patch != patch) {
            return patch - v.patch;
        }
        return 0;
    }

    public boolean isDeployed() {
        return patch != 0 || minor != 0 || major != 0;
    }

    public boolean newerThan(Version other) {
        return compareTo(other) > 0;
    }
}