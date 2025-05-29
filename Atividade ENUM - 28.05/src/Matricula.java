package sistemaacademico;

import java.time.LocalDate; // Mais moderno que java.util.Date
import java.util.Objects;

public class Matricula {
    private LocalDate dataMatricula;
    private Turma turma; // Agregação: Matricula TEM UMA Turma (1)
    private Status status; // Agregação: Matricula TEM UM Status (1)

    // Atributos da classe de associação (ligação entre Aluno e Disciplina)
    private Aluno aluno;
    private Disciplina disciplina;

    public Matricula(Aluno aluno, Disciplina disciplina, Turma turma, LocalDate dataMatricula) {
        if (aluno == null) throw new IllegalArgumentException("Aluno não pode ser nulo.");
        if (disciplina == null) throw new IllegalArgumentException("Disciplina não pode ser nula.");
        if (turma == null) throw new IllegalArgumentException("Turma não pode ser nula.");
        if (dataMatricula == null) throw new IllegalArgumentException("Data da matrícula não pode ser nula.");

        this.aluno = aluno;
        this.disciplina = disciplina;
        this.turma = turma;
        this.dataMatricula = dataMatricula;
        this.status = Status.MATRICULADO; // Status inicial padrão

        // Estabelecer as associações bidirecionais (ou registrar esta matrícula nas outras classes)
        this.aluno.adicionarMatricula(this);
        this.disciplina.adicionarMatricula(this);
        this.turma.adicionarMatricula(this);
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        if (dataMatricula == null) throw new IllegalArgumentException("Data da matrícula não pode ser nula.");
        this.dataMatricula = dataMatricula;
    }

    public Turma getTurma() {
        return turma;
    }

    // A turma de uma matrícula geralmente não muda após ser definida.
    // Se precisar mudar, crie um método setTurma com lógica para desassociar da turma antiga.
    // public void setTurma(Turma turma) {
    //     if (turma == null) throw new IllegalArgumentException("Turma não pode ser nula.");
    //     if (this.turma != null) {
    //         this.turma.removerMatricula(this);
    //     }
    //     this.turma = turma;
    //     this.turma.adicionarMatricula(this);
    // }


    public Status getStatus() {
        return status;
    }

    // Os métodos abaixo alteram o status. setStatus direto pode não ser desejável.
    // public void setStatus(Status status) {
    //     if (status == null) throw new IllegalArgumentException("Status não pode ser nulo.");
    //     this.status = status;
    // }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    // Métodos de negócio
    public void confirmar() {
        if (this.status == Status.MATRICULADO) {
            this.status = Status.CURSANDO;
            System.out.println("Matrícula de " + aluno.getNome() + " em " + disciplina.getNome() + " confirmada (Cursando).");
        } else {
            System.out.println("Ação não permitida: Matrícula não está no status MATRICULADO.");
        }
    }

    public void trancar() {
        if (this.status == Status.CURSANDO) {
            this.status = Status.TRANCADO;
            System.out.println("Matrícula de " + aluno.getNome() + " em " + disciplina.getNome() + " trancada.");
        } else {
            System.out.println("Ação não permitida: Matrícula não está no status CURSANDO para ser trancada.");
        }
    }

    public void aprovar() {
        if (this.status == Status.CURSANDO) {
            this.status = Status.CONCLUIDO; // Ou APROVADO, se tivesse um status assim
            System.out.println("Matrícula de " + aluno.getNome() + " em " + disciplina.getNome() + " aprovada (Concluído).");
        } else {
            System.out.println("Ação não permitida: Matrícula não está no status CURSANDO para ser aprovada.");
        }
    }

    public void reprovar() {
        if (this.status == Status.CURSANDO) {
            this.status = Status.REPROVADO;
            System.out.println("Matrícula de " + aluno.getNome() + " em " + disciplina.getNome() + " reprovada.");
        } else {
            System.out.println("Ação não permitida: Matrícula não está no status CURSANDO para ser reprovada.");
        }
    }

    @Override
    public String toString() {
        return "Matricula [aluno=" + aluno.getNome() +
                ", disciplina=" + disciplina.getNome() +
                ", turma=" + turma.getNome() +
                ", dataMatricula=" + dataMatricula +
                ", status=" + status.getDescricao() + "]";
    }

    // Uma matrícula é única pela combinação de aluno e disciplina (em um determinado período/turma)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(aluno, matricula.aluno) &&
                Objects.equals(disciplina, matricula.disciplina) &&
                Objects.equals(turma, matricula.turma); // Pode incluir data se matrículas podem se repetir em datas diferentes
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, disciplina, turma);
    }
}