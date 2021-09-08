import java.io.IOException;
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

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = null;
                if (!(storageSize - 1 == i)) {
                    storage[i] = storage[storageSize - 1];
                    storage[storageSize - 1] = null;
                }
                --storageSize;
                return;
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
