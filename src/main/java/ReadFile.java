import java.io.*;
import java.util.*;

class ReadFile {
    String theme1;
    private BufferedReader reader;
    private List <String> theme = new ArrayList <>();
    private Map <String, String> dictTheme = new HashMap <>();
    private String excep;
    private List <Question> quest = new ArrayList <>();

    ReadFile() {
        this.theme.add("1.матмех");
        this.theme.add("2.география");
        this.dictTheme.put(this.theme.get(0), "1.txt");
        this.dictTheme.put(this.theme.get(1), "2.txt");
    }

    void readFile(String selectTheme) throws FileException {
        if (this.theme.get(0).contains(selectTheme))
            theme1 = this.dictTheme.get(this.theme.get(0));
        else if (this.theme.get(1).contains(selectTheme))
            theme1 = this.dictTheme.get(this.theme.get(1));
        else {
            excep = "Данная тематика не найдена";
            throw new FileException();
        }
        File filename = new File(theme1);
        try {
            FileReader fr = new FileReader(filename);
            this.reader = new BufferedReader(fr);
        } catch (IOException e) {
            excep = "Данный файл не найден";
            throw new FileException();
        }
    }

    List <String> getAnswer(String file) throws FileException {
        List <String> answer = new ArrayList <>();
        File filename = new File(file);
        BufferedReader buf;
        try {
            FileReader frr = new FileReader(filename);
            buf = new BufferedReader(frr);
        } catch (FileNotFoundException e) {
            excep = "Данный файл не найден";
            throw new FileException();
        }
        while (true) {
            String line;
            try {
                line = buf.readLine();
            } catch (IOException e) {
                excep = "Ошибка при чтении файла";
                throw new FileException();
            }
            if (line == null)
                break;
            answer.add(line);
        }
        return answer;
    }

    List <Question> getList() throws FileException {
        int count = 0;
        String lastQuest = null;
        String lastAnswer = null;
        while (true) {
            String line;
            try {
                line = this.reader.readLine();
            } catch (IOException e) {
                excep = "Невозможно прочитать строку";
                throw new FileException();
            }
            if (line == null)
                break;
            if (line.length() == 1)
                lastAnswer = line;
            else
                lastQuest = line;
            count++;
            if (count % 2 == 0)
                quest.add(new Question(lastQuest, lastAnswer));
        }
        return quest;
    }
}