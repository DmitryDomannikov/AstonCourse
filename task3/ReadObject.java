package task3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadObject {
    public static void main(String[] args) throws MyException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("testFile.bin")))) {

            Person people = (Person) ois.readObject();
            System.out.println(people);

        } catch (IOException | ClassCastException | ClassNotFoundException e) {
                throw new MyException("Анатоле здесь Ошипка!!!" + e);
        }
    }
}

