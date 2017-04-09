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
        /*ContentHandler contentHandler = new ContentHandler("/home/ercan/Bitirme Projesi/corpus/corpus.data", new FileContent());
        contentHandler.fetchContent();
        Map<String, List<String>> map =  contentHandler.getAbstractsWithTheirSentences();*/

        String keyspace = "tp";
        String tableName = "source";

        String query = "CREATE TABLE source(" +
                "source_name text PRIMARY KEY," +
                "word_count_map map<text, int>," +
                "best_words set<text>," +
                "last_updated_date TIMESTAMP);";


        Session session = createSession(keyspace);
        session.execute(query);

        /*for(String key: map.keySet()){
            Statement st = new SimpleStatement("INSERT INTO paragraph " +
                    "(paragraph_key, sentences, saved_date) " +
                    "VALUES ('"+key+"', ?, toTimestamp(now()));", map.get(key));
            session.execute(st);
        }*/
    }

    public static Session createSession(String keyspaceName){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspaceName);
    }
}
