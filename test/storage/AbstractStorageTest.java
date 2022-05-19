package storage;

import exception.NotExistStorageException;
import exception.StorageException;
import exception.ExitStorageException;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


import static basejava4.TestData.TestData.*;
import static basejava4.TestData.TestData.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp()throws Exception{
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
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
        Resume newResume = new Resume(UUID_1,"New Name" );
        R1.addContact(ContactType.MAIL,"mail1@google.com");
        R1.addContact(ContactType.SKYPE,"NewSkype");
        R1.addContact(ContactType.MOBILE,"+380 99 559-45-68");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = ExitStorageException.class)
    public void updateNotExist()throws Exception{
        storage.get("dummy");
    }

    @Test
    public void getAllSorted()throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3,list.size());
        List<Resume> sortedResumes = Arrays.asList(R1,R2,R3);
        Collections.sort(sortedResumes);
        assertEquals(list, sortedResumes);
    }

    @Test
    public void save()throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExitStorageException.class)
    public void saveExist()throws Exception{
        storage.save(R1);
    }

    @Test(expected = StorageException.class)
    public void delete()throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist()throws Exception{
        storage.delete("dummy");
    }

    @Test
    public void get()throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }



    private void assertSize(int size){
        assertEquals(size,storage.size());
    }

    private void assertGet(Resume r){
        assertEquals(r,storage.get(r.getUuid()));
    }
}
