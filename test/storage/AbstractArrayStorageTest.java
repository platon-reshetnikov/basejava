package storage;

import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp()throws Exception{
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(3);
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(3,array.length);
       // assertEquals();

    }

    @Test
    void save() {
       // storage.save();
        assertSize(4);
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

   // @Test(expected = NotExistStorageException.class)
    public void gitNotExist()throws Exception{
        storage.get("dummy");
    }

    private void assertSize(int size){
        assertEquals(size,storage.size());
    }
}