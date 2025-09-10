package ui

import repository.*

fun menu() {
    var opcao = 0
    do {
        println("1- Cadastrar Caixas D'Água")
        println("2- Editar Caixas D'Água")
        println("3- Listar Caixas D'Água")
        println("4- Verificar Caixa D'Água por Id")
        println("5- Excluir Caixas D'Água")
        println("0- Sair")
        opcao = readln().toInt()

        when (opcao) {
            0 -> println("Saindo....")
            1 -> createCaixa()
            2 -> editarCaixa()
            3 -> listarCaixas()
            4 -> listarById()
            5 -> deleteCaixas()
            else -> println("Opção inválida")
        }
    } while (opcao != 0)
}

