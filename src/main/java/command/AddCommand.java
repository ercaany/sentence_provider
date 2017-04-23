package command;

import application.Admin;

/**
 * Created by mustafa on 23.04.2017.
 */
public class AddCommand implements Command {
    public boolean execute(String parameter) {
        Admin.crawler.addUrl(parameter);
        return true;
    }
}
