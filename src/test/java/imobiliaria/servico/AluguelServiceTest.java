package imobiliaria.servico;

import imobiliaria.builder.AluguelBuilder;
import imobiliaria.builder.LocacaoBuilder;
import imobiliaria.modelo.Aluguel;
import imobiliaria.modelo.Locacao;
import imobiliaria.repositorio.AluguelRepository;
import imobiliaria.repositorio.LocacaoRepository;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class AluguelServiceTest {
    private AluguelService aluguelService;

    private AluguelRepository aluguelRepository;
    private LocacaoRepository locacaoRepository;

    @Before
    public void setup() {
        aluguelService = new AluguelService();

        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        aluguelRepository = Mockito.mock(AluguelRepository.class);

        aluguelService.setAluguelRepository(aluguelRepository );
    }

    @Test(expected=IllegalArgumentException.class)
    public void testaUmPagamentoComValorMenor() {

        Locacao locacao1 = LocacaoBuilder.umaLocacao().comValorDeAluguel(new BigDecimal("900")).constroi();

        locacaoRepository.salva(locacao1);
        Aluguel aluguel1 = AluguelBuilder.umAluguel().comLocacao(locacao1)
                .comDataVencimento(LocalDate.now().plusDays(7)).constroi();
        aluguelRepository.salva(aluguel1);

        aluguelService.pagarAluguel(aluguel1,new BigDecimal("800"));

    }
    @Test
    public void testaUmPagamentoComAtraso() {

        Locacao locacao1 = LocacaoBuilder.umaLocacao().comValorDeAluguel(new BigDecimal("900")).constroi();

        locacaoRepository.salva(locacao1);
        Aluguel aluguel1 = AluguelBuilder.umAluguel().comLocacao(locacao1)
                .comDataVencimento(LocalDate.now().minusDays(7)).constroi();
        aluguelRepository.salva(aluguel1);

        aluguel1 = aluguelService.pagarAluguel(aluguel1,new BigDecimal("920.79"));
        Assert.assertEquals(920.79,aluguel1.getValorPago().doubleValue(),0.00001);
        
    }
}
