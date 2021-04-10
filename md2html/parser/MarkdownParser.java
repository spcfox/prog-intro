package md2html.parser;

import markup.*;
import parser.*;

import java.util.*;

public class MarkdownParser extends BaseParser {
    private final Set<Character> ESCAPE_CHARACTERS = Set.of('*', '_');
    private final String lineSeparator;
    private final String blockEnd;
    private Set<MarkdownTag> openedTags = EnumSet.noneOf(MarkdownTag.class);

    public MarkdownParser(String source) {
        this(new StringSource(source));
        nextChar();
    }

    public MarkdownParser(CharSource source) {
        this(source, System.lineSeparator());
    }

    public MarkdownParser(CharSource source, String lineSeparator) {
        super(source);
        this.lineSeparator = lineSeparator;
        this.blockEnd = lineSeparator + lineSeparator;
    }

    public Markup parse() throws ParseException {
        Markup result = parseAll();
        if (eof()) {
            return result;
        }
        throw new UnexpectedEndException("End of file expected");
    }

    private Markup parseAll() throws ParseException {
        skipNewLines();
        List<BlockMarkup> paragraphs = new ArrayList<>();
        while (!eof()) {
            if (testAndSkip('#')) {
                paragraphs.add(parseHeader());
            } else {
                paragraphs.add(parseParagraph());
            }
            skipNewLines();
        }
        return new FullMarkup(paragraphs);
    }

    private BlockMarkup parseHeader() throws ParseException {
        int level = 1;
        while (testAndSkip('#')) {
            level++;
        }
        if (testAndSkip(' ')) {
            return new Header(parseInlineCollection(), level);
        } else {
            return parseParagraph("#".repeat(level));
        }
    }

    private Paragraph parseParagraph() throws ParseException {
        return parseParagraph("");
    }

    private Paragraph parseParagraph(String startText) throws ParseException {
        openedTags.clear();
        return new Paragraph(parseInlineCollection("", startText));
    }

    private List<InlineMarkup> parseInlineCollection() throws ParseException {
        return parseInlineCollection("");
    }

    private List<InlineMarkup> parseInlineCollection(String tag) throws ParseException {
        return parseInlineCollection(tag, "");
    }

    private List<InlineMarkup> parseInlineCollection(String tag, String startText) throws ParseException {
        List<InlineMarkup> elements = new ArrayList<>();
        StringBuilder text = new StringBuilder(startText);

        boolean newLine = testNewline();
        while (!eof() && !testBlockEnd()) {
            if (newLine) {
                text.append(lineSeparator);
                newLine = false;
            }

            if (testAndSkip('\\')) {
                if (ESCAPE_CHARACTERS.contains(ch)) {
                    text.append(ch);
                    nextChar();
                } else {
                    text.append('\\');
                }
            }

            out: {
                for (MarkdownTag mt : EnumSet.allOf(MarkdownTag.class)) {
                    if (!openedTags.contains(mt) && testAndSkip(mt.startTag)) {
                        addText(elements, text);
                        if (mt == MarkdownTag.LINK) {
                            elements.addAll(parseLink());
                        } else {
                            openedTags.add(mt);
                            elements.addAll(parseInlineCollection(mt.startTag));
                        }
                        break out;
                    } else if (openedTags.contains(mt) && testAndSkip(mt.endTag)) {
                        if (tag.equals(mt.endTag)) {
                            addText(elements, text);
                            openedTags.remove(mt);
                            return List.of(mt.factory.create(elements));
                        } else {
                            text.insert(0, tag);
                            addText(elements, text);
                            return elements;
                        }
                    }
                }

                if (testNewline()) {
                    newLine = true;
                } else {
                    text.append(ch);
                    nextChar();
                }
            }
        }

        addText(elements, text);
        if (!tag.isEmpty()) {
            elements.add(0, new Text(tag));
        }

        return elements;
    }

    private void addText(List<InlineMarkup> elements, StringBuilder text) {
        if (text.length() > 0) {
            elements.add(new Text(text.toString()));
            text.setLength(0);
        }
    }

    private List<InlineMarkup> parseLink() throws ParseException {
        Set<MarkdownTag> outerTags = openedTags;
        openedTags = EnumSet.of(MarkdownTag.LINK);
        List<InlineMarkup> elements = parseInlineCollection();
        openedTags = outerTags;
        if (test('(')) {
            Link link = new Link(elements);
            link.setHref(parseHref());
            return List.of(link);
        } else {
            elements.add(0, new Text(MarkdownTag.LINK.startTag));
        }
        return elements;
    }

    private String parseHref() throws ParseException {
        expect('(');
        StringBuilder stringBuilder = new StringBuilder();
        while (!testAndSkip(")")) {
            stringBuilder.append(ch);
            nextChar();
            if (eof() && testBlockEnd()) {
                throw new UnexpectedEndException("Unexpected end, expected end of link");
            }
        }
        return stringBuilder.toString();
    }

    private void skipNewLines() {
        while (testNewline()) {
        }
    }

    private boolean testNewline() {
        return testAndSkip(lineSeparator);
    }

    private boolean testBlockEnd() {
        return test(blockEnd);
    }
}
