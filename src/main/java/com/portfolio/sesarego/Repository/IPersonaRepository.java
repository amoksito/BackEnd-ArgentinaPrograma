package com.portfolio.sesarego.Repository;

import com.portfolio.sesarego.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// gracias a esta capa DAO los objetos java de tipo Persona
// se transformarán en registros en una tabla.
@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {
    public Optional<Persona> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
