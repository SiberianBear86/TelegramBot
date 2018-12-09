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
                point-=300;
            }
        }
        else if (number.equals("joke")){
            setJoke(jokes.get(new Random().nextInt(amountJoke)));
            Collections.swap(jokes, jokes.indexOf(joke), amountJoke - 1);
            amountJoke--;
            setAmountJoke(amountJoke);
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
        setPoint(point);
        setAmountQuest(amountQuest);
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