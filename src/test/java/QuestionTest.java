import static org.junit.Assert.assertEquals;

public class QuestionTest {

    @org.junit.Test
    public void testGetQuestFromLines() {
        Question quest = new Question("Самый большой океан? :1.Тихий; 2.Атлантический; 3.Индийский; 4.Северный ледовитый", "1");
        assertEquals("Самый большой океан? ", quest.text);
        assertEquals("1", quest.rightAnswer);
        assertEquals("1.Тихий", quest.answers[0]);
        assertEquals("2.Атлантический", quest.answers[1]);
        assertEquals("3.Индийский", quest.answers[2]);
        assertEquals("4.Северный ледовитый", quest.answers[3]);
    }
}