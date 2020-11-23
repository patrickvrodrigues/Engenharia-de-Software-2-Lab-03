package imobiliaria.servico;

import imobiliaria.modelo.Cliente;
import imobiliaria.modelo.Imovel;
import imobiliaria.modelo.Locacao;
import imobiliaria.repositorio.ImovelRepository;
import imobiliaria.repositorio.LocacaoRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LocacaoService {
    private LocacaoRepository locacaoRepository;
    private SPCService spcService;



    public Locacao alugarImovel(Cliente cliente, Imovel imovel, Integer diaVencimento, String obs) {

        if (cliente == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if(locacaoRepository.imovelJaEstaAlugado(imovel)){
            throw new IllegalArgumentException("Este imóvel ja está alugado");
        }


        if (spcService.estaNegativado(cliente) ) {
            throw new IllegalStateException("Não pode alugar filme para usuario com pendências no SPC");

        }


        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setCliente(cliente);
        locacao.setValorAluguel(imovel.getValorAluguelSugerido());
        locacao.setPercentualMulta(new BigDecimal("0.33"));
        locacao.setDiaVencimento(diaVencimento);
        locacao.setDataIncio(LocalDate.now());
        locacao.setDataFim(LocalDate.now().plusYears(1));
        locacao.setAtivo(true);
        locacao.setObs(obs);


        locacaoRepository.salva(locacao);

        return locacao;
    }



}
