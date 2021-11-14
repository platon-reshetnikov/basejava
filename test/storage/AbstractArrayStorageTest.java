package storage;

import exception.NotExistStorageException;
import exception.StorageException;
import exception.ExitStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final Resume Resume_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume Resume_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume Resume_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume Resume_4 = new Resume(UUID_4);

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
    public void size()throws Exception {
        assertSize(3);
    }

    @Test
    public void clear()throws Exception {
        storage.clear();
        assertSize(3);
    }

    @Test
    public void update()throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = ExitStorageException.class)
    public void updateNotExist()throws Exception{
        storage.get("dummy");
    }

    @Test
    public void getAll()throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3,array.length);
        assertEquals(Resume_1,array[0]);
        assertEquals(Resume_2,array[1]);
        assertEquals(Resume_3,array[2]);

    }

    @Test
    public void save()throws Exception {
        storage.save(Resume_4);
        assertSize(4);
        assertGet(Resume_4);
    }

    @Test(expected = ExitStorageException.class)
    public void saveExist()throws Exception{
        storage.save(Resume_1);
    }

    @Test(expected = ExitStorageException.class)
    public void saveOverflow()throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        storage.save(new Resume());
    }

    @Test(expected = StorageException.class)
    public void delete()throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void get()throws Exception {
        assertGet(Resume_1);
        assertGet(Resume_2);
        assertGet(Resume_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist()throws Exception{
        storage.delete("dummy");
    }

    private void assertSize(int size){
        assertEquals(size,storage.size());
    }
    private void assertGet(Resume r){
        assertEquals(r,storage.get(r.getUuid()));
    }

    private class ExistStorageException {
    }
}