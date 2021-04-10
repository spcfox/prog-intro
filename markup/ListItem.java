package markup;

import java.util.List;

public class ListItem extends CollectionBlockMarkup {
    public ListItem(List<BlockMarkup> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getMarkdownTag() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getBBCodeStartTag() {
        return "*";
    }

    @Override
    protected String getBBCodeEndTag() {
        return "";
    }

    @Override
    protected String getHtmlTag() {
        return "li";
    }
}

