public abstract class Impressora {

    private int quantidadeFolhas;
    private int documentosimpressos;

    public Impressora() {
        this.documentosimpressos = 0;
    }

    public boolean imprimirDocumento(Documento documento) {

        if (documento.getQuantPaginas() > quantidadeFolhas){
            return false;
        }
        // verifica se há papel suficiente (se não houver, não imprime)
        // ToDo IMPLEMENT ME!!!

        // incrementa o número de documentos impressos
        // ToDo IMPLEMENT ME!!!

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.quantidadeFolhas --;
        }
            this.documentosimpressos +=1;

        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {

        this.quantidadeFolhas += numeroDeFolhas;
        // ToDo IMPLEMENT ME!!!
    }

    public int getQuantidadeFolhasRestantes() {
        return this.quantidadeFolhas;  // ToDo IMPLEMENT ME!!!
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.documentosimpressos;  // ToDo IMPLEMENT ME!!!
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}
