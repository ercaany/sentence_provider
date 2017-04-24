package module.saver.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import model.UniqueWord;
import module.saver.ModelVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ercan on 09.04.2017.
 */
public class UniqueWordDAO {
    private final static Logger logger = LoggerFactory.getLogger(UniqueWordDAO.class);
    private String keyspace;
    private String tableName;
    private Session session;
    private PreparedStatement preparedStatement;

    public UniqueWordDAO(){
        this.keyspace = ModelVariables.keyspace;
        this.tableName = ModelVariables.uniqueWordTableName;
        session = createSession();
    }

    public void insert(UniqueWord uniqueWord){
        try{
            BoundStatement bound = preparedStatement.bind(
                    uniqueWord.getWord(), uniqueWord.getDocumentSet());
            session.execute(bound);
            logger.info("UniqueWordDAO insert başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("UniqueWordDAO insert hata verdi.");
        }
    }

    public void update(UniqueWord uniqueWord){
        try{
            BoundStatement bound = preparedStatement.bind(
                    uniqueWord.getDocumentSet(), uniqueWord.getWord());
            session.execute(bound);
            logger.info("UniqueWordDAO update başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("UniqueWordDAO update hata verdi.");
        }
    }

    public void delete(UniqueWord uniqueWord){
        try{
            BoundStatement bound = preparedStatement.bind(uniqueWord.getWord());
            session.execute(bound);
            logger.info("UniqueWordDAO delete başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("UniqueWordDAO delete hata verdi.");
        }
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

    private Session createSession(){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspace);
    }
}
