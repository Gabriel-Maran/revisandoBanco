import connection.EntidadeJDBC
import dominio.entidades.*
import dominio.enums.*
import repository.criarTabelaCaixa
import ui.menu
import java.math.BigDecimal
import java.time.LocalDate


fun main() {
    criarTabelaCaixa()
    menu()
}
