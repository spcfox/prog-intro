package md2html;

import markup.Markup;
import md2html.parser.MarkdownParser;
import parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Converter should be running with 2 arguments: input and output filenames");
            return;
        }

        Markup markup;
        try {
            String input = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);
            MarkdownParser parser = new MarkdownParser(input);
            markup = parser.parse();
        } catch (FileNotFoundException e) {
            System.out.println("File '" + args[0] + "' not found");
            return;
        } catch (IOException e) {
            System.out.println("Input exception: " + e.getMessage());
            return;
        } catch (ParseException e) {
            System.out.println("Parse exception: " + e.getMessage());
            return;
        }

        try (Writer writer = new FileWriter(args[1], StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            markup.toHtml(sb);
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File '" + args[0] + "' not found");
        } catch (IOException e) {
            System.out.println("Output exception: " + e.getMessage());
        }
    }
}
