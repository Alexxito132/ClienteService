package mx.edu.utez.gestionclientes.ClientesGestion.Controller;

import mx.edu.utez.gestionclientes.ClientesGestion.Dto.ClienteDto;
import mx.edu.utez.gestionclientes.ClientesGestion.Service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("")
    public ResponseEntity<Object> crearCliente(@RequestBody ClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.crearCliente(clienteDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarClientePorId(@PathVariable Integer id){
        return new ResponseEntity<>(clienteService.obtenerClienteById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.actualizarCliente(id, clienteDto), HttpStatus.OK);
    }
}
