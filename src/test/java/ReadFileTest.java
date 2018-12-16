import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReadFileTest {
    @org.junit.Test
    public void testGetQuestFrom1Txt() throws FileException{
        ReadFile read = new ReadFile();
        read.readFile("1");
        assertEquals("1.txt", read.theme1);
    }

    @org.junit.Test
    public void testGetQuestFrom2Txt() throws FileException{
        ReadFile read = new ReadFile();
        read.readFile("2");
        assertEquals("2.txt", read.theme1);
    }

    @org.junit.Test
    public void testGetBotAnswer() throws FileException {
        assertEquals("Ты на верном пути к своему миллиону!", new ReadFile().getAnswer("GoodAnswer.txt").get(1));
        assertEquals("И как мир таких бездарей терпит.", new ReadFile().getAnswer("BadAnswer.txt").get(1));
    }

    @org.junit.Test
    public void testGetQuestList() throws FileException {
        ReadFile read = new ReadFile();
        read.readFile("2");
        List<Question> questList = read.getList();
        assertEquals("Самый мелкий океан? ", questList.get(1).text);
    }

}