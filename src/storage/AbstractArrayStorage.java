package storage;

import exception.ExitStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 1000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size(){
        return size;
    }

    public void clear(){
        Arrays.fill(storage,0,size,null);
        size = 0;
    }

    public void update(Resume r) {
        int index = indexOf(r.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll(){
        return Arrays.copyOfRange(storage,0,size);
    }

    public void save(Resume r) {
        int index = indexOf(r.getUuid());
        if (index >= 0) {
            throw new ExitStorageException(r.getUuid());
        } else if (size == STORAGE_LIMIT) {
           throw new StorageException("Storage overflow",r.getUuid());
        } else {
            insertElement(r,index);
            size++;
        }

    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }



    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract void insertElement(Resume r,int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int indexOf(String uuid);



}
