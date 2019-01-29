import java.io.*;
import java.util.*;

class ReadFile {
    private BufferedReader reader;
    private Map <String, String> dictTheme = new HashMap <>();
    private List <Question> quest = new ArrayList <>();

    void createTheme(String selectTheme) throws IOException {
        readFile("Theme.txt");
        String line;
        while(true){
            line = this.reader.readLine();
            if (line == null)
                break;
            String[] themes = line.split(":");
            dictTheme.put(themes[0], themes[1]);
        }
        readFile(dictTheme.get(selectTheme));
    }

    void readFile(String file) throws FileNotFoundException {
        File filename = new File(file);
        FileReader fr = new FileReader(filename);
        this.reader = new BufferedReader(fr);
    }

    List <Question> getList() throws IOException {
        while (true) {
            String line;
            line = this.reader.readLine();
            if (line == null)
                break;
            String[] q = line.split("=");
            quest.add(new Question(q[0], q[1]));
        }
        return quest;
    }

    List <String> getAnswer(String file) throws IOException {
        List <String> answer = new ArrayList <>();
        readFile(file);
        while (true) {
            String line;
            line = this.reader.readLine();
            if (line == null)
                break;
            answer.add(line);
        }
        return answer;
    }
}