public class Persona {
    
    // Contador para generar IDs únicos
    private static int idCounter = 1; 
    private int id;
    private String cedula;
    private String nombre;
    private String numeroCelular;

    public Persona(String cedula, String nombre, String numeroCelular) {
        
        // Asignar ID único y aumentar el contador
        this.id = idCounter++; 
        this.cedula = cedula;
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", cedula='" + cedula + '\'' + ", nombre='" + nombre + '\'' + ", numeroCelular='" + numeroCelular + '\'' + '}';
    }
}