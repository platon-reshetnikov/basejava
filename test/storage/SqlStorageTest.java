package storage;

public class SqlStorageTest extends AbstractStorageTest{
    public SqlStorageTest() {
        super((Storage) Config.get().getStorageDir());
    }
}
