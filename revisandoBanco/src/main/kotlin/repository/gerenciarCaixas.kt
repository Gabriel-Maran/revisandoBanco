package repository

import dominio.entidades.CaixaDAgua
import dominio.enums.Material
import java.math.BigDecimal
import java.util.*

fun createCaixa() {
    /*idCaixa:UUID*/
    val idCaixa:UUID = UUID.randomUUID()

    /*capacidade: Int,*/
    println("Digite a capacidade da caixa d'água")
    val capacidade: Int = readln().toInt()

    /*material: Material*/
    println("Digite o material da caixa d'água")
    println("1 - POLIETILENO")
    println("2 - FIBRADEVIDRO")
    println("3 - ACOINOXIDAVEL")
    println("4 - CONCRETO")
    println("5 - OUTRO")

    val material: Material = when (readln().toInt()) {
        1 -> Material.POLIETILENO
        2 -> Material.FIBRADEVIDRO
        3 -> Material.ACOINOXIDAVEL
        4 -> Material.CONCRETO
        5 -> Material.OUTRO
        else -> Material.OUTRO
    }

    /*interno: Boolean*/
    println("Tem interno?(1 = sim, 2 = não)")
    val interno: Boolean = when (readln().toInt()) {
        1 -> true
        2 -> false
        else -> false
    }

    /*dimensao: Array<Double>*/
    println("Altura")
    val altura = readln().toDouble()
    println("Raio da Base")
    val raioBase = readln().toDouble()
    val dimensao: Array<Double> = arrayOf(altura, raioBase)

    /*peso: Double*/
    println("Qual o peso da caixa d'água?")
    val peso: Double = readln().toDouble()

    /*marca: String,*/
    println("Qual a marca da caixa d'água")
    val marca:String = readln()

    /*cor: String*/
    println("Qual a cor da caixa d'água")
    val cor:String = readln()

    /*tampa: Boolean*/
    println("Tem tampa?(1 = sim, 2 = não)")
    val tampa: Boolean = when (readln().toInt()) {
        1 -> true
        2 -> false
        else -> false
    }

    /*preco: BigDecimal*/
    println("Qual o preço da caixa d'água")
    val preco:BigDecimal = readln().toBigDecimal()

    /*isInstalada: Boolean*/
    println("Está instalada?(1 = sim, 2 = não)")
    val isInstalada: Boolean = when (readln().toInt()) {
        1 -> true
        2 -> false
        else -> false
    }

    val novaCaixa: CaixaDAgua = CaixaDAgua(
        idCaixa = idCaixa,
        capacidade = capacidade,
        material = material,
        interno = interno,
        dimensao = dimensao,
        peso = peso,
        marca = marca,
        cor = cor,
        tampa = tampa,
        preco = preco,
        isInstalada = isInstalada
    )
}
fun editarCaixa(id: UUID, caixa: CaixaDAgua) {

}

fun listarCaixas() {

}

fun deleteCaixas(id: UUID) {

}

