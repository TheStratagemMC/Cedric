package code.akselm.cedric;

/**
 * Created by Axel on 3/1/2016.
 */
public class Cedric {
    protected static Bot bot;

    public static void setBot(Bot b){
        bot = b;
    }

    public static Bot getBot(){
        return bot;
    }

    public static DataManager getDataManager(){
        return bot.getDataManager();
    }
}
