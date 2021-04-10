package markup;

import java.util.Collections;
import java.util.List;

public class FullMarkup extends CollectionBlockMarkup {
    public FullMarkup(List<BlockMarkup> elements) {
        super(Collections.unmodifiableList(elements));
    }
}
