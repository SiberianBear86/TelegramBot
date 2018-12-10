import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

class Config {
    private static final String configurationBotFile = "./config/bot/bot.properties";

    static String botName;
    static String botToken;

    static void load(){
        Properties botSettings = new Properties();
        try(InputStream is = new FileInputStream(new File(configurationBotFile))){
            botSettings.load(is);
        } catch(Exception e){
            e.printStackTrace();
        }
        botName = botSettings.getProperty("BotName", "Quiz86Bot");
        botToken = botSettings.getProperty("BotToken", "778550876:AAF_5E6G0pYVi_58cEdaTz9BuuoFkpsEQnY");
    }
}
