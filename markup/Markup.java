package markup;

public interface Markup {
    void toMarkdown(StringBuilder stringBuilder);

    void toBBCode(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);
}
