package directory;

import configuration.Configuration;
import lombok.Getter;
import models.PhoneNumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PhoneDirectory {

    private String directoryPath = Configuration.PHONE_DIRECTORY_PATH;
    private static PhoneDirectory phoneDirectory = new PhoneDirectory();
    private List<PhoneNumber> listOfNumbers = new ArrayList<PhoneNumber>();

    public static PhoneDirectory getInstance() {
        return phoneDirectory;
    }

    private PhoneDirectory(){
       loadNumbersInDirectory();
    }

    private void loadNumbersInDirectory() {
        System.out.println("Loading Phone Directory...");
        try {
            listOfNumbers = Files.lines(Paths.get(directoryPath))
                    .map(PhoneNumber::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
