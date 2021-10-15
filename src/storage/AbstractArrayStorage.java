package storage;

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
        int index = indexOf(r.uuid);
        if (index == -1) {
            System.out.println("Resume" + r.uuid + "not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll(){
        return Arrays.copyOfRange(storage,0,size);
    }

    public void save(Resume r) {
        int index = indexOf(r.uuid);
        if (index != -1) {
            System.out.println("ERROR");
        } else if (size == storage.length) {
            System.out.println("Storage overflow");
        } else {
            insertElement(r,index);
            storage[size] = r;
            size++;
        }

    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("Resume" + uuid + " not exist ");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
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

    protected abstract void insertElement(Resume r,int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int indexOf(String uuid);



}
