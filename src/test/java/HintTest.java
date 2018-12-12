import org.junit.Test;

import static org.junit.Assert.*;

public class HintTest {

    @org.junit.Test
    public void getHintAtFirstRightAnswer() {
        Question quest =  new Hint(new Question("Какое имя у Демидовича? :1.Игорь; 2.Вячеслав; 3.Борис; 4.Иван", "3"), 2).getHint();
        assertEquals("Какое имя у Демидовича? 3.Борис 4.Иван", quest.text + quest.answers[0]+ " " + quest.answers[1]);
    }

    @org.junit.Test
    public void getHintAtSecondRightAnswer() {
        Question quest = new Hint(new Question("На каком языке написана эта великолепная программа? :" +
                "1.C#; 2.java; 3.эльфийский; 4.Python", "2"), 0).getHint();
        assertEquals("На каком языке написана эта великолепная программа? 1.C# 2.java", quest.text +
                quest.answers[1]+ " " + quest.answers[0]);
    }
}