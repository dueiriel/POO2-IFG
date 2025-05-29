package sistemaacademico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    // Uma disciplina pode estar em várias matrículas (1..* na associação com Matricula)
    private List<Matricula> matriculas;

    public Disciplina(String nome, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        }
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.matriculas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public List<Matricula> getMatriculas() {
        return new ArrayList<>(matriculas);
    }

    // Método package-private para ser chamado pela classe Matricula
    void adicionarMatricula(Matricula matricula) {
        if (matricula != null && !this.matriculas.contains(matricula)) {
            this.matriculas.add(matricula);
        }
    }

    void removerMatricula(Matricula matricula) {
        this.matriculas.remove(matricula);
    }

    @Override
    public String toString() {
        return "Disciplina [nome=" + nome + ", cargaHoraria=" + cargaHoraria + "h]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(nome, that.nome); // Supondo que nome é único para disciplina
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}