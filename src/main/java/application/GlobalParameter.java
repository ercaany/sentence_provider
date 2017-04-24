package application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mustafa on 23.04.2017.
 */
public class GlobalParameter {
    public static Set<String> savedTokenSet; //değişikliklerin vt ye yansıtılması gerek
    public static Set<String> newTokenSet;
    public static int sessionCount;
    public static Map<String, Integer> parameterMap;


    static {
        savedTokenSet = new HashSet<String>();
        //savedTokenList i vt den oku
        newTokenSet = new HashSet<String>();
        parameterMap = new HashMap<String, Integer>();
        parameterMap.put("sessionCount", 5);
    }

}
