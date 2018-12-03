import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class GameQuiz {
    String lastAns = "1";
    Question lastQuest = null;
    int amountJoke;
    int amountQuest;
    private String botAnswer;
    private String joke;
    private Question hint;
    Question question;
    List<Question> questList;
    List<String> goodAnswer;
    List<String> badAnswer;
    int point;
    private List<String> jokes;

    GameQuiz(String number) throws FileException {
        ReadFile reader = new ReadFile();
        reader.readFile(number);
        questList = reader.getList();
        goodAnswer = reader.getAnswer("GoodAnswer.txt");
        badAnswer = reader.getAnswer("BadAnswer.txt");
        point = 0;
        amountQuest = questList.size();
        jokes = new ParserHTML().parsePage();
        amountJoke = jokes.size();
    }

    Question getQuest(){
        if (amountQuest == 0)
            return null;
        if (lastAns.equals("hint") || lastAns.equals("joke"))
            return lastQuest;
        else{
            question = questList.get(new Random().nextInt(amountQuest));
            return question;
        }
    }

    void correctAnswer(String number) {
        if (!number.contains(question.rightAnswer) && !number.equals("hint") && !number.equals("joke")) {
            point -= 300;
            setBotAnswer(badAnswer.get(new Random().nextInt(badAnswer.size()))
                    + "\nУ тебя: " + point + " очков");
            Collections.swap(questList, questList.indexOf(question), amountQuest -1);
            amountQuest--;
        }
        else if (number.equals("hint")) {
            if (point >= 300){
                setHint(getHint(question));
                point-=300;
            }
        }
        else if (number.equals("joke")){
            setJoke(jokes.get(new Random().nextInt(amountJoke)));
            Collections.swap(jokes, jokes.indexOf(joke), amountJoke - 1);
            amountJoke--;
            System.out.println(amountJoke);
        }
        else {
            point+=100;
            setBotAnswer(goodAnswer.get(new Random().nextInt(goodAnswer.size()))
                    + "\nУ тебя: " + point + " очков");
            Collections.swap(questList, questList.indexOf(question), amountQuest -1);
            amountQuest--;
        }
        this.lastAns = number;
        this.lastQuest = question;
    }

    Question getHint(Question question) {
        List<String> ans = new ArrayList<>();
        List<String> notAns = new ArrayList<>();
        for (String i : question.answers) {
            if (i.contains(question.rightAnswer))
                ans.add(i);
            else
                notAns.add(i);
        }
        ans.add(notAns.get(new Random().nextInt(notAns.size())));
        question.answers = ans.toArray(new String[0]);
        return question;
    }

    private void setHint(Question h) { hint = h; }
    Question getHint() { return hint; }

    private void setBotAnswer(String a) { botAnswer = a; }
    String getBotAnswer() { return botAnswer; }

    private void setJoke(String j) { joke = j; }
    String getJoke() { return joke; }
}