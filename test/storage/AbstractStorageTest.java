package storage;

import exception.NotExistStorageException;
import exception.StorageException;
import exception.ExitStorageException;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\projects\\storage");

    protected Storage storage;


    private static final String UUID_1 = "uuid1";
    private static final Resume Resume_1;

    private static final String UUID_2 = "uuid2";
    private static final Resume Resume_2;

    private static final String UUID_3 = "uuid3";
    private static final Resume Resume_3;

    private static final String UUID_4 = "uuid4";
    private static final Resume Resume_4;

    static {
        Resume_1 = new Resume(UUID_1,"Name1" );
        Resume_2 = new Resume(UUID_2,"Name2" );
        Resume_3 = new Resume(UUID_3,"Name3" );
        Resume_4 = new Resume(UUID_4,"Name4" );

        Resume_1.addContact(ContactType.MAIL, "mail1@ya.ru");
        Resume_1.addContact(ContactType.PHONE, "11111");
        Resume_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        Resume_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        Resume_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        Resume_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        Resume_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        Resume_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        Resume_2.addContact(ContactType.SKYPE, "skype2");
        Resume_2.addContact(ContactType.PHONE, "22222");
        Resume_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp()throws Exception{
        storage.clear();
        storage.save(Resume_1);
        storage.save(Resume_2);
        storage.save(Resume_3);
    }

    @Test
    public void size()throws Exception {
        assertSize(3);
    }

    @Test
    public void clear()throws Exception {
        storage.clear();
        assertSize(3);
    }

    @Test
    public void update()throws Exception {
        Resume newResume = new Resume(UUID_1,"New Name" );
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = ExitStorageException.class)
    public void updateNotExist()throws Exception{
        storage.get("dummy");
    }

    @Test
    public void getAllSorted()throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3,list.size());
        assertEquals(list, Arrays.asList(Resume_1,Resume_2,Resume_3));
    }

    @Test
    public void save()throws Exception {
        storage.save(Resume_4);
        assertSize(4);
        assertGet(Resume_4);
    }

    @Test(expected = ExitStorageException.class)
    public void saveExist()throws Exception{
        storage.save(Resume_1);
    }

    @Test(expected = StorageException.class)
    public void delete()throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void get()throws Exception {
        assertGet(Resume_1);
        assertGet(Resume_2);
        assertGet(Resume_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist()throws Exception{
        storage.delete("dummy");
    }

    private void assertSize(int size){
        assertEquals(size,storage.size());
    }

    private void assertGet(Resume r){
        assertEquals(r,storage.get(r.getUuid()));
    }
}
