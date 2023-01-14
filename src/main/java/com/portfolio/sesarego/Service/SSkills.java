package com.portfolio.sesarego.Service;

import com.portfolio.sesarego.Entity.Skills;
import com.portfolio.sesarego.Repository.RSkills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SSkills {

    @Autowired
    RSkills habilidades;

    public List<Skills> list() {
        return habilidades.findAll();
    }

    public Optional<Skills> getOne(int id) {
        return habilidades.findById(id);
    }

    public Optional<Skills> getByNombre(String nombre) {
        return habilidades.findByNombre(nombre);
    }

    public void save(Skills skill) {
        habilidades.save(skill);
    }

    public void delete(int id) {
        habilidades.deleteById(id);
    }

    public boolean existsById(int id) {
        return habilidades.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return habilidades.existsByNombre(nombre);
    }

}
