package command;

import application.Admin;

/**
 * Created by mustafa on 23.04.2017.
 */
public class StartCommand implements Command {
    public boolean execute(String parameter) {
        if(Admin.crawler.isAlive()) {
            System.out.println("Thread zaten çalışıyor.");
            return false;
        } else {
            try {
                Admin.crawler.start();
                Thread.sleep(1000);
                Admin.processor.start();
                Thread.sleep(1000);
                Admin.dbSaver.start();
                return true;
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                return false;
            }
        }
    }
}
