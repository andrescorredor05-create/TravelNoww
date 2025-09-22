public class PaquetedeViaje {
    private int idPaquete;
    private String destino;
    private double precioBase;
    private int cantidadDisponible;

    public PaquetedeViaje(int idPaquete, String destino, double precioBase, int cantidadDisponible) {
        this.idPaquete = idPaquete;
        this.destino = destino;
        this.precioBase = precioBase;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public void mostrarPaquete() {
        System.out.println("ID Paquete: " + idPaquete);
        System.out.println("Destino: " + destino);
        System.out.println("Precio Base: $" + precioBase);
        System.out.println("Cantidad Disponible: " + cantidadDisponible);
    }

    public void actualizarDisponibilidad(int cantidad) {
        if (cantidadDisponible - cantidad >= 0) {
            cantidadDisponible -= cantidad;
            System.out.println("Disponibilidad actualizada. Nueva cantidad disponible: " + cantidadDisponible);
        } else {
            System.out.println("No hay suficiente disponibilidad para esta operación.");
        }
    }
}
