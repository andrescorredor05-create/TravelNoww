public class TarjetaCredito extends MetodoPago {
    public TarjetaCredito(String idPago, double monto) {
        super(idPago, monto);
    }
    public void procesarPago() {
        System.out.println("Procesando pago con tarjeta de crédito por $" + monto);
    }
}
