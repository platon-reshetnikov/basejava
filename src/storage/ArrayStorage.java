package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    public void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    public void save(Resume r) {
        if (indexOf(r.uuid) != -1) {
            System.out.println("ERROR");
        } else if (storageSize == storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[storageSize] = r;
            storageSize++;
        }

    }

    public void update(Resume r) {
        int index = indexOf(r.uuid);
        if (index == -1) {
            System.out.println("model.Resume" + r.uuid + "not exist");
        } else {
            storage[index] = r;
        }
    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("model.Resume" + uuid + " not exist ");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("model.Resume" + uuid + " not exist ");
        } else {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] result = new Resume[storageSize];
        for (int i = 0; i < storageSize; i++) {
            result[i] = storage[i];
        }
        return result;
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
