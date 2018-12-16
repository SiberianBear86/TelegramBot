import org.junit.Test;

import static org.junit.Assert.*;

public class CommandsTest {

    @Test
    public void testGetCommands() {
        assertEquals(Commands.START, Commands.parse("START"));
        assertEquals(Commands.HINT, Commands.parse("HINT"));
        assertEquals(Commands.HELP, Commands.parse("HELP"));
        assertEquals(Commands.JOKE, Commands.parse("JOKE"));
        assertNull(Commands.parse("SOMETHING"));
    }
}