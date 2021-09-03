
import java.io.IOException;
import java.util.Arrays;



/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    Integer storageSize = 0;

    void clear() {
        storage = new Resume[10000];
        storageSize = 0;
    }

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {
       for(int i = 0; i < storageSize; i++) {
           if (uuid.equals(storage[i].uuid)) {
               return storage[i];
           }
       }
        return null;
    }

    void delete(String uuid) {
        if(storage.equals(uuid)) {
            Resume[] storageDst = new Resume[storage.length - 1];
            int remainingElements = storage.length - (indexOf(uuid) + 1);
            System.arraycopy(storage, 0, storageDst, 0, indexOf(uuid));
            System.arraycopy(storage, indexOf(uuid) + 1, storageDst, indexOf(uuid), remainingElements);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(this.storage,0,this.storageSize);
    }

    int size() {
        return storageSize ;
    }

    int indexOf(String uuid){
        for (int i = 0; i < this.storage.length; i++) {
            if (storage.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
