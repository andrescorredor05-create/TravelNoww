public class TransferenciaBancaria extends MetodoPago {
    public TransferenciaBancaria(String idPago, double monto) {
        super(idPago, monto);
    }
    public void procesarPago() {
        System.out.println("Procesando pago por transferencia bancaria por $" + monto);
    }
}