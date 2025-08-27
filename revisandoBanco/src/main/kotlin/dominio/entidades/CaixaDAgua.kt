package dominio.entidades

import dominio.enums.Material
import java.math.BigDecimal
import java.util.UUID

class CaixaDAgua(
    val idCaixa:UUID,
    val capacidade: Int,
    var material: Material,
    var interno: Boolean,
    var dimensao: Array<Double>,
    var peso: Double,
    var marca: String,
    var cor: String,
    var tampa: Boolean,
    var preco: BigDecimal,
    var isInstalada: Boolean
) {
    override fun toString(): String {
        return "CaixaDAgua(capacidade=$capacidade, material=$material, interno=$interno, dimensao=${dimensao.contentToString()}, peso=$peso, marca='$marca', cor='$cor', tampa=$tampa, preco=$preco, isInstalada=$isInstalada)"
    }
}