package markup;

import java.util.List;

public class UnorderedList extends ListMarkup {
    public UnorderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    protected String getHtmlTag() {
        return "ul";
    }
}
