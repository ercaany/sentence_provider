package momo.saver;

import zemberek.langid.LanguageIdentifier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Created by ercan on 30.03.2017.
 */
public class FileContentSaver implements ContentSaver {
    private int fileNameIndex;

    public FileContentSaver(int fileNameIndex){
        this.fileNameIndex = fileNameIndex;
    }

    public void save(Set<String> sentences, String url) {
        try{
            LanguageIdentifier languageIdentifier  = LanguageIdentifier.fromInternalModels();
            PrintWriter writer = new PrintWriter(
                    new FileOutputStream(
                            fileNameIndex + ".txt", true));

            writer.println("#" + url + "\n");
            for(String sentence: sentences){
                if(languageIdentifier.identify(sentence).equals("tr")){
                    writer.println(sentence);
                }
            }

            writer.println();
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }

    //getter-setter
    public int getFileNameIndex() {
        return fileNameIndex;
    }

    public void setFileNameIndex(int fileNameIndex) {
        this.fileNameIndex = fileNameIndex;
    }
}
