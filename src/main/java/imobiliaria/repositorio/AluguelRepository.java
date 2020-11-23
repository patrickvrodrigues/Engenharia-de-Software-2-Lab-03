package imobiliaria.repositorio;

import imobiliaria.modelo.Aluguel;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository {

    void salva(Aluguel aluguel);

    void exclui(Aluguel aluguel);

    void atualiza(Aluguel aluguel);

    List<Aluguel> buscaAlugueisPagosPorNomeInquilino(String nome);

    List<Aluguel> buscaAlugueisAtrasadosPorNomeInquilino(String nome);





}
