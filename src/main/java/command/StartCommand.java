package command;

import application.Admin;
import module.saver.DBSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mustafa on 23.04.2017.
 */
public class StartCommand implements Command {
    private final static Logger logger = LoggerFactory.getLogger(DBSaver.class);

    public boolean execute(String parameter) {
        try {
            int urlCount = Integer.parseInt(parameter); // madem limit var, url kuyruğunun boyutunu baştan belirle,
                                                        // ya da bunu array ile yap
                                                        // url sayısı ile birlikte zaman sınırlaması da konabilir

            if(Admin.crawler.getUnvisitedPageUrls().size() == 0) {
                System.out.println("Lütfen önce seed url girin.");
                return false;
            }
            Admin.crawler.crawl(urlCount);
            return true;
        } catch (Exception ex) { //string to int exceptionu olarak düzelt
            System.out.println("Lütfen start ile birlikte sayısal bir değer girin."); // parametre kontrolünü ayrı metotta yap
            logger.warn(ex.getMessage());
            logger.warn(ex.toString());
            ex.printStackTrace();
            return false;
        }
    }
}
