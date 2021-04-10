package md2html.parser;

import markup.*;

public enum MarkdownTag {
    STRONG1("**", "**", Strong::new),
    STRONG2("__", "__", Strong::new),
    STRIKEOUT("--", "--", Strikeout::new),
    EMPHASIS1("*", "*", Emphasis::new),
    EMPHASIS2("_", "_", Emphasis::new),
    CODE("`", "`", Code::new),
    LINK("[", "]", Link::new);

    public final String startTag;
    public final String endTag;
    public final InlineMarkupFactory factory;

    MarkdownTag(String startTag, String endTag, InlineMarkupFactory factory) {
        this.startTag = startTag;
        this.endTag = endTag;
        this.factory = factory;
    }
}
