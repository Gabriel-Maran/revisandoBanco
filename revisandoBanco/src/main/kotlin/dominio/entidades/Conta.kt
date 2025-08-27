package dominio.entidades

import dominio.enums.TipoConta
import java.math.BigDecimal

open class Conta(
    val id:Long,
    var isAtiva:Boolean,
    var saldo:BigDecimal,
    var TIPO: TipoConta,
    var agencia: String,
    var numConta:String,
    var titular: Pessoa
) {
    fun pagar(pagamento:BigDecimal){
        if(this.saldo >= pagamento && pagamento > BigDecimal.ZERO){
            this.saldo = this.saldo.subtract(pagamento);
        }else{
            println("Saldo insuficiente")
        }
    }

    fun receber(pagamento: BigDecimal){
        if(pagamento > BigDecimal.ZERO){
            this.saldo = this.saldo.add(pagamento)
        }
    }
}