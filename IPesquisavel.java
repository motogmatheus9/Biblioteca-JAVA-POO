package biblioteca.excecoes;

/**
 * Exceção para usuário com limite de empréstimos atingido.
 */
public class LimiteEmprestimosExcedidoException extends Exception {
    public LimiteEmprestimosExcedidoException(String mensagem) {
        super(mensagem);
    }
}