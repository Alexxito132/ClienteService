package mx.edu.utez.gestionclientes.ClientesGestion.Service;

import mx.edu.utez.gestionclientes.ClientesGestion.Dto.ClienteDto;
import mx.edu.utez.gestionclientes.ClientesGestion.Model.Cliente;
import mx.edu.utez.gestionclientes.ClientesGestion.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Map<String, Object> crearCliente(ClienteDto clienteDto){
        Map<String, Object> respuesta = new HashMap<>();
        if(clienteRepository.existsClienteByEmail(clienteDto.getEmail())){
            respuesta.put("mensaje", "No se ha podido crear el cliente. Este email ya existe!");
            return respuesta;
        }
        if(clienteDto.getEmail().contains("@") && clienteDto.getEmail().contains(".")){
            respuesta.put("mensaje", "No se ha podido crear el cliente. El formato del email no es valido!");
            return respuesta;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setEmail(clienteDto.getEmail());

        clienteRepository.save(cliente);

        respuesta.put("mensaje", "El cliente se ha creado correctamente");
        respuesta.put("Cliente creado: ", cliente);
        return respuesta;
    }

    public Map<String, Object> obtenerClienteById(Integer id){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            respuesta.put("mensaje", "Se ha encontrado el cliente");
            respuesta.put("Cliente:", cliente.get());
            return respuesta;
        } else {
            respuesta.put("mensaje", "No se ha encontrado el cliente con el id " + id);
            return respuesta;
        }
    }

    public Map<String, Object> actualizarCliente(Integer id, ClienteDto clienteDto){
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            Cliente clienteActualizar = new Cliente();
            clienteActualizar.setNombre(clienteDto.getNombre());
            clienteActualizar.setTelefono(clienteDto.getTelefono());
            clienteActualizar.setEmail(clienteDto.getEmail());

            clienteRepository.save(clienteActualizar);
            respuesta.put("mensaje", "Se ha actualizado el cliente");
            respuesta.put("Cliente:", clienteActualizar);
            return respuesta;
        }
        respuesta.put("mensaje", "No se ha encontrado el cliente con el id " + id);
        return respuesta;
    }
}
