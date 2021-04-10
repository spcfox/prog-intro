package markup;

import java.util.List;

public class OrderedList extends ListMarkup {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    protected String getBBCodeStartTag() {
        return "list=1";
    }

    @Override
    protected String getHtmlTag() {
        return "ol";
    }
}
