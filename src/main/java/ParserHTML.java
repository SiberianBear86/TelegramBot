
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserHTML {
    private LineNumberReader reader;

    ParserHTML() throws IOException {
        URL url = new URL("https://nekdo.ru/internet/");
        reader = new LineNumberReader(new InputStreamReader(url.openStream()));
    }

    ParserHTML(String text) throws FileNotFoundException {
        File filename = new File(text);
        reader = new LineNumberReader(new FileReader(filename));
    }

    List <String> parsePage() throws IOException {
        List <String> lines1 = new ArrayList <>();
        String line = reader.readLine();
        while (line != null) {
            if (line.contains("<div class=\"text\""))
                lines1.add(line);
            line = reader.readLine();
        }
        reader.close();
        return lines1;
    }

    List <String> getText(List <String> line) {
        String pattern = "	<div class=\"text\" id=\"\\d+\">|</div>";
        Pattern pat = Pattern.compile(pattern);
        List <String> lines = new ArrayList <>();
        for (String i : line) {
            Matcher matcher = pat.matcher(i);
            while (matcher.find())
                lines.add(matcher.replaceAll(""));
        }
        return lines;
    }

    List <String> removeSpaces(List <String> line) {
        String pattern = "<br>";
        Pattern pat = Pattern.compile(pattern);
        List <String> lines = new ArrayList <>();
        for (String i : line) {
            Matcher matcher = pat.matcher(i);
            if (matcher.find())
                lines.add(matcher.replaceAll("\n"));
            else
                lines.add(i);
        }
        return lines;
    }
}