package storage;

import exception.ExitStorageException;
import model.Resume;
import org.junit.Test;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = ExitStorageException.class)
    public void saveOverflow()throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        storage.save(new Resume("Overflow"));
    }


}