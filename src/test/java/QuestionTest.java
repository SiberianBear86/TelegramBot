import static org.junit.Assert.assertEquals;

public class QuestionTest {

    @org.junit.Test
    public void getQuestFromLines() {
        Question quest = new Question("Самая высокая вершина :1.Джомолунгма; 2.Эльбрус; 3.Чогори; 4.Кения", "1");
        assertEquals("Самая высокая вершина ", quest.text);
        assertEquals("1", quest.rightAnswer);
        assertEquals("1.Джомолунгма", quest.answers[0]);
    }

}