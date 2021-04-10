package md2html;

import markup.Markup;
import md2html.parser.MarkdownParser;
import parser.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        String str = "[hello\r\n\r\nbro](123)";
        Markup markup = new MarkdownParser(str).parse();
        StringBuilder sb = new StringBuilder();
        markup.toHtml(sb);
        System.out.println(sb);
    }
}
