package markup;

import java.util.Collections;
import java.util.List;

public abstract class ListMarkup extends CollectionMarkup implements BlockMarkup {
    protected ListMarkup(List<ListItem> elements) {
        super(Collections.unmodifiableList(elements));
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getBBCodeTag() {
        return "list";
    }
}
