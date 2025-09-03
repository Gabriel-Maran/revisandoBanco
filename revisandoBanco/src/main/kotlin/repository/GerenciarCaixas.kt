package repository

import connection.EntidadeJDBC
import dominio.entidades.CaixaDAgua
import dominio.enums.Material
import java.math.BigDecimal

val conectar = EntidadeJDBC(
        url = "jdbc:postgresql://localhost:5433/exemploHuilson",
        usuario = "postgres",
        senha = "root"
    )



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
    val banco = conectar.connectarComBanco()
    val sql = "INSERT INTO CaixaDAgua (material, capacidade, peso, marca, cor, tampa, preco) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
    //Pré compila os parametros que serão enviados ao banco de dados(trata, retira possiveis injects)
    val statement = banco!!.prepareStatement(sql)
    statement.setString(1, novaCaixa.material.toString())
    statement.setInt(2, novaCaixa.capacidade)
    statement.setFloat(3, novaCaixa.peso.toFloat())
    statement.setString(4, novaCaixa.marca)
    statement.setString(5, novaCaixa.cor)
    statement.setBoolean(6, novaCaixa.tampa)
    statement.setString(7, novaCaixa.preco.toString())

    //Apos o statement, envia ao banco para a alteração. Basicamente da o commit pro banco
    statement.executeUpdate()
    println("Caixa cadastrada com sucesso!")
    banco.close()
}

fun criarTabelaCaixa(){

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
    val banco = conectar.connectarComBanco()
    val enviarParaBanco = banco!!.createStatement().execute(sql)

    println(enviarParaBanco)
    banco.close()
}

fun editarCaixa() {

}

fun listarCaixas() {
    val sql = "SELECT * FROM CaixaDAgua"
    val banco = conectar.connectarComBanco()
    val stmt = banco!!.createStatement()
    val resp = stmt.executeQuery(sql)
    println("[")
    while(resp.next()){
        val id = resp.getInt("id")
        val material = resp.getString("material")
        val capacidade = resp.getFloat("capacidade")
        val peso = resp.getString("marca")
        val cor = resp.getString("cor")
        val tampa = resp.getBoolean("tampa")
        val preco = resp.getString("preco")
        println("{id = $id, material = $material, capacidade = $capacidade, peso = $peso, cor = $cor, tampa = $tampa, preco = $preco}")
    }
    println("]")
    banco.close()
}

fun deleteCaixas() {
    println("Qual caixa deseja excluir?(escreva o id)")
    listarCaixas()
    val caixaExcluir = readln().toInt()
    val sql = "DELETE FROM CaixaDAgua WHERE id = ?"
    val banco = conectar.connectarComBanco()
    val stmt = banco!!.prepareStatement(sql)
    stmt.setInt(1, caixaExcluir)
    val linhasAfetadas = stmt.executeUpdate()
    if(linhasAfetadas > 0){
        println("Excluido com sucesso")
    }else{
        println("Id não encontrado")
    }
}
