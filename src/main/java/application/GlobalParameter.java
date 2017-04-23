package application;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mustafa on 23.04.2017.
 */
public class GlobalParameter {
    public static Set<String> visitedUrlSet; //değişikliklerin vt ye yansıtılması gerek
    public static Set<String> savedTokenSet; //değişikliklerin vt ye yansıtılması gerek
    public static int newIndexUrl;
    public static int newIndexToken;


    static {
        visitedUrlSet = new HashSet<String>();
        savedTokenSet = new HashSet<String>();

        //vtden okuduktan sonra

        newIndexUrl = visitedUrlSet.size();
        newIndexToken = visitedUrlSet.size();
    }

}
