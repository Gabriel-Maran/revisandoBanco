package repository

import connection.EntidadeJDBC
import dominio.entidades.CaixaDAgua
import dominio.enums.Material
import java.math.BigDecimal
import java.util.*

fun createCaixa() {
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


    val novaCaixa: CaixaDAgua = CaixaDAgua(
        capacidade = capacidade,
        material = material,
        peso = peso,
        marca = marca,
        cor = cor,
        tampa = tampa,
        preco = preco,
    )
}

fun criarTabelaCaixa(){
    val conectar = EntidadeJDBC(
        url = "jdbc:postgresql://localhost:5433/exemploHuilson",
        usuario = "postgres",
        senha = "root"
    )

    val banco = conectar.connectarComBanco()
    val sql = "CREATE TABLE IF NOT EXISTS CaixaDAgua (" +
            "id serial NOT NULL PRIMARY KEY, " +
            "material varchar(255) NOT NULL, " +
            "capacidade int NOT NULL, " +
            "peso float NOT NULL, " +
            "marca varchar(255) NOT NULL, " +
            "cor varchar(255) NOT NULL, " +
            "tampa boolean NOT NULL, " +
            "preco varchar(255) NOT NULL" +
            ");"

    val enviarParaBanco = banco!!.createStatement().execute(sql)

    println(enviarParaBanco)
    banco.close()
}

fun editarCaixa(caixa: CaixaDAgua) {

}

fun listarCaixas() {

}

fun deleteCaixas() {

}

