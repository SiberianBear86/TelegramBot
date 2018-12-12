import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameQuizTest {

    @org.junit.Test
    public void getQuestAtZeroAmount() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList <>();
        gameQuiz.questList.add(new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3"));
        gameQuiz.amountQuest = 0;
        assertNull(gameQuiz.getQuest());
    }

    @org.junit.Test
    public void getQuestAtHintLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList <>();
        gameQuiz.questList.add(new Question("Самый мелкий океан? :" +
                "1.Тихий; 2.Атлантический; 3.Индийский; 4.Северный ледовитый", "4"));
        gameQuiz.amountQuest = 1;
        gameQuiz.lastAns = "hint";
        gameQuiz.lastQuest = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс", "3");
        assertEquals("Третья планета от Солнца ", gameQuiz.getQuest().text);
    }

    @org.junit.Test
    public void getQuestAtNotLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList <>();
        gameQuiz.questList.add(new Question("Самый мелкий океан? :" +
                "1.Тихий; 2.Атлантический; 3.Индийский; 4.Северный ледовитый", "4"));
        gameQuiz.amountQuest = 1;
        gameQuiz.lastQuest = null;
        assertEquals("Самый мелкий океан? ", gameQuiz.getQuest().text);
    }

    @org.junit.Test
    public void correctAnswerAtRightAnswer() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList <>();
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.questList.add(gameQuiz.question);
        gameQuiz.amountQuest = gameQuiz.questList.size();
        //gameQuiz.lastQuest = null;
        //gameQuiz.goodAnswer = new ArrayList <>();
        //String botAns = "Молодец";
        //gameQuiz.goodAnswer.add(botAns);
        gameQuiz.correctAnswer("3");
        assertEquals(100, gameQuiz.point);
    }

    @org.junit.Test
    public void correctAnswerAtErrorAnswer() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList<>();
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.questList.add(gameQuiz.question);
        gameQuiz.amountQuest = gameQuiz.questList.size();
//        gameQuiz.lastQuest = null;
//        gameQuiz.badAnswer = new ArrayList <>();
//        String botAns = "Неправильно";
//        gameQuiz.badAnswer.add(botAns);
        gameQuiz.correctAnswer("1");
        assertEquals(-300, gameQuiz.point);
    }

    @org.junit.Test
    public void correctAnswerAtHintLine() throws FileException, IOException {
        GameQuiz gameQuiz = new GameQuiz("1");
        gameQuiz.questList = new ArrayList <>();
        gameQuiz.question = new Question("Третья планета от Солнца :1.Меркурий; 2.Плутон; 3.Земля; 4.Марс","3");
        gameQuiz.questList.add(gameQuiz.question);
        //gameQuiz.lastQuest = null;
        gameQuiz.correctAnswer("hint");
        assertEquals(gameQuiz.question, gameQuiz.lastQuest);
    }
}