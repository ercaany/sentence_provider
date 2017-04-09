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
    private String tableName;
    private String keyspace;

    public DatabaseSaver(String tableName, String keyspace){
        this.tableName = tableName;
        this.keyspace = keyspace;
    }

    public void save(Set<String> sentences, String url) {
        Session session = createSession(keyspace);
        List<String> sentenceList = new ArrayList<String>();

        sentenceList.addAll(sentences);

        Statement st = new SimpleStatement("INSERT INTO paragraph " +
                "(paragraph_key, sentences, saved_date) " +
                "VALUES ('"+url+"', ?, toTimestamp(now()));", sentenceList);
        session.execute(st);
    }

    private Session createSession(String keyspaceName){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspaceName);
    }
}
