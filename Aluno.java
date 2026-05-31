package biblioteca.excecoes;

/**
 * Exceção para tentativa de empréstimo de livro sem estoque.
 */
public class LivroIndisponivelException extends Exception {
    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}