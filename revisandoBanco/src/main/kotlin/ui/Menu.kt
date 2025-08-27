package ui

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
            1 -> println("Cadastrando caixa...")
            2 -> println("Editando caixa...")
            3 -> println("Listar caixas...")
            4 -> println("Excluindo caixa...")
            else -> println("Opção inválida")
        }
    } while (opcao != 0)
}