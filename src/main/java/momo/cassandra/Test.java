package momo.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import momo.content.ContentHandler;
import momo.content.FileContent;

import java.util.List;
import java.util.Map;

/**
 * Created by ercan on 05.04.2017.
 */
public class Test {
    public static void main(String args[]){
        ContentHandler contentHandler = new ContentHandler("/home/ercan/Bitirme Projesi/corpus/corpus.data", new FileContent());
        contentHandler.fetchContent();
        Map<String, List<String>> map =  contentHandler.getAbstractsWithTheirSentences();

        /*String query = "CREATE TABLE sentence(sentence_map map<String, List<String>> PRIMARY KEY, "
                + "saved_date datetime default CURRENT_TIMESTAMP);";*/

        Session session = createSession("tp");

        for(String key: map.keySet()){
            Statement st = new SimpleStatement("INSERT INTO paragraph " +
                    "(paragraph_key, sentences, saved_date) " +
                    "VALUES ('"+key+"', ?, toTimestamp(now()));", map.get(key));
            session.execute(st);
        }
    }

    public static Session createSession(String keyspaceName){
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
