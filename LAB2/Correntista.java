public class Correntista {

    private final long cpf;
    private String nome;

    public Correntista(String NomeCorrentista, long CpfCorrentista){
        this.nome = NomeCorrentista;
        this.cpf  = CpfCorrentista;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpfDoCorrentista() {
        return this.cpf;
    }
}