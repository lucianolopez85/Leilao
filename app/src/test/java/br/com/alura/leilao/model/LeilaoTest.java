package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private Leilao TESTE = new Leilao("teste");
    private final Usuario usuario1 = new Usuario("usuario1");

    @Test
    public void deve_DevolverADescricao() {
        String consoleDescricao = TESTE.getDescricao();
        assertEquals("teste", consoleDescricao);
    }

    //    ############### MAIOR LANCE ##################
    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeUmLance(){
        TESTE.propoe(new Lance(usuario1, 210.00));
        double maiorLanceDevolvidoDoConsole = TESTE.getMaiorLance();
        assertEquals(210.00, maiorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeDoisLancesNaOrdemCrescente(){
        TESTE.propoe(new Lance(usuario1, 500.00));
        TESTE.propoe(new Lance(new Usuario("usuario2"), 600.00));
        double maiorLanceDevolvidoDoComputador = TESTE.getMaiorLance();
        assertEquals(600.00, maiorLanceDevolvidoDoComputador, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeDoisLancesNaOrdemDrescente(){
        TESTE.propoe(new Lance(usuario1, 1000.00));
        TESTE.propoe(new Lance(new Usuario("usuario2"), 750.00));
        double maiorLanceDevolvidoDoNotebook = TESTE.getMaiorLance();
        assertEquals(1000.00, maiorLanceDevolvidoDoNotebook, DELTA);
    }
    //    ############### MENOR LANCE ##################
    @Test
    public void deve_DevolverMenorLance_QuandoRecebeUmLance(){
        TESTE.propoe(new Lance(usuario1, 210.00));
        double menorLanceDevolvido = TESTE.getMenorLance();
        assertEquals(210.00, menorLanceDevolvido, DELTA);
    }

//    @Test
//    public void deve_DevolverMenorLance_QuandoRecebeDoisLancesNaOrdemCrescente(){
//        TESTE.propoe(new Lance(usuario1, 500.00));
//        TESTE.propoe(new Lance(new Usuario("usuario2"), 600.00));
//        double menorLanceDevolvido1 = TESTE.getMenorLance();
//        assertEquals(500.00, menorLanceDevolvido1, DELTA);
//    }
//
//    @Test
//    public void deve_DevolverMenorLance_QuandoRecebeDoisLancesNaOrdemDecrescente(){
//        TESTE.propoe(new Lance(usuario1, 1000.00));
//        TESTE.propoe(new Lance(new Usuario("usuario2"), 750.00));
//        double menorLanceDevolvido2 = TESTE.getMenorLance();
//        assertEquals(750.00, menorLanceDevolvido2, DELTA);
//    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberExatosTresLances(){
        TESTE.propoe(new Lance(usuario1, 400.00));
        TESTE.propoe(new Lance(new Usuario("Maria"), 450.00));
        TESTE.propoe(new Lance(new Usuario("Manoel"), 460.00));

        List<Lance> tresMaioresLancesDevolvido = TESTE.tresMaioresLances();

        assertEquals(3,tresMaioresLancesDevolvido.size()) ;
        assertEquals(460.00, tresMaioresLancesDevolvido.get(0).getValor(), DELTA);
        assertEquals(450.00, tresMaioresLancesDevolvido.get(1).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvido.get(2).getValor(), DELTA);
    }
    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLance(){

        List<Lance> tresMaioresLancesDevolvido = TESTE.tresMaioresLances();
        assertEquals(0,tresMaioresLancesDevolvido.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberApenasUmLance(){
        TESTE.propoe(new Lance(usuario1, 200.00));

        List<Lance> tresMaioresLancesDevolvido = TESTE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvido.size());
        assertEquals(200.00, tresMaioresLancesDevolvido.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoReceberDoisLances(){
        TESTE.propoe(new Lance(usuario1, 300.00));
        TESTE.propoe(new Lance(new Usuario("Fernando"),350.00));

        List<Lance> tresMaioresLancesDevolvido = TESTE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvido.size());
        assertEquals(350.00, tresMaioresLancesDevolvido.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvido.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){


        TESTE.propoe(new Lance(usuario1, 300.00));
        TESTE.propoe(new Lance(new Usuario("Jorge"), 400.00));
        TESTE.propoe(new Lance(new Usuario("Artur"), 500.00));
        TESTE.propoe(new Lance(new Usuario("Cesar"), 200.00));

        final List<Lance> tresMaioresLancesDevolvidoParaQuatroLances = TESTE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidoParaQuatroLances.size());
        assertEquals(500.00, tresMaioresLancesDevolvidoParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvidoParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvidoParaQuatroLances.get(2).getValor(), DELTA);

        TESTE.propoe(new Lance(new Usuario("Carlos"), 600.00));

        final List<Lance> tresMaioresLancesDevolvidoParaCincoLances = TESTE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidoParaCincoLances.size());
        assertEquals(600.00, tresMaioresLancesDevolvidoParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(500.00, tresMaioresLancesDevolvidoParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvidoParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = TESTE.getMaiorLance();
        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = TESTE.getMaiorLance();
        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        TESTE.propoe(new Lance(usuario1, 500.00));
        TESTE.propoe(new Lance(new Usuario("Leticia"), 400.00));
        TESTE.propoe(new Lance(new Usuario("Lucas"), 600.00));
        TESTE.propoe(new Lance(new Usuario("Fernando"), 550.00));

        int quantidadeLanceDevolvida = TESTE.quantidadeDeLance();
        assertEquals(2,quantidadeLanceDevolvida,DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioForIgual(){
        TESTE.propoe(new Lance(usuario1, 500.00));
        TESTE.propoe(new Lance( new Usuario("usuario1"), 600.00));
        int quantidadeLanceDevolvida = TESTE.quantidadeDeLance();
        assertEquals(1,quantidadeLanceDevolvida);


    }


}


