package module.saver;

import com.datastax.driver.core.BatchStatement;
import model.Sentence;
import model.Source;
import module.saver.dao.SentenceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Created by mustafa on 23.04.2017.
 */
public class DBSaver {
    private final static Logger logger = LoggerFactory.getLogger(DBSaver.class);
    private static int batchSize = 2;
    private SentenceDAO sentenceDAO;

    public DBSaver() {
        sentenceDAO = new SentenceDAO();
        sentenceDAO.prepareForInsert();
    }

    public void save(Queue<Source> dataToSaveQueue) {
        logger.trace("Running");

        BatchStatement batch = new BatchStatement();
        int j = 0; //DENEME AMAÇLI batch too size hatası
        for(int i = 0; i < batchSize; i++) {
            Source source = dataToSaveQueue.poll();
            for(Sentence sentence: source.getSentenceSet()) {
                if(j < 5) {
                    System.out.println("batch" + j);
                    batch.add(sentenceDAO.getBoundForInsert(sentence));
                    j++; //refactoring !!
                }
            }
        }

        //Sentence sentence = (Sentence) dataToSaveQueue.poll().getSentenceSet().toArray()[0];
        //sentenceDAO.insert(sentence);
        logger.trace("execute batch çağırıldı");
        sentenceDAO.executeBatch(batch);
    }
}
