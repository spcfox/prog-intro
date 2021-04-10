package markup;

import java.util.List;

public class Paragraph extends CollectionInlineMarkup implements BlockMarkup {
    public Paragraph(List<InlineMarkup> elements) {
        super(elements);
    }

    @Override
    protected String getHtmlTag() {
        return "p";
    }
}
