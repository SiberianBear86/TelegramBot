import org.junit.Test;

import static org.junit.Assert.*;

public class HintTest {

    @org.junit.Test
    public void getHintAtFirstRightAnswer() {
        Question quest =  new Hint().getHint(new Question("Какое имя у Демидовича? :3.Борис; 4.Иван", "3"));
        assertEquals("Какое имя у Демидовича? 3.Борис 4.Иван", quest.text + quest.answers[0]+ " " + quest.answers[1]);
    }

    @org.junit.Test
    public void getHintAtSecondRightAnswer() {
        Question quest = new Hint().getHint(new Question("На каком языке написана эта великолепная программа? :" +
                "1.C#; 2.java", "2"));
        assertEquals("На каком языке написана эта великолепная программа? 1.C# 2.java", quest.text +
                quest.answers[1]+ " " + quest.answers[0]);
    }
}