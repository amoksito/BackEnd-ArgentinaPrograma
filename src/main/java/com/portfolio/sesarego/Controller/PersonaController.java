package com.portfolio.sesarego.Controller;

import com.portfolio.sesarego.Dto.DtoPersona;
import com.portfolio.sesarego.Entity.Persona;
import com.portfolio.sesarego.Security.Controller.Mensaje;
import com.portfolio.sesarego.Service.ImpPersonaService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
// ("https://frontend-argentinaprogra-58ec1.web.app")
@RequestMapping("/personas")
@RestController
public class PersonaController {

    @Autowired
    ImpPersonaService personaService;
    
    @GetMapping("/traer/perfil")
    public Persona findPersona() {
        return personaService.getOne(1).get();
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody DtoPersona dtopersona) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersona.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());

        personaService.save(persona);

        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }

    // Mensaje Prueba
    // Prueba exitosa desde Postman
    // Para implementar en el FrontEnd debería agregar un campo en la DB.
    @GetMapping("/prueba")
    public String getMensajePrueba() {
        String mensaje = "Esto es una Prueba.";
        return String.format("%s", mensaje);
    }

    // Prueba Redireccionamiento desde el Server
    // Prueba Exitosa, desde desde Postman recibe el HTML completo.
    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "http://www.google.com.ar");
        httpServletResponse.setStatus(302);
    }

}
