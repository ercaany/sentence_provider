package module.saver;

import com.datastax.driver.core.BatchStatement;
import model.Sentence;
import module.processor.Processor;
import model.Source;
import module.saver.dao.SentenceDAO;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mustafa on 23.04.2017.
 */
public class DBSaver extends Thread {
    private static int batchSize = 100;
    private Queue<Source> dataToSaveQueue;
    private Processor processor;
    private SentenceDAO sentenceDAO;

    public DBSaver(Processor processor) {
        dataToSaveQueue = new LinkedList<Source>();
        this.processor = processor;
        sentenceDAO = new SentenceDAO();
        sentenceDAO.prepareForInsert();
    }

    public void run() {
        while(processor.isAlive()) {
            if(processor.waitingSize > batchSize) {
                //datatosave lock lu olduğu sürece bekle - while
                //locktan çıkınca sen lockla

                for(int i = 0; i < batchSize; i++) {
                    dataToSaveQueue.offer(processor.getDataToSaveQueue().poll());
                }

                BatchStatement batch = new BatchStatement();

                for(int i = 0; i < batchSize; i++) {
                    for(Sentence sentence: dataToSaveQueue.poll().getSentenceSet())
                    batch.add(sentenceDAO.getBoundForInsert(sentence));
                }

                sentenceDAO.executeBatch(batch);
                processor.waitingSize = 0;
                //lock u bırak
                //kuyruk içindekiler kayıt edilecek
            }
        }

        //is alive değilse kalanı tamamen kaydet
        //sonra sen de öl
    }
}
