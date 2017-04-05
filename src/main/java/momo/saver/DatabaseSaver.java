package momo.saver;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 05.04.2017.
 */
public class DatabaseSaver implements ContentSaver {

    public void save(Set<String> sentences, String url) {
        Session session = createSession("tp");
        List<String> sentenceList = new ArrayList<String>();

        sentenceList.addAll(sentences);

        Statement st = new SimpleStatement("INSERT INTO paragraph " +
                "(paragraph_key, sentences, saved_date) " +
                "VALUES ('"+url+"', ?, toTimestamp(now()));", sentenceList);
        session.execute(st);

    }

    private Session createSession(String keyspaceName){
        // create a builder object for Cluster
        Cluster.Builder clusterBuilder = Cluster.builder();

        // add a contact point (IP address of the node) and build it.
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        /*
        * This method creates a new session and initializes it.
        * If you already have a keyspace, you can set it to the existing one by passing
        * the keyspace name in string format to this method.
        */
        Session session = cluster.connect(keyspaceName);

        return session;
    }
}
