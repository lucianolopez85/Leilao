package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    private Leilao teste = new Leilao("teste");
    private Usuario usuario1 = new Usuario("usuario1");
    private Usuario usuario2 = new Usuario("usuario2");

    @Test
    public void getDescricao() {
        String consoleDescricao = teste.getDescricao();
        assertEquals("teste", consoleDescricao);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeUmLance(){
        teste.propoe(new Lance(usuario1, 210.00));
        double maiorLanceDevolvidoDoConsole = teste.getMaiorLance();
        assertEquals(210.00, maiorLanceDevolvidoDoConsole,0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeDoisLancesNaOrdemCrescente(){
        teste.propoe(new Lance(usuario1, 500.00));
        teste.propoe(new Lance(usuario2, 600.00));
        double maiorLanceDevolvidoDoComputador = teste.getMaiorLance();
        assertEquals(600.00, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeDoisLancesNaOrdemDrescente(){
        teste.propoe(new Lance(usuario1, 1000.00));
        teste.propoe(new Lance(usuario2, 750.00));
        double maiorLanceDevolvidoDoNotebook = teste.getMaiorLance();
        assertEquals(1000.00, maiorLanceDevolvidoDoNotebook, 0.0001);
    }
//    ############### MENOR LANCE ##################
    @Test
    public void deve_DevolverMenorLance_QuandoRecebeUmLance(){
        teste.propoe(new Lance(usuario1, 210.00));
        double menorLanceDevolvido = teste.getMenorLance();
        assertEquals(210.00, menorLanceDevolvido,0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeDoisLancesNaOrdemCrescente(){
        teste.propoe(new Lance(usuario1, 500.00));
        teste.propoe(new Lance(usuario2, 600.00));
        double menorLanceDevolvido1 = teste.getMenorLance();
        assertEquals(500.00, menorLanceDevolvido1, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeDoisLancesNaOrdemDecrescente(){
        teste.propoe(new Lance(usuario1, 1000.00));
        teste.propoe(new Lance(usuario2, 750.00));
        double menorLanceDevolvido2 = teste.getMenorLance();
        assertEquals(750.00, menorLanceDevolvido2, 0.0001);
    }
}