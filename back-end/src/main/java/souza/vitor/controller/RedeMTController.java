package souza.vitor.controller;
import souza.vitor.model.RedeMT;
import souza.vitor.service.RedeMTService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author vitor
 */

@RestController
@RequestMapping("/redesMT")
public class RedeMTController {

    private final RedeMTService service;

    public RedeMTController(RedeMTService service) {
        this.service = service;
    }

    @GetMapping
    public List<RedeMT> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public RedeMT buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public RedeMT criar(@RequestBody RedeMT redeMT) {
        return service.salvar(redeMT);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
