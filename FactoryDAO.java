import java.sql.SQLException;

public class FactoryDAO {

    public static DisciplinaDAO createDisciplinaDAO() throws SQLException {
        return new DisciplinaDAOImp(DB.getConnection());
    }
}
