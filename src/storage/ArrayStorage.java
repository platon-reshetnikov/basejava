package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize = 0;

    public void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    public void update(Resume r) {
        int index = indexOf(r.uuid);
        if (index == -1) {
            System.out.println("Resume" + r.uuid + "not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("Resume" + uuid + " not exist ");
            return null;
        }
        return storage[index];
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage[storageSize] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[storageSize - 1];
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

    public int size() {
        return storageSize;
    }

    public int indexOf(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
