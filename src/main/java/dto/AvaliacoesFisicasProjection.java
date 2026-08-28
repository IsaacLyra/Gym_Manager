package dto;

import java.math.BigDecimal;

public interface AvaliacoesFisicasProjection {

    Integer getIdAluno();
    String getNomeAluno();
    Integer getAvaliacao();
    BigDecimal getPeso();
    BigDecimal getAltura();
    BigDecimal getPercentualGorduraCorporal();

}
