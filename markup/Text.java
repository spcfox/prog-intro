package markup;

import java.util.Map;

public class Text extends AbstractMarkup implements InlineMarkup {
    protected String content;
    protected Map<Character, String> HTML_ENTITIES = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public Text(String content) {
        this.content = content;
    }

    @Override
    protected void contentToMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(content);
    }

    @Override
    protected void contentToBBCode(StringBuilder stringBuilder) {
        stringBuilder.append(content);
    }

    @Override
    protected void contentToHtml(StringBuilder stringBuilder) {
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            stringBuilder.append(HTML_ENTITIES.getOrDefault(ch, Character.toString(ch)));
        }
    }
}
