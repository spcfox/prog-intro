package markup;

import java.util.List;

public class Strikeout extends CollectionInlineMarkup implements InlineMarkup {
    public Strikeout(List<InlineMarkup> elements) {
        super(elements);
    }

    @Override
    protected String getMarkdownTag() {
        return "~";
    }

    @Override
    protected String getBBCodeTag() {
        return "s";
    }

    @Override
    protected String getHtmlTag() {
        return "s";
    }
}
