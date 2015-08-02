package ch.tiim.log;

public class ConsoleAppender implements Appender {
    @Override
    public void appendString(String str) {
        System.out.println(str);
    }
}
