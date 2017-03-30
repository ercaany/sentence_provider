package momo.saver;

import java.util.Set;

/**
 * Created by ercan on 30.03.2017.
 */
public interface ContentSaver {
    void save(Set<String> sentences, String url);
}
