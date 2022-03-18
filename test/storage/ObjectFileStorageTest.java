package storage;

import storage.serializer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest{
    protected ObjectFileStorageTest(Storage storage) {
        super(new FileStorage(STORAGE_DIR,new ObjectStreamSerializer()));
    }
}
