package command;

import application.Admin;

/**
 * Created by mustafa on 23.04.2017.
 */
public class AddCommand implements Command {
    private final String urlRgex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)\n";

    public boolean execute(String[] parameter) {
        if(parameter[1].matches(urlRgex)) {
            Admin.crawler.addUrl(parameter[1]);
            System.out.println(true);
            return true;
        } else {
            System.out.println("Lütfen geçerli bir url girin.");
            return true;
        }

    }
}
