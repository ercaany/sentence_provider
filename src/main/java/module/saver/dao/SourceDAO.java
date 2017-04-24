package module.saver.dao;

import com.datastax.driver.core.*;
import model.Source;
import module.saver.ModelVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ercan on 09.04.2017.
 */
public class SourceDAO {
    private final static Logger logger = LoggerFactory.getLogger(SourceDAO.class);
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
        try{
            BoundStatement bound = preparedStatement.bind(
                    source.getSourceName(), source.getBestWords(),
                    new Timestamp(System.currentTimeMillis()), source.getWordCountMap());
            session.execute(bound);
            logger.info("SourceDAO insert başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("SourceDAO insert hata verdi.");
        }
    }

    public void update(Source source){
        try{
            BoundStatement bound = preparedStatement.bind(
                    source.getBestWords(), new Timestamp(System.currentTimeMillis()),
                    source.getWordCountMap(), source.getSourceName());
            session.execute(bound);
            logger.info("SourceDAO update başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("SourceDAO update hata verdi.");
        }
    }

    public void delete(Source source){
        try{
            BoundStatement bound = preparedStatement.bind(source.getSourceName());
            session.execute(bound);
            logger.info("SourceDAO delete başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("SourceDAO delete hata verdi.");
        }
    }

    public void insertBatch(List<Source> sourceList){
        try{
            BatchStatement batch = new BatchStatement();
            prepareForInsert();

            for(Source source: sourceList){
                BoundStatement bound = preparedStatement.bind(
                        source.getSourceName(), source.getBestWords(),
                        new Timestamp(System.currentTimeMillis()), source.getWordCountMap());
                batch.add(bound);
            }

            session.execute(batch);
            logger.info("SourceDAO insertBatch başarıyla tamamlandı.");
        } catch(Exception ex){
            logger.warn("SourceDAO insertBatch hata verdi.");
        }

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
