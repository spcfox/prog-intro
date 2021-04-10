package markup;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Link extends CollectionInlineMarkup implements InlineMarkup {
    protected String href;

    public Link(List<InlineMarkup> elements) {
        super(elements);
    }

    public Link(List<InlineMarkup> elements, String href) {
        super(elements);
        this.href = href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    protected void contentToMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("[");
        super.toMarkdown(stringBuilder);
        stringBuilder.append("](").append(href).append(")");
    }

    @Override
    protected String getHtmlTag() {
        return "a";
    }

    @Override
    protected Map<String, String> getHtmlArguments() {
        Map<String, String> args = new LinkedHashMap<>();
        args.put("href", href);
        return args;
    }
}
