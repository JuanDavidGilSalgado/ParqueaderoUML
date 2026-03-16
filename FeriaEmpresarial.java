Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ===== CLASE EMPRESA =====
    static class Empresa {
        private String nombre;
        private String sector;
        private String correo;
        private Stand stand;

        public Empresa(String nombre, String sector, String correo) {
            this.nombre = nombre;
            this.sector = sector;
            this.correo = correo;
        }

        public String getNombre() {
            return nombre;
        }

        public void asignarStand(Stand stand) {
            this.stand = stand;
        }

        @Override
        public String toString() {
            return "Empresa: " + nombre + " | Sector: " + sector + " | Correo: " + correo;
        }
    }

    // ===== CLASE VISITANTE =====
    static class Visitante {
        private String nombre;
        private String identificacion;
        private String correo;

        public Visitante(String nombre, String identificacion, String correo) {
            this.nombre = nombre;
            this.identificacion = identificacion;
            this.correo = correo;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return "Visitante: " + nombre + " | ID: " + identificacion + " | Correo: " + correo;
        }
    }

    // ===== CLASE COMENTARIO =====
    static class Comentario {
        private Visitante visitante;
        private int calificacion;
        private String mensaje;

        public Comentario(Visitante visitante, int calificacion, String mensaje) {
            this.visitante = visitante;
            this.calificacion = calificacion;
            this.mensaje = mensaje;
        }

        public int getCalificacion() {
            return calificacion;
        }

        @Override
        public String toString() {
            return visitante.getNombre() +
                    " | Calificación: " + calificacion +
                    " | Comentario: " + mensaje;
        }
    }

    // ===== CLASE STAND =====
    static class Stand {
        private int numero;
        private String ubicacion;
        private String tamano;
        private Empresa empresa;
        private ArrayList<Comentario> comentarios = new ArrayList<>();

        public Stand(int numero, String ubicacion, String tamano) {
            this.numero = numero;
            this.ubicacion = ubicacion;
            this.tamano = tamano;
        }

        public int getNumero() {
            return numero;
        }

        public void asignarEmpresa(Empresa empresa) {
            this.empresa = empresa;
            empresa.asignarStand(this);
        }

        public void agregarComentario(Comentario comentario) {
            comentarios.add(comentario);
        }

        public double calcularPromedio() {
            if (comentarios.isEmpty()) return 0;
            int suma = 0;
            for (Comentario c : comentarios) {
                suma += c.getCalificacion();
            }
            return (double) suma / comentarios.size();
        }

        public void mostrarComentarios() {
            if (comentarios.isEmpty()) {
                System.out.println("No hay comentarios.");
                return;
            }
            for (Comentario c : comentarios) {
                System.out.println(c);
            }
            System.out.println("Promedio: " + calcularPromedio());
        }

        @Override
        public String toString() {
            return "Stand #" + numero + " | Ubicación: " + ubicacion + " | Tamaño: " + tamano;
        }
    }

    // ===== CLASE FERIA =====
    static class Feria {
        private ArrayList<Empresa> empresas = new ArrayList<>();
        private ArrayList<Stand> stands = new ArrayList<>();
        private ArrayList<Visitante> visitantes = new ArrayList<>();

        public void registrarEmpresa(Empresa e) {
            empresas.add(e);
        }

        public void registrarStand(Stand s) {
            stands.add(s);
        }

        public void registrarVisitante(Visitante v) {
            visitantes.add(v);
        }

        public ArrayList<Stand> getStands() {
            return stands;
        }

        public ArrayList<Visitante> getVisitantes() {
            return visitantes;
        }

        public void reporteEmpresas() {
            if (empresas.isEmpty()) {
                System.out.println("No hay empresas registradas.");
                return;
            }
            for (Empresa e : empresas) {
                System.out.println(e);
            }
        }

        public void reporteVisitantes() {
            if (visitantes.isEmpty()) {
                System.out.println("No hay visitantes registrados.");
                return;
            }
            for (Visitante v : visitantes) {
                System.out.println(v);
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Feria feria = new Feria();
        int opcion;

        do {
            System.out.println("\n=== FERIA EMPRESARIAL ===");
            System.out.println("1. Registrar Empresa");
            System.out.println("2. Registrar Stand");
            System.out.println("3. Registrar Visitante");
            System.out.println("4. Agregar Comentario a Stand");
            System.out.println("5. Ver Comentarios de Stand");
            System.out.println("6. Reporte Empresas");
            System.out.println("7. Reporte Visitantes");
            System.out.println("8. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Sector: ");
                    String sector = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    feria.registrarEmpresa(new Empresa(nombre, sector, correo));
                    System.out.println("Empresa registrada.");
                    break;

                case 2:
                    System.out.print("Número de Stand: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ubicación: ");
                    String ubicacion = sc.nextLine();
                    System.out.print("Tamaño: ");
                    String tamano = sc.nextLine();
                    feria.registrarStand(new Stand(numero, ubicacion, tamano));
                    System.out.println("Stand registrado.");
                    break;

                case 3:
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Correo: ");
                    String cor = sc.nextLine();
                    feria.registrarVisitante(new Visitante(nom, id, cor));
                    System.out.println("Visitante registrado.");
                    break;

                case 4:
                    if (feria.getStands().isEmpty() || feria.getVisitantes().isEmpty()) {
                        System.out.println("Debe haber stands y visitantes registrados.");
                        break;
                    }

                    System.out.print("Número de Stand: ");
                    int numStand = sc.nextInt();
                    sc.nextLine();

                    Stand standSeleccionado = null;
                    for (Stand s : feria.getStands()) {
                        if (s.getNumero() == numStand) {
                            standSeleccionado = s;
                            break;
                        }
                    }

                    if (standSeleccionado == null) {
                        System.out.println("Stand no encontrado.");
                        break;
                    }

                    System.out.print("Nombre del visitante: ");
                    String nombreVisitante = sc.nextLine();

                    Visitante visitanteSeleccionado = null;
                    for (Visitante v : feria.getVisitantes()) {
                        if (v.getNombre().equalsIgnoreCase(nombreVisitante)) {
                            visitanteSeleccionado = v;
                            break;
                        }
                    }

                    if (visitanteSeleccionado == null) {
                        System.out.println("Visitante no encontrado.");
                        break;
                    }

                    int calificacion;
                    do {
                        System.out.print("Calificación (1-5): ");
                        calificacion = sc.nextInt();
                    } while (calificacion < 1 || calificacion > 5);
                    sc.nextLine();

                    System.out.print("Mensaje: ");
                    String mensaje = sc.nextLine();

                    standSeleccionado.agregarComentario(
                            new Comentario(visitanteSeleccionado, calificacion, mensaje)
                    );

                    System.out.println("Comentario agregado.");
                    break;

                case 5:
                    System.out.print("Número de Stand: ");
                    int num = sc.nextInt();
                    sc.nextLine();

                    for (Stand s : feria.getStands()) {
                        if (s.getNumero() == num) {
                            s.mostrarComentarios();
                        }
                    }
                    break;

                case 6:
                    feria.reporteEmpresas();
                    break;

                case 7:
                    feria.reporteVisitantes();
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 8);

        sc.close();
    }
}
