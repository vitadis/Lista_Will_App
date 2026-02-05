package clases;

public class RankingSeccion {

    private String nombreSeccion;
    private String porcentaje;

    public RankingSeccion(String nombreSeccion, double porcentaje) {
        this.nombreSeccion = nombreSeccion;
        this.porcentaje = String.format("%.2f %%", porcentaje);
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public String getPorcentaje() {
        return porcentaje;
    }
}
