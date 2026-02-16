GestionParqueadero.java
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

// ===== CLASE BASE =====
class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected LocalDateTime horaEntrada;

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public double calcularCosto(long horas) {
        return 0; // Se sobreescribe en las clases hijas
    }
}

// ===== AUTOMOVIL =====
class Automovil extends Vehiculo {
    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, String tipoCombustible) {
        super(placa, marca, modelo);
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public double calcularCosto(long horas) {
        return horas * 3000; // tarifa simple
    }
}

// ===== MOTOCICLETA =====
class Motocicleta extends Vehiculo {
    private int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, int cilindraje) {
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(long horas) {
        return horas * 1500;
    }
}

// ===== CAMION =====
class Camion extends Vehiculo {
    private double capacidadCarga;

    public Camion(String placa, String marca, String modelo, double capacidadCarga) {
        super(placa, marca, modelo);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public double calcularCosto(long horas) {
        return horas * 7000;
    }
}

// ===== PARQUEADERO =====
class Parqueadero {
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public void registrarEntrada(Vehiculo v) {
        listaVehiculos.add(v);
        System.out.println("Vehículo registrado correctamente.");
    }

    public void registrarSalida(String placa) {
        Vehiculo encontrado = null;

        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                encontrado = v;
                break;
            }
        }

        if (encontrado != null) {
            LocalDateTime salida = LocalDateTime.now();
            Duration duracion = Duration.between(encontrado.getHoraEntrada(), salida);

            long horas = duracion.toHours();
            if (duracion.toMinutes() % 60 != 0) {
                horas++; // fracción cuenta como hora completa
            }
            if (horas == 0) {
                horas = 1;
            }

            double costo = encontrado.calcularCosto(horas);

            listaVehiculos.remove(encontrado);

            System.out.println("Horas: " + horas);
            System.out.println("Total a pagar: $" + costo);
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    public void mostrarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos en el parqueadero.");
        } else {
            for (Vehiculo v : listaVehiculos) {
                System.out.println("Placa: " + v.getPlaca());
            }
        }
    }
}

// ===== CLASE PRINCIPAL =====
public class GestionParqueadero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero();
        int opcion;

        do {
            System.out.println("\n--- PARQUEADERO ---");
            System.out.println("1. Ingresar vehículo");
            System.out.println("2. Registrar salida");
            System.out.println("3. Ver vehículos");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Tipo (1=Auto, 2=Moto, 3=Camión): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("Tipo de combustible: ");
                        String combustible = sc.nextLine();
                        parqueadero.registrarEntrada(
                                new Automovil(placa, marca, modelo, combustible));
                    } else if (tipo == 2) {
                        System.out.print("Cilindraje: ");
                        int cilindraje = sc.nextInt();
                        parqueadero.registrarEntrada(
                                new Motocicleta(placa, marca, modelo, cilindraje));
                    } else if (tipo == 3) {
                        System.out.print("Capacidad de carga (ton): ");
                        double carga = sc.nextDouble();
                        parqueadero.registrarEntrada(
                                new Camion(placa, marca, modelo, carga));
                    }
                    break;

                case 2:
                    System.out.print("Ingrese placa: ");
                    String placaSalida = sc.nextLine();
                    parqueadero.registrarSalida(placaSalida);
                    break;

                case 3:
                    parqueadero.mostrarVehiculos();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}
