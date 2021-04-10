package markup;

import java.util.List;

public interface InlineMarkupFactory {
    InlineMarkup create(List<InlineMarkup> elements);
}
