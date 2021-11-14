package exception;

public class ExitStorageException extends StorageException{
    public ExitStorageException(String uuid) {
        super("Resume" + uuid + "already exist",uuid);
    }
}
