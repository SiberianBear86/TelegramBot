import java.io.*;
import java.util.*;

class ReadFile {
    private static Map <String, String> dictTheme = new HashMap <>();
    private List <Question> quest = new ArrayList <>();

    static void createTheme() throws IOException {
        BufferedReader reader = readFile("Theme.txt");
        String line;
        while(true){
            line = reader.readLine();
            if (line == null)
                break;
            String[] themes = line.split(":");
            dictTheme.put(themes[0], themes[1]);
        }
    }

    private static BufferedReader readFile(String file) throws FileNotFoundException {
        File filename = new File(file);
        FileReader fr = new FileReader(filename);
        return new BufferedReader(fr);
    }

    List <Question> getList(String theme) throws IOException {
        BufferedReader reader = readFile(dictTheme.get(theme));
        while (true) {
            String line;
            line = reader.readLine();
            if (line == null)
                break;
            String[] q = line.split("=");
            quest.add(new Question(q[0], q[1], theme));
        }
        return quest;
    }

    List <String> getAnswer(String file) throws IOException {
        List <String> answer = new ArrayList <>();
        BufferedReader reader = readFile(file);
        while (true) {
            String line;
            line = reader.readLine();
            if (line == null)
                break;
            answer.add(line);
        }
        return answer;
    }
}