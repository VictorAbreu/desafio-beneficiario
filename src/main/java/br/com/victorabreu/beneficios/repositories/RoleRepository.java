package br.com.victorabreu.beneficios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.victorabreu.beneficios.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
