package imobiliaria.repositorio;

import imobiliaria.modelo.Imovel;
import imobiliaria.modelo.Locacao;

import java.math.BigDecimal;
import java.util.List;

public interface LocacaoRepository {
    void salva(Locacao locacao);
    void exclui(Locacao locacao);
    void atualiza(Locacao locacao);
    boolean imovelJaEstaAlugado(Imovel imovel);
    List<Locacao> imoveisDisponiveis();
    List<Locacao> imoveisDisponiveisPor(String bairro);
    List<Locacao> imoveisDisponiveisPorLimiteDe(BigDecimal preco);
}
