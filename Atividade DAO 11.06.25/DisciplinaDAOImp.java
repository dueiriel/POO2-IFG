import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {

    private Connection conn;

    public DisciplinaDAOImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
        PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, disciplina.getNomeDisciplina());
        st.setInt(2, disciplina.getCargaHoraria());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            disciplina.setIdDisciplina(rs.getInt(1));
        }
        rs.close();
        st.close();
    }

    @Override
    public void update(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, disciplina.getNomeDisciplina());
        st.setInt(2, disciplina.getCargaHoraria());
        st.setInt(3, disciplina.getIdDisciplina());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        st.executeUpdate();
        st.close();
    }

    @Override
    public Disciplina findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM disciplina WHERE iddisciplina = ?";
        Disciplina disciplina = null;
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            disciplina = new Disciplina();
            disciplina.setIdDisciplina(rs.getInt("iddisciplina"));
            disciplina.setNomeDisciplina(rs.getString("nomedisciplina"));
            disciplina.setCargaHoraria(rs.getInt("cargahoraria"));
        }
        rs.close();
        st.close();
        return disciplina;
    }

    @Override
    public List<Disciplina> findAll() throws SQLException {
        String sql = "SELECT * FROM disciplina ORDER BY nomedisciplina";
        List<Disciplina> lista = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Disciplina disciplina = new Disciplina();
            disciplina.setIdDisciplina(rs.getInt("iddisciplina"));
            disciplina.setNomeDisciplina(rs.getString("nomedisciplina"));
            disciplina.setCargaHoraria(rs.getInt("cargahoraria"));
            lista.add(disciplina);
        }
        rs.close();
        st.close();
        return lista;
    }
}
