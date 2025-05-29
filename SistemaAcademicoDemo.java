package sistemaacademico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaAcademicoDemo {

    // Collections to store system objects
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();
    private static List<Matricula> matriculas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        carregarDadosIniciais(); // Populates the system with example data

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    realizarMatricula();
                    break;
                case 3:
                    menuGerenciarMatriculas();
                    break;
                case 4:
                    menuListagens();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            if (opcao != 0) { // Do not pause if exiting
                pressioneEnterParaContinuar();
            }
        } while (opcao != 0);

        scanner.close();
    }

    // Populates the system with initial data for demonstration
    private static void carregarDadosIniciais() {
        System.out.println("Carregando dados iniciais para demonstração...");
        // Students
        Aluno a1 = new Aluno("111.222.333-44", "João Silva");
        Aluno a2 = new Aluno("555.666.777-88", "Maria Oliveira");
        Aluno a3 = new Aluno("999.888.777-66", "Carlos Pereira");
        alunos.add(a1);
        alunos.add(a2);
        alunos.add(a3);

        // Disciplines
        Disciplina d1 = new Disciplina("Algoritmos e Programação", 80);
        Disciplina d2 = new Disciplina("Estrutura de Dados", 80);
        Disciplina d3 = new Disciplina("Banco de Dados", 60);
        disciplinas.add(d1);
        disciplinas.add(d2);
        disciplinas.add(d3);

        // Turmas (Classes/Sections) - CORRECTED Instantiation
        Turma t1 = new Turma("T2023A-ALG"); // Uses Turma(String nome) constructor
        Turma t2 = new Turma(20230202, "T2023B-ESD"); // Uses Turma(int id, String nome) constructor

        turmas.add(t1);
        turmas.add(t2);

        // Example Enrollments
        try {
            Matricula m1 = new Matricula(a1, d1, t1, LocalDate.of(2023, 8, 1));
            m1.confirmar(); // João in Algoritmos -> Cursando
            matriculas.add(m1);

            Matricula m2 = new Matricula(a2, d1, t1, LocalDate.of(2023, 8, 2));
            matriculas.add(m2); // Maria in Algoritmos -> Matriculado

            Matricula m3 = new Matricula(a1, d2, t2, LocalDate.of(2023, 8, 3));
            m3.confirmar();
            m3.aprovar(); // João in Estrutura de Dados -> Concluído
            matriculas.add(m3);

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao carregar matrículas iniciais: " + e.getMessage());
        }
        System.out.println("Dados iniciais carregados.\n");
    }

    // Displays the main system menu
    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema Acadêmico ---");
        System.out.println("1. Cadastros");
        System.out.println("2. Realizar Matrícula");
        System.out.println("3. Gerenciar Matrículas");
        System.out.println("4. Listagens");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Reads user option, handling non-numeric input
    private static int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            return opcao;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear scanner buffer
            return -1; // Return invalid value to indicate error
        }
    }

    // Pauses execution until user presses Enter
    private static void pressioneEnterParaContinuar() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    // Submenu for registration operations
    private static void menuCadastros() {
        int opcao;
        do {
            System.out.println("\n--- Menu Cadastros ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Disciplina");
            System.out.println("3. Cadastrar Turma");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1: cadastrarAluno(); break;
                case 2: cadastrarDisciplina(); break;
                case 3: cadastrarTurma(); break;
                case 0: break; // Return to main menu
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Logic to register a new student
    private static void cadastrarAluno() {
        System.out.println("\n--- Cadastrar Novo Aluno ---");
        System.out.print("CPF (xxx.xxx.xxx-xx): ");
        String cpf = scanner.nextLine();
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        // Basic CPF validation to avoid duplicates
        if (buscarAlunoPorCpf(cpf) != null) {
            System.out.println("Erro: Já existe um aluno cadastrado com este CPF.");
            return;
        }

        try {
            Aluno novoAluno = new Aluno(cpf, nome);
            alunos.add(novoAluno);
            System.out.println("Aluno '" + nome + "' cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    // Logic to register a new discipline
    private static void cadastrarDisciplina() {
        System.out.println("\n--- Cadastrar Nova Disciplina ---");
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Carga Horária (horas): ");
        int cargaHoraria = -1;
        try {
            cargaHoraria = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer
            System.out.println("Carga horária inválida. Deve ser um número.");
            return;
        }

        // Basic name validation to avoid duplicates
        if (buscarDisciplinaPorNome(nome) != null) {
            System.out.println("Erro: Já existe uma disciplina cadastrada com este nome.");
            return;
        }

        try {
            Disciplina novaDisciplina = new Disciplina(nome, cargaHoraria);
            disciplinas.add(novaDisciplina);
            System.out.println("Disciplina '" + nome + "' cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    // Logic to register a new turma (class/section)
    private static void cadastrarTurma() {
        System.out.println("\n--- Cadastrar Nova Turma ---");
        System.out.print("Nome/Código da turma (Ex: T2024A-MAT): ");
        String nome = scanner.nextLine();

        // Basic name validation to avoid duplicates
        if (buscarTurmaPorNome(nome) != null) {
            System.out.println("Erro: Já existe uma turma cadastrada com este nome/código.");
            return;
        }

        System.out.print("Deseja informar um ID numérico para a turma? (S/N) (Se não, será gerado automaticamente): ");
        String respId = scanner.nextLine();

        Turma novaTurma;
        try {
            if (respId.equalsIgnoreCase("S")) {
                System.out.print("ID da turma (numérico): ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (buscarTurmaPorId(id) != null) {
                    System.out.println("Erro: Já existe uma turma cadastrada com este ID.");
                    return;
                }
                novaTurma = new Turma(id, nome);
            } else {
                novaTurma = new Turma(nome); // Uses auto-generated ID
            }
            turmas.add(novaTurma);
            System.out.println("Turma '" + novaTurma.getNome() + "' (ID: "+ novaTurma.getId() +") cadastrada com sucesso!");
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer if ID input was not a number
            System.out.println("Erro: ID inválido. Deve ser um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar turma: " + e.getMessage());
        }
    }


    // Logic to perform a new enrollment
    private static void realizarMatricula() {
        System.out.println("\n--- Realizar Nova Matrícula ---");
        if (alunos.isEmpty() || disciplinas.isEmpty() || turmas.isEmpty()) {
            System.out.println("É necessário ter ao menos um aluno, uma disciplina e uma turma cadastrados.");
            return;
        }

        Aluno aluno = selecionarAluno("Selecione o Aluno para a matrícula:");
        if (aluno == null) return;

        Disciplina disciplina = selecionarDisciplina("Selecione a Disciplina para a matrícula:");
        if (disciplina == null) return;

        Turma turma = selecionarTurma("Selecione a Turma para a matrícula:");
        if (turma == null) return;

        // Check if an enrollment already exists for this student in this discipline and turma
        for (Matricula m : matriculas) {
            if (m.getAluno().equals(aluno) && m.getDisciplina().equals(disciplina) && m.getTurma().equals(turma)) {
                System.out.println("Erro: Este aluno já está matriculado nesta disciplina e turma.");
                return;
            }
        }

        LocalDate dataMatricula;
        while (true) {
            System.out.print("Data da Matrícula (dd/MM/yyyy) ou deixe em branco para hoje: ");
            String dataStr = scanner.nextLine();
            if (dataStr.trim().isEmpty()) {
                dataMatricula = LocalDate.now();
                break;
            }
            try {
                dataMatricula = LocalDate.parse(dataStr, dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
            }
        }


        try {
            Matricula novaMatricula = new Matricula(aluno, disciplina, turma, dataMatricula);
            matriculas.add(novaMatricula); // Add to the global list of matriculas
            System.out.println("Matrícula realizada com sucesso! Status: " + novaMatricula.getStatus());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao realizar matrícula: " + e.getMessage());
        }
    }

    // Submenu for managing existing enrollments
    private static void menuGerenciarMatriculas() {
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula cadastrada para gerenciar.");
            return;
        }

        Matricula matricula = selecionarMatricula("Selecione a Matrícula para gerenciar:");
        if (matricula == null) return;

        int opcao;
        do {
            System.out.println("\n--- Gerenciar Matrícula ---");
            System.out.println("Aluno: " + matricula.getAluno().getNome() +
                    " | Disciplina: " + matricula.getDisciplina().getNome() +
                    " | Turma: " + matricula.getTurma().getNome() +
                    " | Status: " + matricula.getStatus());
            System.out.println("1. Confirmar Matrícula (Mudar para Cursando)");
            System.out.println("2. Trancar Matrícula");
            System.out.println("3. Aprovar Aluno");
            System.out.println("4. Reprovar Aluno");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma ação: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1: matricula.confirmar(); break;
                case 2: matricula.trancar(); break;
                case 3: matricula.aprovar(); break;
                case 4: matricula.reprovar(); break;
                case 0: break; // Return
                default: System.out.println("Opção inválida.");
            }
            if (opcao >= 1 && opcao <= 4) { // If an action was taken, show the new status
                System.out.println("Novo status da matrícula: " + matricula.getStatus());
            }
        } while (opcao != 0);
    }

    // Submenu for various listings
    private static void menuListagens() {
        int opcao;
        do {
            System.out.println("\n--- Menu Listagens ---");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Listar Disciplinas");
            System.out.println("3. Listar Turmas");
            System.out.println("4. Listar Todas as Matrículas");
            System.out.println("5. Listar Matrículas por Aluno");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1: listarAlunos(); break;
                case 2: listarDisciplinas(); break;
                case 3: listarTurmas(); break;
                case 4: listarTodasMatriculas(); break;
                case 5: listarMatriculasPorAluno(); break;
                case 0: break; // Return to main menu
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // Lists all registered students
    private static void listarAlunos() {
        System.out.println("\n--- Lista de Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            System.out.println((i + 1) + ". CPF: " + a.getCpf() + " | Nome: " + a.getNome());
        }
    }

    // Lists all registered disciplines
    private static void listarDisciplinas() {
        System.out.println("\n--- Lista de Disciplinas ---");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.println((i + 1) + ". Nome: " + d.getNome() + " | Carga Horária: " + d.getCargaHoraria() + "h");
        }
    }

    // Lists all registered turmas (classes/sections)
    private static void listarTurmas() {
        System.out.println("\n--- Lista de Turmas ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        for (int i = 0; i < turmas.size(); i++) {
            Turma t = turmas.get(i);
            System.out.println((i + 1) + ". ID: " + t.getId() + " | Nome/Código: " + t.getNome());
        }
    }

    // Lists all performed enrollments
    private static void listarTodasMatriculas() {
        System.out.println("\n--- Lista de Todas as Matrículas ---");
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula realizada.");
            return;
        }
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula m = matriculas.get(i);
            System.out.println((i + 1) + ". " + m.toString()); // Matricula.toString() provides good summary
        }
    }

    // Lists enrollments for a specific student
    private static void listarMatriculasPorAluno() {
        System.out.println("\n--- Listar Matrículas por Aluno ---");
        Aluno aluno = selecionarAluno("Selecione o Aluno:");
        if (aluno == null) return;

        List<Matricula> matriculasDoAluno = aluno.getMatriculas(); // Uses Aluno's method
        if (matriculasDoAluno.isEmpty()) {
            System.out.println("O aluno " + aluno.getNome() + " não possui matrículas.");
            return;
        }
        System.out.println("Matrículas do aluno: " + aluno.getNome());
        for (int i = 0; i < matriculasDoAluno.size(); i++) {
            Matricula m = matriculasDoAluno.get(i);
            System.out.println((i + 1) + ". Disciplina: " + m.getDisciplina().getNome() +
                    " | Turma: " + m.getTurma().getNome() +
                    " | Status: " + m.getStatus().getDescricao());
        }
    }

    // Helper methods for entity selection
    private static Aluno selecionarAluno(String mensagem) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return null;
        }
        System.out.println(mensagem);
        listarAlunos();
        System.out.print("Digite o número do aluno (ou 0 para cancelar): ");
        int escolha = lerOpcao();
        if (escolha > 0 && escolha <= alunos.size()) {
            return alunos.get(escolha - 1);
        }
        if(escolha != 0) System.out.println("Seleção inválida.");
        return null;
    }

    private static Disciplina selecionarDisciplina(String mensagem) {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return null;
        }
        System.out.println(mensagem);
        listarDisciplinas();
        System.out.print("Digite o número da disciplina (ou 0 para cancelar): ");
        int escolha = lerOpcao();
        if (escolha > 0 && escolha <= disciplinas.size()) {
            return disciplinas.get(escolha - 1);
        }
        if(escolha != 0) System.out.println("Seleção inválida.");
        return null;
    }

    private static Turma selecionarTurma(String mensagem) {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return null;
        }
        System.out.println(mensagem);
        listarTurmas();
        System.out.print("Digite o número da turma (ou 0 para cancelar): ");
        int escolha = lerOpcao();
        if (escolha > 0 && escolha <= turmas.size()) {
            return turmas.get(escolha - 1);
        }
        if(escolha != 0) System.out.println("Seleção inválida.");
        return null;
    }

    private static Matricula selecionarMatricula(String mensagem) {
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula cadastrada.");
            return null;
        }
        System.out.println(mensagem);
        listarTodasMatriculas();
        System.out.print("Digite o número da matrícula (ou 0 para cancelar): ");
        int escolha = lerOpcao();
        if (escolha > 0 && escolha <= matriculas.size()) {
            return matriculas.get(escolha - 1);
        }
        if(escolha != 0) System.out.println("Seleção inválida.");
        return null;
    }

    // Helper methods for finding entities (to avoid duplicates)
    private static Aluno buscarAlunoPorCpf(String cpf) {
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                return a;
            }
        }
        return null;
    }

    private static Disciplina buscarDisciplinaPorNome(String nome) {
        for (Disciplina d : disciplinas) {
            if (d.getNome().equalsIgnoreCase(nome)) {
                return d;
            }
        }
        return null;
    }

    private static Turma buscarTurmaPorNome(String nome) {
        for (Turma t : turmas) {
            if (t.getNome().equalsIgnoreCase(nome)) {
                return t;
            }
        }
        return null;
    }

    private static Turma buscarTurmaPorId(int id) {
        for (Turma t : turmas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}