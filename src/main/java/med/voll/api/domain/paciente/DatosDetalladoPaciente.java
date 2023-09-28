package med.voll.api.domain.paciente;


import med.voll.api.domain.direccion.DatosDireccionP;

public record DatosDetalladoPaciente(Long id, String nombre, String email, String telefono,
                                     DatosDireccionP direccion) {

}
