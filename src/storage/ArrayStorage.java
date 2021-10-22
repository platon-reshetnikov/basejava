package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertElement(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[index - 1];
    }


    public int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
