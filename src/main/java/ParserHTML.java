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
    private List <String> lines = new ArrayList <>();
    private List <String> lines1 = new ArrayList <>();
    private List <String> lines2 = new ArrayList <>();

    List <String> parsePage() {
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
                getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        return removeSpaces();
    }

    private void getText(){
        String pattern = "	<div class=\"text\" id=\"\\d+\">|</div>";
        Pattern pat = Pattern.compile(pattern);
        for (String i : lines) {
            Matcher matcher = pat.matcher(i);
            while (matcher.find())
                lines1.add(matcher.replaceAll(""));
        }
    }

    private List <String> removeSpaces(){
        String pattern = "<br>";
        Pattern pat = Pattern.compile(pattern);
        for (String i : lines1) {
            Matcher matcher = pat.matcher(i);
            if (matcher.find())
                lines2.add(matcher.replaceAll("\n"));
            else
                lines2.add(i);
        }
        return lines2;
    }

}
