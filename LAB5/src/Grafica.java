import java.util.ArrayList;

public class Grafica {

    private int AuxiliarDeRevezamento;
    private float precoImpressaoPreta;
    private float precoImpressaoColorida;
    private float orcamento;
    private ArrayList<Impressora> ListaImpressoras = new ArrayList<>();

    public Grafica(){
        orcamento = 0f;
        precoImpressaoPreta = 0.10f; // valor em real
        precoImpressaoColorida = 0.30f; // valor em real
    }

    public void imprimirDocumento(Documento documento) {

            for (int i = 0; i < ListaImpressoras.size(); i++) {
                if (AuxiliarDeRevezamento == i) {
                    ListaImpressoras.get(i).imprimirDocumento(documento);
                    AuxiliarDeRevezamento++;
                    if (AuxiliarDeRevezamento >= ListaImpressoras.size()) {
                        AuxiliarDeRevezamento = 0;
                    }
                    break;
                }
            }
        // ToDo IMPLEMENT ME!!!
    }

    public float orcarImpressao(Documento documento) {

        if (documento.isEmCores()){
            orcamento = precoImpressaoColorida * (documento.getQuantPaginas());
            return orcamento;
        }
            orcamento = precoImpressaoPreta * documento.getQuantPaginas();

        return orcamento;  // ToDo IMPLEMENT ME!!!
    }
    public void adicionarImpressora(Impressora impressora) {

        ListaImpressoras.add(impressora);

        // ToDo IMPLEMENT ME!!!
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {

        if (emCores){
            precoImpressaoColorida = precoPorPagina;
            return;
        }
        precoImpressaoPreta = precoPorPagina;
        // ToDo IMPLEMENT ME!!!
    }
}
