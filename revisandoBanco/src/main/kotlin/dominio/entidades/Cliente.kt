package dominio.entidades

import dominio.enums.Sexo

class Cliente(
    CPF: String,
    nome: String,
    idade: Int,
    altura: Double,
    peso: Double,
    sexo: Sexo,
    endereco: Endereco,
    var telefone: String,
    var email: String,
    var CONTA: Conta

): Pessoa(
CPF = CPF, nome = nome, idade = idade, altura = altura,
  peso = peso, sexo = sexo, endereco = endereco
)
{
    var listaCompras: MutableList<String> = mutableListOf()

    fun compra(item: String) {
        listaCompras.add(item)
    }
}