package markup;

import java.util.Map;

public abstract class AbstractMarkup implements Markup {
    // Markdown
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(getMarkdownTag());
        contentToMarkdown(stringBuilder);
        stringBuilder.append(getMarkdownTag());
    }

    protected String getMarkdownTag() {
        return "";
    }

    protected abstract void contentToMarkdown(StringBuilder stringBuilder);

    // BBCode
    @Override
    public void toBBCode(StringBuilder stringBuilder) {
        String startTag = getBBCodeStartTag();
        if (!startTag.isEmpty()) {
            stringBuilder.append("[").append(startTag).append("]");
        }

        contentToBBCode(stringBuilder);

        String endTag = getBBCodeEndTag();
        if (!endTag.isEmpty()) {
            stringBuilder.append("[/").append(endTag).append("]");
        }
    }

    protected String getBBCodeTag() {
        return "";
    }

    protected String getBBCodeStartTag() {
        return getBBCodeTag();
    }

    protected String getBBCodeEndTag() {
        return getBBCodeTag();
    }

    protected abstract void contentToBBCode(StringBuilder stringBuilder);

    // HTML
    @Override
    public void toHtml(StringBuilder stringBuilder) {
        String startTag = getHtmlStartTag();
        if (!startTag.isEmpty()) {
            stringBuilder.append("<").append(startTag);
            for (Map.Entry<String, String> arg : getHtmlArguments().entrySet()) {
                stringBuilder.append(" ").append(arg.getKey()).append("='").append(arg.getValue()).append("'");
            }
            stringBuilder.append(">");
        }

        contentToHtml(stringBuilder);

        String endTag = getHtmlTag();
        if (!endTag.isEmpty()) {
            stringBuilder.append("</").append(endTag).append(">");
            if (this instanceof BlockMarkup) {
                stringBuilder.append("\n");
            }
        }
    }

    protected String getHtmlTag() {
        return "";
    }

    protected String getHtmlStartTag() {
        return getHtmlTag();
    }

    protected String getHtmlEndTag() {
        return getHtmlTag();
    }

    protected Map<String, String> getHtmlArguments() {
        return Map.of();
    }

    protected abstract void contentToHtml(StringBuilder stringBuilder);
}
