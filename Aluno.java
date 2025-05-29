package sistemaacademico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno {
    private String cpf;
    private String nome;
    // Um aluno pode ter várias matrículas (0..n na associação com Matricula)
    private List<Matricula> matriculas;

    public Aluno(String cpf, String nome) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.matriculas = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    // CPF geralmente é imutável após a criação, então sem setCpf público.
    // Se precisar mudar, crie um método específico com validação.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public List<Matricula> getMatriculas() {
        // Retorna uma cópia para proteger a lista interna de modificações externas diretas
        return new ArrayList<>(matriculas);
    }

    // Método package-private para ser chamado pela classe Matricula ao se associar
    void adicionarMatricula(Matricula matricula) {
        if (matricula != null && !this.matriculas.contains(matricula)) {
            this.matriculas.add(matricula);
        }
    }

    // Método para remover matrícula, se necessário (geralmente gerenciado pela Matrícula)
    void removerMatricula(Matricula matricula) {
        this.matriculas.remove(matricula);
    }

    @Override
    public String toString() {
        return "Aluno [cpf=" + cpf + ", nome=" + nome + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(cpf, aluno.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}