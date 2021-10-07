import java.util.ArrayList;

public class Documento {

    private ArrayList<String> numeroPaginas;
    private boolean emCores;

    public Documento(ArrayList<String> paginas, boolean emCores) {
        this.emCores = emCores;
        this.numeroPaginas = paginas;
        // ToDo IMPLEMENT ME!!!
    }

    public boolean isEmCores() {
        return this.emCores;  // ToDo IMPLEMENT ME!!!
    }

    public int getQuantPaginas() {
        return numeroPaginas.size();  // ToDo IMPLEMENT ME!!!
    }

    public String getPagina(int numeroDaPagina) {
        return numeroPaginas.get(numeroDaPagina - 1);  // ToDo IMPLEMENT ME!!!
    }
}
