import java.time.LocalDateTime;
import java.util.List;

public class Socio {
    private static int idCounter = 1; 
    private int idRegistro;
    private int idUsuario;
    private double fondosDisponibles;
    private String tipoSuscripcion; 
    private LocalDateTime fechaAfiliacion;
    private boolean aprobado;

    public Socio(int idUsuario, double fondosDisponibles, String tipoSuscripcion) {
        this.idRegistro = idCounter++; 
        this.idUsuario = idUsuario;
        this.fondosDisponibles = fondosDisponibles;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaAfiliacion = LocalDateTime.now();
        this.aprobado = false; // Por defecto no aprobado hasta que el administrador lo apruebe
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public double getFondosDisponibles() {
        return fondosDisponibles;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public LocalDateTime getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void incrementarFondos(double monto) {
        if (this.tipoSuscripcion.equalsIgnoreCase("regular") && (this.fondosDisponibles + monto > 1000000)) {
            System.out.println("Error: El monto máximo para socios regulares es $1,000,000.");
        } else if (this.tipoSuscripcion.equalsIgnoreCase("VIP") && (this.fondosDisponibles + monto > 5000000)) {
            System.out.println("Error: El monto máximo para socios VIP es $5,000,000.");
        } else {
            this.fondosDisponibles += monto;
            System.out.println("Fondos incrementados exitosamente. Fondos disponibles: " + this.fondosDisponibles);
        }
    }

    public void pagarFacturasPendientes(List<Factura> facturas) {
        for (Factura factura : facturas) {
            if (factura.getEstado().equals("sin pagar") && this.fondosDisponibles >= factura.getValorTotal()) {
                this.fondosDisponibles -= factura.getValorTotal();
                factura.pagar();
                System.out.println("Factura " + factura.getIdFactura() + " pagada.");
            }
        }
    }

    public void solicitarCambioAVIP() {
        if (this.tipoSuscripcion.equalsIgnoreCase("VIP")) {
            System.out.println("Ya es socio VIP.");
            return;
        }

        // Lógica de aprobación (simulación)
        if (cumpleRequisitosCambioVIP()) {
            boolean aprobado = simularAprobacionAdministrador(); 

            if (aprobado) {
                this.tipoSuscripcion = "VIP";
                System.out.println("Solicitud de cambio a VIP aprobada.");
            } else {
                System.out.println("Solicitud de cambio a VIP denegada.");
            }
        } else {
            System.out.println("No cumple con los requisitos para el cambio a VIP.");
        }
    }

    private boolean cumpleRequisitosCambioVIP() {
        // Simulación de un criterio simple: debe haber pagado más de $10,000 en facturas
        double totalPagado = 0;
        for (Factura factura : Club.getFacturasBySocio(this.idRegistro)) {
            if (factura.getEstado().equals("pagada")) {
                totalPagado += factura.getValorTotal();
            }
        }
        return totalPagado > 10000;
    }

    private boolean simularAprobacionAdministrador() {
        // Simulación simple de la aprobación del administrador (puede ser más compleja)
        return Math.random() > 0.3; // 70% de probabilidades de ser aprobado
    }

    @Override
    public String toString() {
        return "Socio{" +
                "idRegistro=" + idRegistro +
                ", idUsuario=" + idUsuario +
                ", fondosDisponibles=" + fondosDisponibles +
                ", tipoSuscripcion='" + tipoSuscripcion + '\'' +
                ", fechaAfiliacion=" + fechaAfiliacion +
                ", aprobado=" + aprobado +
                '}';
    }
}
