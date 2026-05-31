package biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import biblioteca.excecoes.LimiteEmprestimosExcedidoException;
import biblioteca.excecoes.LivroIndisponivelException;
import biblioteca.excecoes.UsuarioInadimplenteException;
import biblioteca.modelo.Aluno;
import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Professor;
import biblioteca.modelo.Usuario;
import biblioteca.padroes.Biblioteca;
import biblioteca.padroes.TipoUsuario;
import biblioteca.padroes.UsuarioFactory;

/**
 * Classe principal com menu interativo do sistema.
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Biblioteca BIBLIOTECA = Biblioteca.getInstancia();

    /**
     * Inicia aplicação.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        preloadDados();
        executarMenu();
    }

    private static void preloadDados() {
        BIBLIOTECA.cadastrarLivro(new Livro("Clean Code", "Robert C. Martin", "ISBN-001", 2008, 2));
        BIBLIOTECA.cadastrarLivro(new Livro("Effective Java", "Joshua Bloch", "ISBN-002", 2018, 1));
        BIBLIOTECA.cadastrarLivro(new Livro("Design Patterns", "GoF", "ISBN-003", 1994, 2));
        BIBLIOTECA.cadastrarLivro(new Livro("Refactoring", "Martin Fowler", "ISBN-004", 2019, 1));

        Usuario aluno = UsuarioFactory.criar(TipoUsuario.ALUNO, "Ana Silva", "A100", "ana@uni.edu", "Computacao");
        Usuario professor = UsuarioFactory.criar(TipoUsuario.PROFESSOR, "Carlos Lima", "P200", "carlos@uni.edu", "Engenharia");
        BIBLIOTECA.cadastrarUsuario(aluno);
        BIBLIOTECA.cadastrarUsuario(professor);
    }

    private static void executarMenu() {
        int opcao;
        do {
            imprimirMenu();
            opcao = lerInt("Escolha uma opção: ");
            try {
                processarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);
        SCANNER.close();
        System.out.println("Sistema encerrado.");
    }

    private static void imprimirMenu() {
        System.out.println("\n===== MENU BIBLIOTECA =====");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Atualizar livro");
        System.out.println("3. Remover livro");
        System.out.println("4. Cadastrar usuário");
        System.out.println("5. Atualizar usuário");
        System.out.println("6. Listar usuários");
        System.out.println("7. Realizar empréstimo");
        System.out.println("8. Devolução");
        System.out.println("9. Empréstimos ativos");
        System.out.println("10. Histórico");
        System.out.println("11. Buscar título");
        System.out.println("12. Buscar autor");
        System.out.println("13. Buscar ISBN");
        System.out.println("14. Situação do usuário");
        System.out.println("15. Disponibilidade");
        System.out.println("0. Sair");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrarLivro();
            case 2 -> atualizarLivro();
            case 3 -> removerLivro();
            case 4 -> cadastrarUsuario();
            case 5 -> atualizarUsuario();
            case 6 -> listarUsuarios();
            case 7 -> realizarEmprestimo();
            case 8 -> realizarDevolucao();
            case 9 -> listarEmprestimosAtivos();
            case 10 -> listarHistorico();
            case 11 -> buscarTitulo();
            case 12 -> buscarAutor();
            case 13 -> buscarIsbn();
            case 14 -> situacaoUsuario();
            case 15 -> disponibilidadeLivro();
            case 0 -> {
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarLivro() {
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        String isbn = lerTexto("ISBN: ");
        int ano = lerInt("Ano publicação: ");
        int qtd = lerInt("Quantidade disponível: ");
        BIBLIOTECA.cadastrarLivro(new Livro(titulo, autor, isbn, ano, qtd));
        System.out.println("Livro cadastrado com sucesso.");
    }

    private static void atualizarLivro() {
        String isbnRef = lerTexto("ISBN do livro a atualizar: ");
        String titulo = lerTexto("Novo título: ");
        String autor = lerTexto("Novo autor: ");
        String isbnNovo = lerTexto("Novo ISBN: ");
        int ano = lerInt("Novo ano: ");
        int qtd = lerInt("Nova quantidade: ");
        boolean ok = BIBLIOTECA.atualizarLivro(isbnRef, new Livro(titulo, autor, isbnNovo, ano, qtd));
        System.out.println(ok ? "Livro atualizado." : "Livro não encontrado.");
    }

    private static void removerLivro() {
        String isbn = lerTexto("ISBN para remover: ");
        boolean ok = BIBLIOTECA.removerLivro(isbn);
        if (ok) {
            System.out.println("Livro removido.");
        } else {
            System.out.println("Não foi possível remover (inexistente ou emprestado).");
        }
    }

    private static void cadastrarUsuario() {
        int tipoInt = lerInt("Tipo (1-Aluno, 2-Professor): ");
        TipoUsuario tipo = (tipoInt == 1) ? TipoUsuario.ALUNO : TipoUsuario.PROFESSOR;
        String nome = lerTexto("Nome: ");
        String matricula = lerTexto("Matrícula: ");
        String email = lerTexto("E-mail: ");
        String extra = lerTexto(tipo == TipoUsuario.ALUNO ? "Curso: " : "Departamento: ");
        Usuario usuario = UsuarioFactory.criar(tipo, nome, matricula, email, extra);
        BIBLIOTECA.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    private static void atualizarUsuario() {
        String matricula = lerTexto("Matrícula do usuário: ");
        String nome = lerTexto("Novo nome: ");
        String email = lerTexto("Novo e-mail: ");
        Usuario usuario = BIBLIOTECA.buscarUsuarioPorMatricula(matricula);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        String extra = lerTexto(usuario instanceof Aluno ? "Novo curso: " : "Novo departamento: ");
        boolean ok = BIBLIOTECA.atualizarUsuario(matricula, nome, email, extra);
        System.out.println(ok ? "Usuário atualizado." : "Falha na atualização.");
    }

    private static void listarUsuarios() {
        if (BIBLIOTECA.getUsuarios().isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario usuario : BIBLIOTECA.getUsuarios()) {
            System.out.println(usuario);
        }
    }

    private static void realizarEmprestimo() {
        String isbn = lerTexto("ISBN do livro: ");
        String matricula = lerTexto("Matrícula do usuário: ");
        try {
            Emprestimo emprestimo = BIBLIOTECA.realizarEmprestimo(isbn, matricula);
            if (emprestimo == null) {
                System.out.println("Livro ou usuário não encontrado.");
                return;
            }
            System.out.println("Empréstimo realizado: " + emprestimo);
        } catch (LivroIndisponivelException | UsuarioInadimplenteException | LimiteEmprestimosExcedidoException e) {
            System.out.println("Falha no empréstimo: " + e.getMessage());
        }
    }

    private static void realizarDevolucao() {
        String isbn = lerTexto("ISBN do livro: ");
        String matricula = lerTexto("Matrícula do usuário: ");
        String data = lerTexto("Data devolução (AAAA-MM-DD) ou vazio para hoje: ");
        LocalDate devolucao = LocalDate.now();
        if (!data.isBlank()) {
            try {
                devolucao = LocalDate.parse(data);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Usando data atual.");
            }
        }
        try {
            double multa = BIBLIOTECA.realizarDevolucao(isbn, matricula, devolucao);
            if (multa < 0) {
                System.out.println("Empréstimo ativo não encontrado para devolução.");
            } else {
                System.out.printf("Devolução registrada. Multa: R$ %.2f%n", multa);
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar devolução: " + e.getMessage());
        }
    }

    private static void listarEmprestimosAtivos() {
        List<Emprestimo> ativos = BIBLIOTECA.listarEmprestimosAtivos();
        if (ativos.isEmpty()) {
            System.out.println("Sem empréstimos ativos.");
            return;
        }
        for (Emprestimo e : ativos) {
            System.out.println(e);
        }
    }

    private static void listarHistorico() {
        if (BIBLIOTECA.getHistoricoEmprestimos().isEmpty()) {
            System.out.println("Sem histórico de empréstimos.");
            return;
        }
        for (Emprestimo e : BIBLIOTECA.getHistoricoEmprestimos()) {
            System.out.println(e);
        }
    }

    private static void buscarTitulo() {
        String termo = lerTexto("Título para busca: ");
        List<Livro> encontrados = BIBLIOTECA.buscarPorTitulo(termo);
        imprimirLivros(encontrados);
    }

    private static void buscarAutor() {
        String termo = lerTexto("Autor para busca: ");
        List<Livro> encontrados = BIBLIOTECA.buscarPorAutor(termo);
        imprimirLivros(encontrados);
    }

    private static void buscarIsbn() {
        String isbn = lerTexto("ISBN: ");
        Livro livro = BIBLIOTECA.buscarPorIsbn(isbn);
        System.out.println(livro == null ? "Livro não encontrado." : livro);
    }

    private static void situacaoUsuario() {
        String matricula = lerTexto("Matrícula: ");
        Usuario usuario = BIBLIOTECA.buscarUsuarioPorMatricula(matricula);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        System.out.println(usuario);
        System.out.println("Empréstimos ativos: " + usuario.getEmprestimosAtivos().size());
        System.out.println("Inadimplente: " + (usuario.possuiAtraso() ? "Sim" : "Não"));
    }

    private static void disponibilidadeLivro() {
        String isbn = lerTexto("ISBN: ");
        Livro livro = BIBLIOTECA.buscarPorIsbn(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.printf("Disponibilidade de '%s': %d%n", livro.getTitulo(), livro.getQuantidadeDisponivel());
    }

    private static void imprimirLivros(List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine().trim();
    }

    private static int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = SCANNER.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
}