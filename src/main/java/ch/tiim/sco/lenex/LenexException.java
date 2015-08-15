package ch.tiim.sco.lenex;

public class LenexException extends Exception {

    public LenexException() {
        super();
    }

    public LenexException(String message) {
        super(message);
    }

    public LenexException(String message, Throwable cause) {
        super(message, cause);
    }

    public LenexException(Throwable cause) {
        super(cause);
    }
}
