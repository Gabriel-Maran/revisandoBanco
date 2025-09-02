package dominio.entidades

import dominio.enums.Material
import java.math.BigDecimal
import java.util.UUID

class CaixaDAgua(
    var material: Material,
    val capacidade: Int,
    var peso: Double,
    var marca: String,
    var cor: String,
    var tampa: Boolean,
    var preco: BigDecimal,
) {

}