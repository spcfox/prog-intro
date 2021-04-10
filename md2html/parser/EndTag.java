package md2html.parser;

import markup.InlineMarkup;

public class EndTag implements InlineMarkup {
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        throw new UnsupportedOperationException();
    }
}
