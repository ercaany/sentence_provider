package command;

import application.Admin;
import module.crawler.WebCrawler;
import module.processor.Processor;
import module.saver.DBSaver;

/**
 * Created by mustafa on 23.04.2017.
 */
public class StopCommand implements Command {
    public boolean execute(String parameter) {
        try {
            Admin.crawler.interrupt();
            Admin.crawler = new WebCrawler();
            Thread.sleep(500);
            Admin.processor.interrupt();
            Admin.processor = new Processor(Admin.crawler);
            Thread.sleep(500);
            Admin.dbSaver = new DBSaver(Admin.processor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }
}
