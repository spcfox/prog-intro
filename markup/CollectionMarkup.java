package markup;

import java.util.List;

public abstract class CollectionMarkup extends AbstractMarkup {
    protected final List<Markup> elements;

    protected CollectionMarkup(List<Markup> elements) {
        this.elements = List.copyOf(elements);
    }

    @Override
    protected void contentToMarkdown(StringBuilder stringBuilder) {
        for (Markup element : elements) {
            element.toMarkdown(stringBuilder);
        }
    }

    @Override
    protected void contentToBBCode(StringBuilder stringBuilder) {
        for (Markup element : elements) {
            element.toBBCode(stringBuilder);
        }
    }

    @Override
    protected void contentToHtml(StringBuilder stringBuilder) {
        for (Markup element : elements) {
            element.toHtml(stringBuilder);
        }
    }
}
