import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    public void update(Resume resume) {


    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < storageSize; i++){
            if (uuid == storage[i].uuid){
                storage[i] = storage[storageSize - 1];
                storage[i] = storage[storageSize - 1];
                storageSize--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    int size() {
        return storageSize;
    }

    int indexOf(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
