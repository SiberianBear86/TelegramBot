import java.util.Arrays;

enum Commands{
    START(new StartCommand()),
    HINT(new HintCommand()),
    JOKE(new JokeCommand()),
    HELP(new HelpCommand());

    Command command;
    Commands(Command command) {
        this.command = command;
    }

    public static Commands parse(String text) {
        if (!Arrays.toString(Commands.values()).contains(text))
            return null;
        return valueOf(text);
    }
}