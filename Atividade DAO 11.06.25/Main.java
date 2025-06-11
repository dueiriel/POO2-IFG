import java.sql.SQLException;
import java.util.List;

//Para testar as funções
public class Main {
    
    public static void main(String[] args) throws SQLException {
        
        DisciplinaServico servico = new DisciplinaServico();

        System.out.println("--- Listando disciplinas iniciais ---");
        List<Disciplina> listaInicial = servico.listar();
        for (Disciplina d : listaInicial) {
            System.out.println(d);
        }

        System.out.println("\n--- Cadastrando nova disciplina ---");
        Disciplina nova = new Disciplina();
        nova.setNomeDisciplina("Inteligencia Artificial");
        nova.setCargaHoraria(100);
        servico.cadastrar(nova);
        System.out.println("Cadastrado: " + nova);

        System.out.println("\n--- Localizando disciplina ---");
        Disciplina localizada = servico.localizar(nova.getIdDisciplina());
        System.out.println("Localizada: " + localizada);

        System.out.println("\n--- Alterando disciplina ---");
        localizada.setCargaHoraria(110);
        servico.alterar(localizada);
        System.out.println("Alterado para: " + servico.localizar(localizada.getIdDisciplina()));

        System.out.println("\n--- Apagando disciplina ---");
        servico.apagar(localizada.getIdDisciplina());
        System.out.println("Disciplina " + localizada.getIdDisciplina() + " apagada.");
        
        System.out.println("\n--- Lista Final ---");
        List<Disciplina> listaFinal = servico.listar();
        for (Disciplina d : listaFinal) {
            System.out.println(d);
        }
    }
}
