package application;

import command.AddCommand;
import command.Command;
import module.crawler.WebCrawler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mustafa on 23.04.2017.
 */
public class CommandSet {
    private Map<String, Command> commandMap;

    public CommandSet(WebCrawler crawler) {
        commandMap = new HashMap<String, Command>();
        commandMap.put("add", new AddCommand(crawler));
    }

    public void run(String command) {
        String[] parseList = command.split(" ");

        if(commandMap.get(parseList[0]) == null) {
            System.out.println("Komut bulunamadı");
        } else {
            commandMap.get(parseList[0]).execute(parseList[1]);
        }
    }
}
