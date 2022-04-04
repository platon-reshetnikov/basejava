package storage;

import com.sun.xml.internal.txw2.output.XmlSerializer;
import storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest{
    public XmlPathStorageTest(Storage storage) {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(),new XmlStreamSerializer()));
    }
}
