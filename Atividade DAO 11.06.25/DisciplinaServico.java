import java.sql.SQLException;
import java.util.List;

public class DisciplinaServico {

    private DisciplinaDAO dao;
    
    public DisciplinaServico() throws SQLException {
        this.dao = FactoryDAO.createDisciplinaDAO();
    }

    public void cadastrar(Disciplina disciplina) throws SQLException {
        dao.insert(disciplina);
    }

    public void alterar(Disciplina disciplina) throws SQLException {
        dao.update(disciplina);
    }

    public void apagar(Integer id) throws SQLException {
        dao.deleteById(id);
    }

    public Disciplina localizar(Integer id) throws SQLException {
        return dao.findById(id);
    }

    public List<Disciplina> listar() throws SQLException {
        return dao.findAll();
    }
}
