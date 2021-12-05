package storage;

import static org.junit.jupiter.api.Assertions.*;

public class ListStorageTest extends AbstractArrayStorageTest{

    public ListStorageTest(){
        super(new ListStorage());
    }
}