import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Club {

    static Iterable<Factura> getFacturasBySocio(int idRegistro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List<Persona> personas;
    private List<Usuario> usuarios;
    private List<Socio> socios;
    private List<Invitado> invitados;
    private List<Factura> facturas;
    private List<DetalleFactura> detallesFactura;

    public Club() {
        personas = new ArrayList<>();
        usuarios = new ArrayList<>();
        socios = new ArrayList<>();
        invitados = new ArrayList<>();
        facturas = new ArrayList<>();
        detallesFactura = new ArrayList<>();
    }

    public Persona agregarPersona(String cedula, String nombre, String numeroCelular) {
        Persona persona = new Persona(cedula, nombre, numeroCelular);
        personas.add(persona);
        return persona;
    }

    public Usuario agregarUsuario(String nombreUsuario, String contrasena, String rol) {
        if (!esRolValido(rol)) {
            System.out.println("Error: Rol no válido. Debe ser 'socio', 'invitado' o 'administrador'.");
            return null;
        }

        Usuario usuario = new Usuario(nombreUsuario, contrasena, rol);
        usuarios.add(usuario);
        return usuario;
    }

    private boolean esRolValido(String rol) {
        return rol.equalsIgnoreCase("socio") || rol.equalsIgnoreCase("invitado") || rol.equalsIgnoreCase("administrador");
    }

    public Socio agregarSocio(int idUsuario, double fondosDisponibles, String tipoSuscripcion) {
        Socio socio = new Socio(idUsuario, fondosDisponibles, tipoSuscripcion);
        socios.add(socio);
        return socio;
    }

    public Invitado agregarInvitado(int idUsuario, int idSocioInvitador) {
        if (!existeSocio(idSocioInvitador)) {
            System.out.println("Error: El ID del socio que invita no existe.");
            return null;
        }

        Invitado invitado = new Invitado(idUsuario, idSocioInvitador);
        invitados.add(invitado);
        return invitado;
    }

    private boolean existeSocio(int idSocio) {
        for (Socio socio : socios) {
            if (socio.getIdRegistro() == idSocio) {
                return true;
            }
        }
        return false;
    }

    private boolean existePersona(int idPersona) {
        for (Persona persona : personas) {
            if (persona.getId() == idPersona) {
                return true;
            }
        }
        return false;
    }

    public Factura generarFactura(int idPersona, int idSocio, double valorTotal) {
        // Validamos que la persona y el socio existan
        if (!existePersona(idPersona)) {
            System.out.println("Error: La persona con ID " + idPersona + " no está registrada.");
            return null;
        }

        if (!existeSocio(idSocio)) {
            System.out.println("Error: El socio con ID " + idSocio + " no está registrado.");
            return null;
        }

        Factura factura = new Factura(idPersona, idSocio, valorTotal);
        facturas.add(factura);
        return factura;
    }

    public DetalleFactura agregarDetalleFactura(int idFactura, int numeroItem, String concepto, double valorItem) {
        DetalleFactura detalleFactura = new DetalleFactura(idFactura, numeroItem, concepto, valorItem);
        detallesFactura.add(detalleFactura);
        return detalleFactura;
    }

    public void pagarFactura(int idFactura) {
        for (Factura factura : facturas) {
            if (factura.getIdFactura() == idFactura) {
                factura.pagar();
                break;
            }
        }
    }

    public List<Socio> listarSocios() {
        return socios;
    }

    public List<Invitado> listarInvitados() {
        return invitados;
    }

    public List<Factura> listarFacturas() {
        return facturas;
    }

    public List<DetalleFactura> listarDetallesFactura() {
        return detallesFactura;
    }

    public Socio buscarSocioPorId(int idSocio) {
        for (Socio socio : socios) {
            if (socio.getIdRegistro() == idSocio) {
                return socio;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Club club = new Club();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\033[33mMenú de opciones:");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Usuario");
            System.out.println("3. Agregar Socio");
            System.out.println("4. Agregar Invitado");
            System.out.println("5. Generar Factura");
            System.out.println("6. Listar Socios");
            System.out.println("7. Listar Invitados");
            System.out.println("8. Listar Facturas");
            System.out.println("9. Salir");
            System.out.print("\033[32mSeleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\033[31mError: Debe ingresar un número.");
                scanner.next(); 
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine(); 
                    System.out.print("Ingrese cédula: ");
                    String cedula = scanner.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese número de celular (10 dígitos): ");
                    String numeroCelular = scanner.nextLine();

                    while (!esNumeroValido(numeroCelular)) {
                        System.out.println("\033[31mError: El número de celular debe tener 10 dígitos.");
                        System.out.print("Ingrese número de celular (10 dígitos): ");
                        numeroCelular = scanner.nextLine();
                    }

                    club.agregarPersona(cedula, nombre, numeroCelular);
                    System.out.println("\033[32mPersona agregada exitosamente.");
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.print("Ingrese nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    String contrasena = scanner.nextLine();
                    System.out.print("Ingrese rol (socio/invitado/administrador): ");
                    String rol = scanner.nextLine();

                    Usuario usuario = club.agregarUsuario(nombreUsuario, contrasena, rol);
                    if (usuario != null) {
                        System.out.println("\033[32mUsuario agregado exitosamente.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese ID de usuario: ");
                    int idUsuario = scanner.nextInt();
                    System.out.print("Ingrese fondos disponibles: ");
                    double fondosDisponibles = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Ingrese tipo de suscripción (regular/VIP): ");
                    String tipoSuscripcion = scanner.nextLine();
                    club.agregarSocio(idUsuario, fondosDisponibles, tipoSuscripcion);
                    System.out.println("\033[32mSocio agregado exitosamente.");
                    break;

                case 4:
                    System.out.print("Ingrese ID de usuario: ");
                    int idUsuarioInvitado = scanner.nextInt();
                    System.out.print("Ingrese ID del socio que lo invitó: ");
                    int idSocioInvitador = scanner.nextInt();
                    Invitado invitado = club.agregarInvitado(idUsuarioInvitado, idSocioInvitador);
                    if (invitado != null) {
                        System.out.println("\033[32mInvitado agregado exitosamente.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese ID de persona: ");
                    int idPersona = scanner.nextInt();
                    System.out.print("Ingrese ID de socio: ");
                    int idSocio = scanner.nextInt();
                    System.out.print("Ingrese valor total de la factura: ");
                    double valorTotal = scanner.nextDouble();
                    Factura factura = club.generarFactura(idPersona, idSocio, valorTotal);
                    if (factura != null) {
                        System.out.println("\033[32mFactura generada exitosamente.");
                    }
                    break;

                case 6:
                    System.out.println("Lista de Socios:");
                    for (Socio socio : club.listarSocios()) {
                        System.out.println(socio);
                    }
                    break;

                case 7:
                    System.out.println("Lista de Invitados:");
                    for (Invitado invitadoLista : club.listarInvitados()) {
                        System.out.println(invitadoLista);
                    }
                    break;

                case 8:
                    // Lista de Facturas
                    if (club.listarFacturas().isEmpty()) {
                        System.out.println("No hay facturas registradas.");
                    } else {
                        System.out.println("Lista de Facturas:");
                        for (Factura facturaLista : club.listarFacturas()) {
                            System.out.println(facturaLista);
                        }
                    }
                    break;

                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("\033[31mOpción no válida. Intente de nuevo.");
            }

            System.out.println();
        } while (opcion != 9);

        scanner.close();
    }

    private static boolean esNumeroValido(String numero) {
        return numero.matches("\\d{10}");
    }
}