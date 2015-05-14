package templates.java.core.serialization;

import org.junit.Assert;

import java.io.*;

/**
 * Author: Gennadii Cherniaiev
 * Date: 5/14/2015
 */
public class Start {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializeObjectImplementsSerializable();
    }

    private static void serializeObjectImplementsSerializable() throws IOException, ClassNotFoundException {
        System.out.println("create object to serialize");
        final String fileName = "1.dat";
        // serialize
        PersonSer p = new PersonSer();
        p.setAge(15);
        p.setName("Bella");

        writeObject(fileName, p);

        // deserialize
        final PersonSer stored = readObject(fileName);

        Assert.assertEquals(p.getAge(), stored.getAge());
        Assert.assertEquals(p.getName(), stored.getName());

    }

    private static <T> T readObject(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInput objectInput = new ObjectInputStream(fileInputStream);
        System.out.println("before readObject");
        final T stored = (T) objectInput.readObject();
        System.out.println("after readObject");
        objectInput.close();
        return stored;
    }

    private static void writeObject(String fileName, PersonSer p) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
        System.out.println("before writeObject");
        objectOutput.writeObject(p);
        System.out.println("after writeObject");
        objectOutput.close();
    }

}
