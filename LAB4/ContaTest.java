import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private Conta contaDoJoao;
    private Conta contaDaMaria;
    private Conta contaDoHugo;
    private Correntista Joao;
    private Correntista Maria;
    private Correntista Hugo;
    private float saldoInicial;

    @Before
    public void setUp() {
        Joao = new Correntista("Joao", 12345);
        contaDoJoao = new Conta(1, Joao);
        saldoInicial = contaDoJoao.getSaldoEmReais();

        Maria = new Correntista("Maria", 5678);
        contaDaMaria = new Conta(2, Maria);

        Hugo = new Correntista("Hugo", 8994);
        contaDoHugo = new Conta(3, Hugo);
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0, saldoInicial, FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito", saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados", saldoInicial,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados", saldoInicial,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);
    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta", saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicial, contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferencia() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("Valor de transferencia deve ser menor ou igual ao saldo da conta", saldoInicial + 3,
                contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        assertEquals("O valor transferido deve ser descontado da conta", saldoInicial - 3,
                contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Transferencia maior que o saldo deve ser ignorada", saldoInicial,
                contaDaMaria.getSaldoEmReais(), FLOAT_DELTA);

        contaDoJoao.efetuarTransferecia(contaDaMaria, -20);

        assertEquals("Valores negativos ou nulos devem ser ignorados", saldoInicial
                , contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void testarTransferenciaNegativasOuNulas() {

        contaDoJoao.efetuarTransferecia(contaDaMaria, -20);

        assertEquals("Valores negativos ou nulos devem ser ignorados", saldoInicial
                , contaDoJoao.getSaldoEmReais(), FLOAT_DELTA);
    }

    @Test
    public void TesteQuantidadeTransferencias() {

        contaDoHugo.efetuarTransferecia(contaDoJoao, 2);
        contaDoJoao.efetuarTransferecia(contaDoHugo, 1003546565);
        contaDoHugo.efetuarTransferecia(contaDoJoao, 3);
        contaDaMaria.efetuarTransferecia(contaDoJoao, 4);
        contaDaMaria.efetuarTransferecia(contaDoJoao, 1);
        contaDoHugo.efetuarTransferecia(contaDaMaria, 3);
        contaDoJoao.efetuarTransferecia(contaDaMaria, 1);
        contaDoHugo.efetuarTransferecia(contaDoJoao, -9000);
        contaDaMaria.sacar(96611654);
        contaDoHugo.sacar(78);
        contaDoHugo.sacar(6);
        contaDaMaria.sacar(11);

        assertEquals("Testando metodo quantidade de transacoes", 6,
                Conta.QuantidadeDeTransacoesDeTodasAsContas(), FLOAT_DELTA);
    }


    @Test
    public void getCpfDoCorrentistaTeste() {

        assertEquals("Conferindo se o metodo getCpfCorrentista está retornando o esperado", 12345,
                Joao.getCpf(), FLOAT_DELTA);
    }


    @Test
    public void testarContaEncerradaPeloGerenteDeContaErrado() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);

        assertTrue("Uma conta não pode ser encerrada por gerentes de conta " +
                "que não gerenciem aquela conta", contaDoJoao.isAtiva());
    }

    @Test
    public void testarContaEncerradaPeloGerenteDeContaCorreto() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);

        // sanity check
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteDeContaCarlos.encerrarConta(contaDoJoao);

        assertFalse("Uma conta deve ficar com status de inativa após ser encerrada " +
                "pelo seu gerente", contaDoJoao.isAtiva());

        assertFalse("Contas encerradas não devem constar da lista de contas " +
                "gerenciadas por gerente algum", gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));
    }

    @Test
    public void testarContaEncerradaPorUmGerenteGeral() {
        Gerente gerenteDeContaCarlos = new Gerente("Carlos Roberto", 999, 20);

        gerenteDeContaCarlos.gerenciarConta(contaDoJoao);

        GerenteGeral gerenteGeralMariza = new GerenteGeral("Mariza Silva", 888, 2);

        // sanity check
        assertTrue(gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));

        gerenteGeralMariza.encerrarConta(contaDoJoao);

        assertFalse("Uma conta deve ficar com status de inativa após ser encerrada " +
                "por um gerente geral", contaDoJoao.isAtiva());

        assertFalse("Uma conta encerrada não deve constar da lista de contas " +
                "gerenciadas pelo gerente geral que a encerrou", gerenteGeralMariza.ehGerenteDaConta(contaDoJoao));

        assertFalse("Contas encerradas não devem constar da lista de contas " +
                "gerenciadas por gerente algum", gerenteDeContaCarlos.ehGerenteDaConta(contaDoJoao));
    }


    //TESTES LAB 4!!!


    @Test
    public void TestandoMetodosCriadosNoContaInvestimento() {

        Correntista fufu = new Correntista("ALGUSTO", 444);
        Conta contadofufu = new Conta(31, fufu);

        contadofufu.receberDepositoEmDinheiro(15000);
        ContaInvestimento Investimentodofufu = new ContaInvestimento(789, fufu, "Bitcoin", 1.6f);

        contadofufu.efetuarTransferecia(Investimentodofufu, 15000);

        assertEquals("", 24016, Investimentodofufu.aplicarJuros(), FLOAT_DELTA);

        Investimentodofufu.resgatarTotal(contadofufu);

        assertEquals("", 0,Investimentodofufu.getSaldoEmReais(), FLOAT_DELTA);
    }
}
