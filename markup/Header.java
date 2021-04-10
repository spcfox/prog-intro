package markup;

import java.util.List;

public class Header extends CollectionInlineMarkup implements BlockMarkup {
    private final int level;

    public Header(List<InlineMarkup> elements, int level) {
        super(elements);
        this.level = level;
    }

    @Override
    protected String getMarkdownTag() {
        return "#".repeat(level);
    }

    @Override
    protected String getBBCodeStartTag() {
        return "headline=" + level;
    }

    @Override
    protected String getBBCodeEndTag() {
        return "headline";
    }

    @Override
    protected String getHtmlTag() {
        return "h" + level;
    }
}
