package markup;

import java.util.List;

public class Strong extends CollectionInlineMarkup implements InlineMarkup {
    public Strong(List<InlineMarkup> elements) {
        super(elements);
    }

    @Override
    protected String getMarkdownTag() {
        return "__";
    }

    @Override
    protected String getBBCodeTag() {
        return "b";
    }

    @Override
    protected String getHtmlTag() {
        return "strong";
    }
}
