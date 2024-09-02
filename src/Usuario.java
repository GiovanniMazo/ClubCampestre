public class Usuario {
    
  // Id unicos 
    private static int idCounter = 1; 
    private int id;
    private String nombreUsuario;
    private String contrasena;
    
    // Puede ser socio, invitado o administrador
    private String rol; 

    public Usuario(String nombreUsuario, String contrasena, String rol) {
        
    // Asignar ID único y aumentar el contador
        this.id = idCounter++; 
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario='" + nombreUsuario + '\'' + ", rol='" + rol + '\'' + '}';
    }
}