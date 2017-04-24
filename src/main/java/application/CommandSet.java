package application;

import command.AddCommand;
import command.Command;
import command.SetCommand;
import command.StartCommand;
import module.crawler.WebCrawler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mustafa on 23.04.2017.
 */
public class CommandSet {
    private Map<String, Command> commandMap;

    public CommandSet() {
        commandMap = new HashMap<String, Command>();
        commandMap.put("add", new AddCommand());
        commandMap.put("start", new StartCommand());
        commandMap.put("set", new SetCommand());
    }

    public void run(String command) {
        String[] parseList = command.split(" ");

        if(parseList.length < 2) {
            System.out.println("Eksik parametre"); //bu kontrol komutlarda da olmalı. parametre sayısı değişebilir
        } else if(commandMap.get(parseList[0]) == null) {
            System.out.println("Komut bulunamadı");
        } else {
            commandMap.get(parseList[0]).execute(parseList);
        }
    }
}
