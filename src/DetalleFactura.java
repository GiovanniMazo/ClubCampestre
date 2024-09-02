public class DetalleFactura {

    // Id unco 
    private static int idCounter = 1;
    private int idRegistro;
    private int idFactura;
    private int numeroItem;
    private String concepto;
    private double valorItem;

    public DetalleFactura(int idFactura, int numeroItem, String concepto, double valorItem) {
        this.idRegistro = idCounter++; 
        this.idFactura = idFactura;
        this.numeroItem = numeroItem;
        this.concepto = concepto;
        this.valorItem = valorItem;
    }

    
    public int getIdRegistro() {
        return idRegistro;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getNumeroItem() {
        return numeroItem;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getValorItem() {
        return valorItem;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "idRegistro=" + idRegistro + ", idFactura=" + idFactura + ", numeroItem=" + numeroItem + ", concepto='" + concepto + '\'' + ", valorItem=" + valorItem + '}';
    }
}