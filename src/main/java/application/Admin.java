package application;

import module.crawler.WebCrawler;
import module.processor.Processor;
import module.saver.DBSaver;

import java.util.Scanner;

/**
 * Created by mustafa on 23.04.2017.
 */
public class Admin {
    public static WebCrawler crawler;
    public static Processor processor;
    public static DBSaver dbSaver;

    static {
        crawler = new WebCrawler();
        processor = new Processor(crawler);
        dbSaver = new DBSaver(processor);

        //ilklendirmeleri yap
        //global parameters ı oluştur
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.run();
    }

    private void run() {
        CommandSet commandSet = new CommandSet(crawler);
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
