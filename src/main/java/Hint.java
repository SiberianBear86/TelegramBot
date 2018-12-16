import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Hint {
    private int answerNumber;
    private Question quest;
    Hint(Question question){
        quest = question;
        answerNumber = new Random().nextInt(quest.answers.length - 1);

    }
    Hint(Question question, int number){
        quest = question;
        answerNumber = number;
    }
    Question getHint() {
        List<String> ans = new ArrayList<>();
        List<String> notAns = new ArrayList<>();
        for (String i : quest.answers) {
            if (i.contains(quest.rightAnswer))
                ans.add(i);
            else
                notAns.add(i);
        }
        ans.add(notAns.get(answerNumber));
        quest.answers = ans.toArray(new String[0]);
        return quest;
    }
}
