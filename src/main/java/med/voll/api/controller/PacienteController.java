package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccionP;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosDetalladoPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = repository.save(new Paciente(datosRegistroPaciente));
        DatosDetalladoPaciente datosDetalladoPaciente = new DatosDetalladoPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), new DatosDireccionP(paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanización(), paciente.getDireccion().getCodigopostal(),
                paciente.getDireccion().getProvincia()));
        //        URI url = "http://localhost:8080/pacientes/" + paciente.getId();
        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalladoPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaciente>> listadoMedicos(@PageableDefault(size = 3) Pageable paginacion){
    //    return repository.findAll(paginacion).map(DatosListadoPaciente::new);
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
        Paciente paciente = repository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosDetalladoPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), new DatosDireccionP(paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanización(), paciente.getDireccion().getCodigopostal(),
                paciente.getDireccion().getProvincia())));
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    //DELETE BASE DE DATOS
//   @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarPaciente(@PathVariable Long id) {
//        Paciente paciente = repository.getReferenceById(id);
//        repository.delete(paciente);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalladoPaciente> retornaDatosPaciente(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        var datosPaciente = new DatosDetalladoPaciente(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), new DatosDireccionP(paciente.getDireccion().getDistrito(), paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(), paciente.getDireccion().getUrbanización(), paciente.getDireccion().getCodigopostal(),
                paciente.getDireccion().getProvincia()));
        return ResponseEntity.ok(datosPaciente);
    }
}
