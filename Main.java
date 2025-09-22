import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Main {

    public static List<PaquetedeViaje> cargarPaquetes(String archivo) {
        List<PaquetedeViaje> paquetes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int id = Integer.parseInt(datos[0]);
                String destino = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);
                paquetes.add(new PaquetedeViaje(id, destino, precio, cantidad));
            }
        } catch (IOException e) {
            System.out.println("Error al leer paquetes: " + e.getMessage());
        }
        return paquetes;
    }

    public static void guardarReserva(String archivo, Reserva reserva, int idCliente, String metodoPago) {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(idCliente + ";" + reserva.getIdReserva() + ";" + reserva.getFechaReserva() + ";" +
                    reserva.getFechaViaje() + ";" + reserva.getCostoTotal() + ";" + metodoPago + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PaquetedeViaje> paquetes = cargarPaquetes("paquetes.csv");
        List<Cliente> clientes = new ArrayList<>();
        int idReserva = 1;

        while (true) {
            System.out.println("\n--- TravelNow ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Crear reserva");
            System.out.println("3. Mostrar reservas de un cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.print("ID: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Correo: ");
                String correo = sc.nextLine();
                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();
                clientes.add(new Cliente(id, nombre, correo, telefono));
                System.out.println("Cliente registrado.");
            } else if (opcion == 2) {
                System.out.print("ID cliente: ");
                int idCliente = sc.nextInt(); sc.nextLine();
                Cliente cliente = null;
                for (Cliente c : clientes) {
                    if (c.getId() == idCliente) cliente = c;
                }
                if (cliente == null) {
                    System.out.println("Cliente no encontrado.");
                    continue;
                }
                Reserva reserva = new Reserva(idReserva++, LocalDate.now().toString(), "", 0);
                while (true) {
                    System.out.println("Paquetes disponibles:");
                    for (PaquetedeViaje p : paquetes) {
                        p.mostrarPaquete();
                    }
                    System.out.print("ID paquete a agregar (0 para terminar): ");
                    int idPaquete = sc.nextInt();
                    if (idPaquete == 0) break;
                    PaquetedeViaje paqueteSeleccionado = null;
                    for (PaquetedeViaje paqueteDisponible : paquetes) {
                        if (paqueteDisponible.getIdPaquete() == idPaquete) paqueteSeleccionado = paqueteDisponible;
                    }
                    if (paqueteSeleccionado == null || paqueteSeleccionado.getCantidadDisponible() <= 0) {
                        System.out.println("Paquete no disponible.");
                        continue;
                    }
                    paqueteSeleccionado.actualizarDisponibilidad(1);
                    reserva.agregarPaquete(paqueteSeleccionado);
                }
                if (reserva.getPaquetes().isEmpty()) {
                    System.out.println("Debe seleccionar al menos un paquete para la reserva.");
                    continue;
                }
                System.out.print("Días hasta el viaje: ");
                int diasHastaViaje = sc.nextInt(); sc.nextLine();
                reserva.setFechaViaje(LocalDate.now().plusDays(diasHastaViaje).toString());
                reserva.calcularCostoTotal();

                System.out.print("Método de pago (1=Tarjeta, 2=Transferencia): ");
                int opcionMetodoPago = sc.nextInt(); sc.nextLine();
                MetodoPago metodoPagoReserva;
                if (opcionMetodoPago == 1) {
                    metodoPagoReserva = new TarjetaCredito("T" + reserva.getIdReserva(), reserva.getCostoTotal());
                } else {
                    metodoPagoReserva = new TransferenciaBancaria("B" + reserva.getIdReserva(), reserva.getCostoTotal());
                }
                metodoPagoReserva.procesarPago();

                cliente.registrarReserva(reserva);
                guardarReserva("reservas.csv", reserva, cliente.getId(), metodoPagoReserva.getClass().getSimpleName());
                System.out.println("Reserva registrada.");
            } else if (opcion == 3) {
                System.out.print("ID cliente: ");
                int idCliente = sc.nextInt(); sc.nextLine();
                Cliente cliente = null;
                for (Cliente c : clientes) {
                    if (c.getId() == idCliente) cliente = c;
                }
                if (cliente != null) {
                    cliente.mostrarReservas();
                } else {
                    System.out.println("Cliente no encontrado.");
                }
            } else if (opcion == 4) {
                break;
            }
        }
        sc.close();
    }
}