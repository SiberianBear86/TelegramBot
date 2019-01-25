import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

class Config {
    private static final String configurationBotFile = "config/bot/bot.properties";

    static String botName;
    static String botToken;

    static void load(){
        Properties botSettings = new Properties();
        try(InputStream is = new FileInputStream(new File(configurationBotFile))){
            botSettings.load(is);
            is.close();
            System.out.println("Configs load");
        } catch(Exception e){
            System.out.println("Config don't load");
        }
        botName = botSettings.getProperty("BotName");
        botToken = botSettings.getProperty("BotToken");
    }
}
