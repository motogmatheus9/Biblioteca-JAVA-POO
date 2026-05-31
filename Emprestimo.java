package biblioteca.excecoes;

/**
 * Exceção para usuário com empréstimo em atraso.
 */
public class UsuarioInadimplenteException extends Exception {
    public UsuarioInadimplenteException(String mensagem) {
        super(mensagem);
    }
}