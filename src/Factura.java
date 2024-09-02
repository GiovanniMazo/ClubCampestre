import java.time.LocalDateTime;

public class Factura {
    // Contador para generar IDs únicos
    private static int idCounter = 1; 
    private int idFactura;
    private int idPersona;
    private int idSocio;
    private LocalDateTime fechaGeneracion;
    private double valorTotal;
    private String estado; // pagads o sin pagar

    public Factura(int idPersona, int idSocio, double valorTotal) {
        this.idFactura = idCounter++;
        this.idPersona = idPersona;
        this.idSocio = idSocio;
        this.fechaGeneracion = LocalDateTime.now();
        this.valorTotal = valorTotal;
        this.estado = "sin pagar";
    }

    // Getters
    public int getIdFactura() {
        return idFactura;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void pagar() {
        this.estado = "pagada";
    }

    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", idPersona=" + idPersona + ", idSocio=" + idSocio + ", fechaGeneracion=" + fechaGeneracion + ", valorTotal=" + valorTotal + ", estado='" + estado + '\'' + '}';
    }
}