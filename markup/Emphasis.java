package markup;

import java.util.List;

public class Emphasis extends CollectionInlineMarkup implements InlineMarkup {
    public Emphasis(List<InlineMarkup> elements) {
        super(elements);
    }

    @Override
    protected String getMarkdownTag() {
        return "*";
    }

    @Override
    protected String getBBCodeTag() {
        return "i";
    }

    @Override
    protected String getHtmlTag() {
        return "em";
    }
}
