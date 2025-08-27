package dominio.entidades

import dominio.enums.Senioridade
import dominio.enums.Setor
import dominio.enums.Sexo
import java.math.BigDecimal
import java.util.Optional

class Profissional(
    nome: String,
    idade: Int,
    altura: Double,
    peso: Double,
    sexo: Sexo,
    CPF: String,
    endereco: Endereco,
    var telefone: String,
    var profissao: String,
    var senioridade: Senioridade,
    var habilidade: String,
    var salario: BigDecimal,
    var SETOR: Setor,
    var CONTA: Conta
) : Pessoa(
    nome = nome, idade = idade, altura = altura, peso = peso, sexo = sexo,
    CPF = CPF, endereco = endereco
) {
    var eFormado: Optional<Boolean> = Optional.empty<Boolean>()
    var formação: Optional<String> = Optional.empty<String>()

    fun instalarCaixasDAgua(caixaDAgua: CaixaDAgua){
        if(this.SETOR == Setor.INSTALADOR){
            println("Pode instalar")
        }else{
            println("Não pode instalar")
        }
    }
}