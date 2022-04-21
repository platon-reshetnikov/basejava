package storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                ArrayStorageTest.class,
                SortedArrayStorage.class,
                ListStorageTest.class,
                MapUuidStorage.class,
                MapResumeStorageTest.class,
                ObjectPathStorageTest.class,
                ObjectFileStorageTest.class,
                XmlPathStorageTest.class,
                JsonPathStorageTest.class,
                DataPathStorageTest.class,
                SqlStorage.class
})

public class AllStorageTest {
}
