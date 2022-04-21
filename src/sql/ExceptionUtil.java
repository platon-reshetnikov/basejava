package sql;

import exception.ExitStorageException;
import exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil(){

    }
    public static StorageException convertException(SQLException e){
        if (e instanceof PSQLException){
            if (e.getSQLState().equals("23505")){
                return new ExitStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
