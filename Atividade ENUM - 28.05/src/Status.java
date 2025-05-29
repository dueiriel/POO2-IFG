package sistemaacademico;

/**
 * Enumeração para representar o status de uma matrícula.
 */
public enum Status {
    MATRICULADO("Matriculado"),
    CURSANDO("Cursando"),
    CONCLUIDO("Concluído"),
    TRANCADO("Trancado"),
    REPROVADO("Reprovado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}