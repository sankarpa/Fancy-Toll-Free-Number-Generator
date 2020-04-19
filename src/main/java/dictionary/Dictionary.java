package dictionary;

import com.google.common.collect.Sets;
import configuration.Configuration;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

@Getter
public class Dictionary {

    public static Dictionary dictionary = new Dictionary();
    private String dictionaryPath = Configuration.DICTIONARY_PATH;
    private Properties dictionaryData = new Properties();

    private Dictionary() {
        initDictionaryData();
    }

    public static Dictionary getInstance() {
        return dictionary;
    }

    private void initDictionaryData() {
        try {
            System.out.println("Loading Dictionary Data...");
            BufferedReader br = Files.newBufferedReader(Paths.get(dictionaryPath));
            dictionaryData.load(br);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean hasEntry(String number) {
        return this.dictionaryData.getProperty(number) != null;
    }
}
