package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccionP(

        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        String urbanización,
        @NotBlank
        String codigopostal,
        @NotBlank
        String provincia) {
}
