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
        List <String> lines1 = new ArrayList <>();
        List <String> lines2 = new ArrayList <>();
        try {
            URL url = new URL("https://nekdo.ru/internet/");
            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String s = reader.readLine();
                while (s != null) {
                    if (s.contains("<div class=\"text\""))
                        lines.add(s);
                    s = reader.readLine();
                }
                reader.close();
                String pattern = "	<div class=\"text\" id=\"\\d+\">|</div>";
                Pattern p = Pattern.compile(pattern);
                for (String i : lines) {
                    Matcher m = p.matcher(i);
                    while (m.find())
                        lines1.add(m.replaceAll(""));
                }
                String pat = "<br>";
                Pattern p1 = Pattern.compile(pat);
                for (String i : lines1) {
                    Matcher m = p1.matcher(i);
                    if (m.find())
                        lines2.add(m.replaceAll("\n"));
                    else
                        lines2.add(i);
                }
                return lines2;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        return lines2;
    }
}
