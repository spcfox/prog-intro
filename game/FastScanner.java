package game;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastScanner {
	private Reader reader;
	private DelimiterChecker delimiter;
	private StringBuilder word;
	private char[] buffer;
	private int bufferPointer;
	private int readedChars;
	private int newLinesBeforeNext;
	private char previousChar;
	private boolean eof;

	private FastScanner() {
		word = new StringBuilder();
		delimiter = Character::isWhitespace;
		buffer = new char[1024];
	}

	public FastScanner(Reader reader) {
		this();
		this.reader = reader;
	}

	public FastScanner(InputStream stream, Charset charset) {
		this(new InputStreamReader(stream, charset));
	}

	public FastScanner(InputStream stream) {
		this(stream, StandardCharsets.UTF_8);
	}

	public FastScanner(String fileName, Charset charset) throws IOException {
		this(new FileReader(fileName, charset));
	}

	public FastScanner(String fileName) throws IOException {
		this(new FileReader(fileName, StandardCharsets.UTF_8));
	}

	public String next() throws IOException {
		if (!hasNext()) {
			if (eof) {
				throw new EOFException();
			}
			return null;
		}

		String result = word.toString();
		word.setLength(0);
		newLinesBeforeNext = 0;
		
		return result;
	}

	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	public boolean hasNext() throws IOException {
		readIfWordEmpty();
		return word.length() > 0; 
	}

	public boolean hasNextInt() throws IOException {
		if (!hasNext()) {
			return false;
		}

		try {
			Integer.parseInt(word.toString());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int getNewLines() throws IOException {
		readIfWordEmpty();
		return newLinesBeforeNext;
	}

	public void useDelimiter(DelimiterChecker delimiter) {
		this.delimiter = delimiter;
	}

	public void close() throws IOException {
		reader.close();
	}

	private void readIfWordEmpty() throws IOException {
		if (word.length() == 0) {
			readWord();
		}
	}

	private void readWord() throws IOException {
		do {
			while (bufferPointer < readedChars) {
				char ch = buffer[bufferPointer++];

				if (previousChar == '\n' || previousChar == '\r' && ch != '\n') {
					newLinesBeforeNext++;
				} 
				previousChar = ch;

				if (!delimiter.isDelimiter(ch)) {
					word.append(ch);
				} else if (word.length() > 0) {
					return;
				}
			}
			bufferPointer = 0;
			readedChars = reader.read(buffer);
		} while(readedChars != -1);
		eof = true;
	}

	interface DelimiterChecker {
		boolean isDelimiter(char ch);
	}
} 
