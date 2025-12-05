package mx.edu.utez.gestionclientes.ClientesGestion.Repository;

import mx.edu.utez.gestionclientes.ClientesGestion.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsClienteByEmail(String email);
}
