package module.saver.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import module.saver.ModelVariables;
import model.Source;

import java.sql.Timestamp;

/**
 * Created by ercan on 09.04.2017.
 */
public class SourceDAO {
    private String keyspace;
    private String tableName;
    private Session session;
    private PreparedStatement preparedStatement;

    public SourceDAO(){
        this.keyspace = ModelVariables.keyspace;
        this.tableName = ModelVariables.sourceTableName;
        session = createSession();
    }

    public void insert(Source source){
        BoundStatement bound = preparedStatement.bind(
                source.getSourceName(), source.getBestWords(),
                new Timestamp(System.currentTimeMillis()), source.getWordCountMap());
        session.execute(bound);
    }

    public void update(Source source){
        BoundStatement bound = preparedStatement.bind(
                source.getBestWords(), new Timestamp(System.currentTimeMillis()),
                source.getWordCountMap(), source.getSourceName());
        session.execute(bound);
    }

    public void delete(Source source){
        BoundStatement bound = preparedStatement.bind(source.getSourceName());
        session.execute(bound);
    }

    public void prepareForInsert(){
        preparedStatement = session.prepare(
                "INSERT INTO " + tableName + " (source_name, best_words, " +
                        "last_updated_date, word_count_map) values (?, ?, ?, ?)");
    }

    public void prepareForUpdate(){
        preparedStatement = session.prepare("UPDATE " + tableName +" " +
                "SET best_words= ?," + "last_updated_date=?, " + "word_count_map = word_count_map + ?" +
                "WHERE source_name=?");
    }

    public void prepareForDelete(){
        preparedStatement = session.prepare(
                "DELETE FROM " + tableName + " WHERE source_name=?");
    }

    private Session createSession(){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspace);
    }



}
