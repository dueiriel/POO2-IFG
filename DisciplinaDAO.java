import java.sql.SQLException;
import java.util.List;

public interface DisciplinaDAO {

    void insert(Disciplina disciplina) throws SQLException;

    void update(Disciplina disciplina) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    Disciplina findById(Integer id) throws SQLException;

    List<Disciplina> findAll() throws SQLException;
}
