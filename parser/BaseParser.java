package parser;

public class BaseParser {
    public static final char END = '\0';
    protected final CharSource source;
    protected char ch = 0xffff;
    protected char[] buffer;
    protected int position;

    protected BaseParser(final CharSource source) {
        this.source = source;
    }

    protected void nextChar() {
        if (buffer != null) {
            ch = buffer[position++];
            if (position == buffer.length) {
                buffer = null;
            }
            return;
        }
        ch = source.hasNext() ? source.next() : END;
    }

    protected boolean test(char expected) {
        return ch == expected;
    }

    protected boolean testAndSkip(char expected) {
        if (test(expected)) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        if (expected.isEmpty()) {
            throw new IllegalArgumentException("Cannot test to empty string");
        }

        if (buffer != null && buffer.length - position + 1 >= expected.length()) {
            return expected.equals(String.valueOf(buffer, position - 1, expected.length()));
        }

        char[] buffer = new char[expected.length()];
        for (int i = 0; i < buffer.length; i++) {
            if (i != 0) {
                nextChar();
            }
            buffer[i] = ch;
        }

        this.buffer = buffer;
        position = 0;
        nextChar();
        return expected.equals(String.valueOf(buffer));
    }

    protected boolean testAndSkip(String expected) {
        if (test(expected)) {
            skip(expected.length());
            return true;
        }
        return false;
    }

    protected void skip(int n) {
        for (int i = 0; i < n; i++) {
            nextChar();
        }
    }

    protected void expect(final char c) throws ParseException {
        if (ch != c) {
            throw new ParseException(
                    String.format("Expected '%c' at position %d, found '%c' - %d", c, source.getPosition(), ch, (int) ch)
            );
        }
        nextChar();
    }

    protected boolean eof() {
        return testAndSkip(END);
    }
}
