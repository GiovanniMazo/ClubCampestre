public class Invitado {
    // Contador para generar IDs únicos
    private static int idCounter = 1; 
    private int idRegistro;
    private int idUsuario;
    private int idSocioInvitador;
    // "activa" o "inactiva"
    private String estado; 

    public Invitado(int idUsuario, int idSocioInvitador) {
        // Asignar ID único y aumentar el contador
        this.idRegistro = idCounter++; 
        this.idUsuario = idUsuario;
        this.idSocioInvitador = idSocioInvitador;
        this.estado = "activa";
    }

    // Getters
    public int getIdRegistro() {
        return idRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdSocioInvitador() {
        return idSocioInvitador;
    }

    public String getEstado() {
        return estado;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Invitado{" + "idRegistro=" + idRegistro + ", idUsuario=" + idUsuario + ", idSocioInvitador=" + idSocioInvitador + ", estado='" + estado + '\'' + '}';
    }
}