package application;

import module.crawler.WebCrawler;

import java.util.Scanner;

/**
 * Created by mustafa on 23.04.2017.
 */
public class Admin {
    private WebCrawler crawler;

    static {
        //ilklendirmeleri yap
        //global parameters ı oluştur
    }

    public Admin(WebCrawler crawler) {
        this.crawler = crawler;
    }

    public static void main(String[] args) {
        Admin admin = new Admin(new WebCrawler());
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
