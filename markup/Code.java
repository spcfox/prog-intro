package markup;

import java.util.List;

public class Code extends CollectionInlineMarkup implements InlineMarkup {
    public Code(List<InlineMarkup> elements) {
        super(elements);
    }

    @Override
    protected String getMarkdownTag() {
        return "`";
    }

    @Override
    protected String getBBCodeTag() {
        return "code";
    }

    @Override
    protected String getHtmlTag() {
        return "code";
    }
}
