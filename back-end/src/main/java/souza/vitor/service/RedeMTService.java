/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package souza.vitor.service;
import souza.vitor.model.RedeMT;
import souza.vitor.repository.RedeMTRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author vitor
 */


@Service
public class RedeMTService {

    private final RedeMTRepository repository;

    public RedeMTService(RedeMTRepository repository) {
        this.repository = repository;
    }

    public List<RedeMT> listarTodas() {
        return repository.findAll();
    }

    public RedeMT buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RedeMT salvar(RedeMT redeMT) {
        return repository.save(redeMT);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
