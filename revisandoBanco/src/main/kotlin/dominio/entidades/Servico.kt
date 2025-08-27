package dominio.entidades

import java.math.BigDecimal
import java.time.LocalDate

class Servico(
    var responsaveis: Array<Profissional>,
    var preco: BigDecimal,
    var tipo: String,
    var dataInicioPrevista: LocalDate,
    var dataFimPrevista: LocalDate,
    var trabalhaSeChover: Boolean,
    var expediente: Int
) {
    var dataInicioReal: LocalDate = LocalDate.of(9999, 1, 1)
    var dataFimReal: LocalDate = LocalDate.of(9999, 1, 1)
}