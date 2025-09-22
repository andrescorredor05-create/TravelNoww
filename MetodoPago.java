public abstract class MetodoPago {
    protected String idPago;
    protected double monto;

    public MetodoPago(String idPago, double monto) {
        this.idPago = idPago;
        this.monto = monto;
    }

    public abstract void procesarPago();
}
