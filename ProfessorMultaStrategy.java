package biblioteca.modelo;

/**
 * Representa um recurso genérico da biblioteca.
 */
public abstract class RecursoBiblioteca {
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public RecursoBiblioteca() {
        this("", "", 0);
    }

    public RecursoBiblioteca(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
}