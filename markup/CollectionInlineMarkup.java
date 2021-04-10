package markup;

import java.util.Collections;
import java.util.List;

public abstract class CollectionInlineMarkup extends CollectionMarkup {
    protected CollectionInlineMarkup(List<InlineMarkup> elements) {
        super(Collections.unmodifiableList(elements));
    }
}
