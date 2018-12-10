import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserHTML {
    List <String> parsePage() {
        List <String> lines = new ArrayList <>();
        try {
            URL url = new URL("https://nekdo.ru/internet/");
            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String line = reader.readLine();

                while (line != null) {
                    if (line.contains("<div class=\"text\""))
                        lines.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        return removeSpaces(getText(lines));
    }

    private List<String> getText(List<String> line){
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

    private List <String> removeSpaces(List<String> line){
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