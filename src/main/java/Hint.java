import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Hint {
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
}
