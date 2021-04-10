package markup;

import java.util.Collections;
import java.util.List;

public abstract class CollectionBlockMarkup extends CollectionMarkup {
    protected CollectionBlockMarkup(List<BlockMarkup> elements) {
        super(Collections.unmodifiableList(elements));
    }
}
