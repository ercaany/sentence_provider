package module.saver.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import model.UniqueWord;
import module.saver.ModelVariables;

/**
 * Created by ercan on 09.04.2017.
 */
public class UniqueWordDAO {
    private String keyspace;
    private String tableName;
    private Session session;
    private PreparedStatement preparedStatement;

    public UniqueWordDAO(){
        this.keyspace = ModelVariables.keyspace;
        this.tableName = ModelVariables.uniqueWordTableName;
        session = createSession();
    }

    public void prepareForInsert(){
        preparedStatement = session.prepare(
                "INSERT INTO " + tableName + " (word, documents) values (?, ?)");
    }

    public void prepareForUpdate(){
        preparedStatement = session.prepare("UPDATE " + tableName +" " +
                "SET documents = documents+?" +
                "WHERE word=?");
    }

    public void prepareForDelete(){
        preparedStatement = session.prepare(
                "DELETE FROM " + tableName + " WHERE word=?");
    }

    public void insert(UniqueWord uniqueWord){
        BoundStatement bound = preparedStatement.bind(
                uniqueWord.getWord(), uniqueWord.getDocumentSet());
        session.execute(bound);
    }

    public void update(UniqueWord uniqueWord){
        BoundStatement bound = preparedStatement.bind(
                uniqueWord.getDocumentSet(), uniqueWord.getWord());
        session.execute(bound);
    }

    public void delete(UniqueWord uniqueWord){
        BoundStatement bound = preparedStatement.bind(uniqueWord.getWord());
        session.execute(bound);
    }

    private Session createSession(){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspace);
    }
}
