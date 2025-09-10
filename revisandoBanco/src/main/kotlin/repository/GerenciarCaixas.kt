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

fun criarTabelaCaixa() {

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

fun listarById() {
    var id = ""
    val regex = "^[1-9]\\d*|0\$"
    do {
        println("Digite o ID que deseja listar")
        id = readlnOrNull() ?: "a"
    } while (id == regex)
    if (id == "-1") {
        println("ID invalido")
        return
    }
    val sql = "SELECT * FROM CaixaDAgua Where id = ?"
    val stmt = conectar.connectarComBanco()!!.prepareStatement(sql)
    stmt.setInt(1, id.toInt())
    val resp = stmt.executeQuery()
    println("[")
    while (resp.next()) {
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
}

fun createCaixa() {
    println("----------- Criando Caixa -----------")
    /*capacidade: Int,*/
    println("Digite a capacidade da caixa d'água")
    val capacidade: Int = readln().toIntOrNull() ?: 0

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
    val peso: Double = readln().toDoubleOrNull() ?: 0.0

    /*marca: String,*/
    println("Qual a marca da caixa d'água")
    val marca: String = readlnOrNull() ?: ""

    /*cor: String*/
    println("Qual a cor da caixa d'água")
    val cor: String = readlnOrNull() ?: ""

    /*tampa: Boolean*/
    println("Tem tampa?(1 = sim, 2 = não)")
    val tampa: Boolean = when (readln().toInt()) {
        1 -> true
        2 -> false
        else -> false
    }

    /*preco: BigDecimal*/
    println("Qual o preço da caixa d'água")
    val preco: BigDecimal = (readlnOrNull()?.toBigDecimal() ?: "0.0") as BigDecimal

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
    println("-------------------------------------")
}

fun editarCaixa() {
    println("----------- Editando Caixa -----------")
    val regexOnlyFloats = Regex("^\\d+.\\d+\$")
    val regexOnlyNumbers = Regex("^\\d+$")
    val regexEscolhaAtributo = Regex("^[1-7]$")
    val regexId = Regex("^[1-9][0-9]*$")
    listarCaixas()
    var escolhaId = 0
    do {
        println("Qual o id da caixa que você deseja editar?")
        escolhaId = readln().toIntOrNull() ?: 0
        if (!escolhaId.toString().matches(regexId)) {
            println("Erro, escolha um id valido")
        }
    } while (!escolhaId.toString().matches(regexId))
    val atributo = arrayOf("material", "capacidade", "marca", "cor", "tampa", "preco", "peso")
    var atributoEscolhido = "0"
    do {
        println("1- material, 2-capacidade, 3-marca, 4-cor, 5-tampa, 6-preço, 7-peso")
        println("Qual atributo deseja trocar?")
        atributoEscolhido = readlnOrNull() ?: "0"
        if (!atributoEscolhido.matches(regexEscolhaAtributo)) {
            println("Erro, escolha um atributo valido")
        }
    } while (!atributoEscolhido.matches(regexEscolhaAtributo))
    var novoAtributo = "";
    val sql = "UPDATE CaixaDAgua SET ${atributo[atributoEscolhido.toInt() - 1]} = ? WHERE id = ?"

    val banco = conectar.connectarComBanco()
    val stmt = banco!!.prepareStatement(sql)
    stmt.setInt(2, escolhaId)
    when (atributoEscolhido) {
        "1" -> {
            println("Digite o novo material da caixa d'água")
            println("1 - POLIETILENO")
            println("2 - FIBRADEVIDRO")
            println("3 - ACOINOXIDAVEL")
            println("4 - CONCRETO")
            println("5 - OUTRO")

            novoAtributo = when (readln()) {
                "1" -> Material.POLIETILENO.toString()
                "2" -> Material.FIBRADEVIDRO.toString()
                "3" -> Material.ACOINOXIDAVEL.toString()
                "4" -> Material.CONCRETO.toString()
                "5" -> Material.OUTRO.toString()
                else -> Material.OUTRO.toString()
            }
            stmt.setString(1, novoAtributo)
        }

        "2" -> {
            do {
                println("Digite a nova capacidade da caixa d'água")
                novoAtributo = readlnOrNull() ?: "0"
            } while (!regexOnlyFloats.matches(novoAtributo))
            stmt.setFloat(1, novoAtributo.toFloat())
        }

        "3" -> {
            println("Qual a nova marca da caixa d'água")
            novoAtributo = readlnOrNull() ?: "Sem Marca"

            stmt.setString(1, novoAtributo)
        }

        "4" -> {
            println("Qual o novo cor da caixa d'água?")
            novoAtributo = readlnOrNull() ?: "Sem Cor"

            stmt.setString(1, novoAtributo)
        }

        "5" -> {
            println("Agora tem tampa?(1 = sim, 2 = não)")
            novoAtributo = when (readln().toInt()) {
                1 -> "true"
                2 -> "false"
                else -> "false"
            }

            stmt.setBoolean(1, novoAtributo.toBoolean())
        }

        "6" -> {
            do {
                println("Qual o novo preço da caixa d'água(Ex: 10.0)")
                novoAtributo = readlnOrNull() ?: "0.0"
            } while (!regexOnlyFloats.matches(novoAtributo))

            stmt.setString(1, novoAtributo)
        }

        "7" -> {
            do {
                println("Qual o novo peso da caixa?")
                novoAtributo = readlnOrNull() ?: "0"
            } while (!regexOnlyFloats.matches(novoAtributo))
            stmt.setFloat(1, novoAtributo.toFloat())
        }

        else -> {
            println("Escolha invalida")
            return
        }
    }
    stmt.executeUpdate()
    println("Id $escolhaId alterado com sucesso")
    banco.close()
    println("--------------------------------------")
}

fun listarCaixas() {
    println("----------- Listando Caixa(s) -----------")
    val sql = "SELECT * FROM CaixaDAgua"
    val banco = conectar.connectarComBanco()
    val stmt = banco!!.createStatement()
    val resp = stmt.executeQuery(sql)
    while (resp.next()) {
        val id = resp.getInt("id")
        val material = resp.getString("material")
        val capacidade = resp.getFloat("capacidade")
        val peso = resp.getString("marca")
        val cor = resp.getString("cor")
        val tampa = resp.getBoolean("tampa")
        val preco = resp.getString("preco")
        println("{id = $id, material = $material, capacidade = $capacidade, peso = $peso, cor = $cor, tampa = $tampa, preco = $preco}")
    }
    banco.close()
    println("-----------------------------------------")
}

fun deleteCaixas() {
    println("----------- Deletando Caixa -----------")
    val regexId = Regex("^[1-9][0-9]*$")
    println("Caixas:[")
    listarCaixas()
    println("]")
    var caixaExcluir = 0
    do {
        println("Qual caixa deseja excluir?(escreva o id)")
        caixaExcluir = readln().toIntOrNull() ?: 0
        if (!caixaExcluir.toString().matches(regexId)) {
            println("Erro, escolha um id valido")
        }
    } while (!caixaExcluir.toString().matches(regexId))
    println("Are you sure? Type 1 to execute")
    if ((readlnOrNull() ?: "0") != "1") {
        println("Cancelado com sucesso!")
        return
    }
    println("Em processo de eliminação")
    val sql = "DELETE FROM CaixaDAgua WHERE id = ?"
    val banco = conectar.connectarComBanco()
    val stmt = banco!!.prepareStatement(sql)
    stmt.setInt(1, caixaExcluir)
    val linhasAfetadas = stmt.executeUpdate()
    if (linhasAfetadas > 0) {
        println("Excluido com sucesso")
    } else {
        println("Id não encontrado")
    }
    banco.close()
    println("---------------------------------------")
}
