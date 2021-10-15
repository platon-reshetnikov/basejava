package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected void insertElement(Resume r, int index) {

    }

    @Override
    protected void fillDeletedElement(int index) {

    }

    @Override
    protected int indexOf(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0,size,searchKey);
    }


}
