package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionP {

    private String numero;
    private String complemento;
    private String ciudad;
    private String distrito;
    private String urbanización;
    private String codigopostal;
    private String provincia;

    public DireccionP(DatosDireccionP direccion) {
        this.numero = direccion.numero();
        this.distrito = direccion.distrito();
        this.complemento = direccion.complemento();
        this.ciudad = direccion.ciudad();
        this.urbanización = direccion.urbanización();
        this.codigopostal = direccion.codigopostal();
        this.provincia = direccion.provincia();
    }

    public DireccionP actualizarDatos(DatosDireccionP direccion) {
        this.numero = direccion.numero();
        this.distrito = direccion.distrito();
        this.complemento = direccion.complemento();
        this.ciudad = direccion.ciudad();
        this.urbanización = direccion.urbanización();
        this.codigopostal = direccion.codigopostal();
        this.provincia = direccion.provincia();
        return this;
    }
}
