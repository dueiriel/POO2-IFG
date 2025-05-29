package sistemaacademico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Turma {
    private int id; // Pode ser um código String também, ex: "TURMA2023A"
    private String nome; // Ex: "Engenharia de Software - Noite"
    // Uma turma pode ter várias matrículas (agregação 0..n com Matricula)
    private List<Matricula> matriculas;

    private static int proximoId = 1; // Para gerar IDs automaticamente

    public Turma(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma não pode ser nulo ou vazio.");
        }
        this.id = proximoId++;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
    }

    // Construtor alternativo se o ID for gerenciado externamente
    public Turma(int id, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma não pode ser nulo ou vazio.");
        }
        this.id = id;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
        if (id >= proximoId) { // Atualiza o contador se ID manual for maior
            proximoId = id + 1;
        }
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da turma não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public List<Matricula> getMatriculas() {
        return new ArrayList<>(matriculas);
    }

    // Método package-private para ser chamado pela classe Matricula
    void adicionarMatricula(Matricula matricula) {
        if (matricula != null && !this.matriculas.contains(matricula)) {
            this.matriculas.add(matricula);
            // Se a Matricula não definir a turma em seu construtor, faríamos aqui:
            // if (matricula.getTurma() != this) {
            //     matricula.setTurma(this);
            // }
        }
    }

    void removerMatricula(Matricula matricula) {
        this.matriculas.remove(matricula);
    }

    @Override
    public String toString() {
        return "Turma [id=" + id + ", nome=" + nome + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return id == turma.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}