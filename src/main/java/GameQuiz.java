import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class GameQuiz {
    String theme;
    String lastAns = "1";
    Question lastQuest = null;
    int amountJoke;
    int amountQuest;
    private String botAnswer;
    private String joke;
    Question question;
    List<Question> questList;
    private List<String> goodAnswer;
    private List<String> badAnswer;
    int point = 0;
    Boolean canGetHint;
    List<String> jokes;

    GameQuiz(String typeQuestion) throws IOException {
        ReadFile reader = new ReadFile();
        theme = typeQuestion;
        questList = reader.getList(theme);
        goodAnswer = reader.getAnswer("GoodAnswer.txt");
        badAnswer = reader.getAnswer("BadAnswer.txt");
        amountQuest = questList.size();
        ParserHTML parserHTML = new ParserHTML();
        jokes = parserHTML.removeSpaces(parserHTML.getText(parserHTML.parsePage()));
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
            Collections.swap(questList, questList.indexOf(question), amountQuest - 1);
            amountQuest--;
        }
        else if (number.equals("hint")) {
            if (point >= 300){
                canGetHint = true;
                point-=300;
            }
            else
                canGetHint = false;
        }
        else if (number.equals("joke")){
            setJoke(jokes.get(new Random().nextInt(amountJoke)));
            Collections.swap(jokes, jokes.indexOf(joke), amountJoke - 1);
            amountJoke--;
            setAmountJoke(amountJoke);
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
        setAmountQuest(amountQuest);
        setPoint(point);
    }

    private void setBotAnswer(String answer) { botAnswer = answer; }
    String getBotAnswer() { return botAnswer; }

    private void setJoke(String jok) { joke = jok; }
    String getJoke() { return joke; }

    private void setAmountJoke(int amJoke) { amountJoke = amJoke; }
    int getAmountJoke() { return amountJoke; }

    private void setAmountQuest(int amQuest) { amountQuest = amQuest; }
    int getAmountQuest() { return amountQuest; }

    private void setPoint(int points) { point = points; }
    int getPoint() { return point; }
}