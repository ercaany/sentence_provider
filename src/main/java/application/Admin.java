package application;

import module.crawler.WebCrawler;
import module.processor.Processor;
import module.saver.DBSaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by mustafa on 23.04.2017.
 */
public class Admin {
    private final static Logger logger = LoggerFactory.getLogger(Admin.class);
    public static WebCrawler crawler;
    public static Processor processor;
    public static DBSaver dbSaver;

    static {
        dbSaver = new DBSaver();
        processor = new Processor(dbSaver);
        crawler = new WebCrawler(processor);

        //ilklendirmeleri yap
        //global parameters ı oluştur
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.run();
    }

    private void run() {
        logger.info("selam");
        CommandSet commandSet = new CommandSet();
        boolean next = true;

        while(next) {
            Scanner in = new Scanner(System.in);
            System.out.print("Komut giriniz=>");
            String commandInput = in.nextLine();

            if(!commandInput.equals("exit"))
                commandSet.run(commandInput);
            else
                next = false;
        }
    }
}
