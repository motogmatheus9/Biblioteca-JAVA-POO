package biblioteca.modelo;

/**
 * Entidade de livro da biblioteca.
 */
public class Livro extends RecursoBiblioteca {
    private String isbn;
    private int quantidadeDisponivel;

    public Livro() {
        this("", "", "", 0, 0);
    }

    public Livro(String titulo, String autor, String isbn) {
        this(titulo, autor, isbn, 0, 1);
    }

    public Livro(String titulo, String autor, String isbn, int anoPublicacao, int quantidadeDisponivel) {
        super(titulo, autor, anoPublicacao);
        this.isbn = isbn;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | ISBN: %s | Ano: %d | Disponíveis: %d",
                getTitulo(), getAutor(), isbn, getAnoPublicacao(), quantidadeDisponivel);
    }
}