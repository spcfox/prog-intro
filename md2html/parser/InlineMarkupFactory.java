package md2html.parser;

import markup.InlineMarkup;

import java.util.List;

public interface InlineMarkupFactory {
    InlineMarkup create(List<InlineMarkup> elements);
}
