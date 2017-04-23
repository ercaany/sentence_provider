package module.database.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import module.database.ModelVariables;
import module.processor.model.Sentence;

/**
 * Created by ercan on 09.04.2017.
 */
public class SentenceDAO {
    private String keyspace;
    private String tableName;
    private Session session;
    private PreparedStatement preparedStatement;

    public SentenceDAO(){
        this.keyspace = ModelVariables.keyspace;
        this.tableName = ModelVariables.sentenceTableName;
        session = createSession();
    }

    public void insert(Sentence sentence){
        BoundStatement bound = preparedStatement.bind(sentence.getOriginalSentence(),
                sentence.getQuestions(), sentence.getStemmedWordsList(),
                sentence.getTags(), sentence.getTokenList());
        session.execute(bound);
    }

    public void update(Sentence sentence){
        BoundStatement bound = preparedStatement.bind(sentence.getQuestions(), sentence.getStemmedWordsList(),
                sentence.getTags(), sentence.getTokenList());
        session.execute(bound);
    }

    public void delete(Sentence sentence){
        BoundStatement bound = preparedStatement.bind(sentence.getOriginalSentence());
        session.execute(bound);
    }

    public void prepareForInsert(){
        preparedStatement = session.prepare(
                "INSERT INTO " + tableName + " (original_sentence, questions, " +
                        "stemmed_words_list, tags, token_list) values (?, ?, ?, ?, ?)");
    }

    public void prepareForUpdate(){
        preparedStatement = session.prepare("UPDATE " + tableName +" " +
                "SET questions= ?, stemmed_words_list=?, tags=?, token_list=?" +
                "WHERE original_sentence=?");
    }

    public void prepareForDelete(){
        preparedStatement = session.prepare(
                "DELETE FROM " + tableName + " WHERE original_sentence=?");
    }

    private Session createSession(){
        Cluster.Builder clusterBuilder = Cluster.builder();
        Cluster cluster = clusterBuilder.addContactPoint("127.0.0.1").build();

        return cluster.connect(keyspace);
    }
}
