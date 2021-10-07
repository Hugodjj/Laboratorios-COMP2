import java.util.ArrayList;

public class Conta {

        private final int numero;
        private Correntista correntista;
        private float saldoEmReais = 0;
        private ArrayList<String> transacoes;
        public static final float SALDO_INICIAL_DA_CONTA = 10;  // "constante"
        private static int quantidadeDeTransacoesDeTodasAsContas = 0;
        private boolean ativa;
        private Gerente gerente;
        public static ArrayList<Conta> ContasDoBanco = new ArrayList<>();

    // CONSTRUTOR: método especial que roda quando chamamos o "new" para instanciar
            public Conta(int numeroDaConta, Correntista correntista) {
            this.numero = numeroDaConta;
            this.correntista = correntista;
            this.saldoEmReais = SALDO_INICIAL_DA_CONTA;  // saldo inicial doado pelo banco
            this.transacoes = new ArrayList<>();
            this.transacoes.add("Conta criada com saldo de " + this.saldoEmReais);
            this.ContasDoBanco.add(this);
            ativa = true;
        }

        public float getSaldoEmReais() {
            return this.saldoEmReais;
        }

        public void receberDepositoEmDinheiro(float valor) {
            if (valor <= 0) {
                return;  // valor inválido; não faz nada!
                // ToDo lançar uma exceção
            }

            this.saldoEmReais += valor;

            quantidadeDeTransacoesDeTodasAsContas++;
        }

        public String getExtrato() {
            String resultado = "";
            for (int i = 0; i < transacoes.size(); i++) {
                resultado += transacoes.get(i) + "\n";
            }
            return resultado;
        }

        public void sacar(float valor) {

            if (valor > saldoEmReais || valor <=0){
                return;
            }
            this.saldoEmReais -= valor;

            quantidadeDeTransacoesDeTodasAsContas++;
        }
        public void efetuarTransferecia(Conta ContaDestino, float valor) {

            if (valor > saldoEmReais || valor <=0){
                return;
            }

            ContaDestino.saldoEmReais += valor;

            this.saldoEmReais -= valor;

            quantidadeDeTransacoesDeTodasAsContas++;
        }

        /**
         * Retorna a quantidade total de transações do banco, ou seja,
         * de todas as contas correntes que já foram criadas.
         *
         * @return o total de transações
         */
        public static int QuantidadeDeTransacoesDeTodasAsContas() {
            return quantidadeDeTransacoesDeTodasAsContas;
        }

    public void encerrar() {
        if (this.saldoEmReais < 0) {
            // ToDo lançar exceção
            // não deixa encerrar conta com saldo negativo
        }
        this.ativa = false;  // desativou a conta

        System.out.printf("\nConta %d encerrada", this.numero);
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente novaGerente) {
        if (this.gerente != null) {
            // avisa ao gerente antigo que ele não é mais gerente
            this.gerente.deixarDeGerenciarConta(this);
        }
        this.gerente = novaGerente;
    }

    public Correntista getCorrentista(){
            return this.correntista;
    }

    public boolean isAtiva() {
        return this.ativa;
    }
}