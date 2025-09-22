import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String email;
    private String telefono;
    private int id;
    private List<Reserva> reservas; 

    public Cliente(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

  
    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            System.out.println("Reservas de " + nombre + ":");
            for (Reserva reserva : reservas) {
                reserva.mostrarReserva(); 
            }
        }
    }
}
