package templates.java.core.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Author: Gennadii Cherniaiev
 * Date: 5/14/2015
 */
public class PersonSer implements Serializable {
    private String name;
    private int age;

    public PersonSer() {
        System.out.println(getClass().getName() + " : constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        System.out.println(getClass().getName() + " : readObject");
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        System.out.println(getClass().getName() + " : writeObject");
    }
}
