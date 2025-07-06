package task3;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteObject {
    public static void main(String[] args) throws MyException {

        Person people = new Person(1, "Serega");

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("testFile.bin")))){
            oos.writeObject(people);
        } catch (IOException e) {
            throw new MyException ("Анатоле здесь Ошипка!!!" + e);
        }
    }
}
