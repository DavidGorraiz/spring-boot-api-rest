package med.voll.api.domain.paciente;

public record DatosListadoPaciente(Long id,
                                   String nombre,
                                   String telefono,
                                   String documentoidentidad,
                                   String email) {
    public DatosListadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getTelefono(), paciente.getDocumentoidentidad(), paciente.getEmail());
    }
}
