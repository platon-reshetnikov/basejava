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
        int index = indexOf(r.uuid);
        if (index != -1){
            System.out.println("ERROR");
        }else if (storageSize == storage.length){
            System.out.println("Storage overflow");
        }else {
            storage[storageSize - 1] = r;
            storageSize++;
        }

        }
        public void update(Resume resume) {

    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1){
            System.out.println("Resume" + uuid + " not exist ");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1){
            System.out.println("Resume" + uuid + " not exist ");
        }else {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
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

    private int indexOf(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
