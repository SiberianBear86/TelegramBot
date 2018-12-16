import static org.junit.Assert.assertEquals;

public class QuestionTest {

    @org.junit.Test
    public void testGetQuestFromLines() {
        Question quest = new Question("Самая высокая вершина :1.Джомолунгма; 2.Эльбрус; 3.Чогори; 4.Кения", "1");
        assertEquals("Самая высокая вершина ", quest.text);
        assertEquals("1", quest.rightAnswer);
        assertEquals("1.Джомолунгма", quest.answers[0]);
        assertEquals("2.Эльбрус", quest.answers[1]);
        assertEquals("3.Чогори", quest.answers[2]);
        assertEquals("4.Кения", quest.answers[3]);
    }
}