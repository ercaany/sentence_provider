package command;

import module.crawler.WebCrawler;

/**
 * Created by mustafa on 23.04.2017.
 */
public class AddCommand implements Command {
    private WebCrawler crawler;

    public AddCommand(WebCrawler crawler) {
        this.crawler = crawler;
    }

    public boolean execute(String parameter) {
        crawler.addUrl(parameter);
        return false;
    }
}
