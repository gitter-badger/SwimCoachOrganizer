package ch.tiim.sco.update;

public class NewVersionEvent {

    private Version currentVersion;
    private Version newVersion;

    public NewVersionEvent(Version currentVersion, Version newVersion) {
        this.currentVersion = currentVersion;
        this.newVersion = newVersion;
    }


    public Version getCurrentVersion() {
        return currentVersion;
    }

    public Version getNewVersion() {
        return newVersion;
    }
}
