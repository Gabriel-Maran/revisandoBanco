package ui

import repository.createCaixa
import repository.deleteCaixas
import repository.listarCaixas

fun menu() {
    var opcao = 0
    do {
        println("1- Cadastrar Caixas D'Água")
        println("2- Editar Caixas D'Água")
        println("3- Listar Caixas D'Água")
        println("4- Excluir Caixas D'Água")
        println("0- Sair")
        opcao = readln().toInt()

        when (opcao) {
            0 -> println("Saindo....")
            1 -> createCaixa()
            2 -> println("Editando caixa...")
            3 -> listarCaixas()
            4 -> deleteCaixas()
            else -> println("Opção inválida")
        }
    } while (opcao != 0)
}

