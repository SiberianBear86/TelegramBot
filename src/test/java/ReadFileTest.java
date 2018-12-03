import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReadFileTest {
    @org.junit.Test
    public void getQuestFrom1Txt() throws FileException{
        ReadFile read = new ReadFile();
        read.readFile("1");
        assertEquals("1.txt", read.theme1);
    }

    @org.junit.Test
    public void getQuestFrom2Txt() throws FileException{
        ReadFile read = new ReadFile();
        read.readFile("2");
        assertEquals("2.txt", read.theme1);
    }

    @org.junit.Test
    public void getBotAnswer() throws FileException {
        assertEquals("Ты на верном пути к своему миллиону!", new ReadFile().getAnswer("GoodAnswer.txt").get(1));
        assertEquals("И как мир таких бездарей терпит.", new ReadFile().getAnswer("BadAnswer.txt").get(1));
    }

    @org.junit.Test
    public void getQuestList() throws FileException {
        ReadFile read = new ReadFile();
        read.readFile("2");
        List<Question> questList = read.getList();
        assertEquals("Самый мелкий океан? ", questList.get(1).text);
    }

}