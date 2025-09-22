import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int idReserva;
    private String fechaReserva;
    private String fechaViaje;
    private double costoTotal;
    private List<PaquetedeViaje> paquetes;

    public Reserva(int idReserva, String fechaReserva, String fechaViaje, double costoTotal) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.fechaViaje = fechaViaje;
        this.costoTotal = costoTotal;
        this.paquetes = new ArrayList<>(); 
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    public void mostrarReserva() {
        System.out.println("ID Reserva: " + idReserva);
        System.out.println("Fecha de Reserva: " + fechaReserva);
        System.out.println("Fecha de Viaje: " + fechaViaje);
        System.out.println("Costo Total: $" + costoTotal);
    }
    public void agregarPaquete(PaquetedeViaje paquete) {
        paquetes.add(paquete);
    }
    public void calcularCostoTotal() {
        costoTotal = 0;
        for (PaquetedeViaje paquete : paquetes) {
            costoTotal += paquete.getPrecioBase();
        }
    }

    public List<PaquetedeViaje> getPaquetes() {
        return paquetes;
    }
}
