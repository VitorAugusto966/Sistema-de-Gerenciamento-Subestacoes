/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package souza.vitor.service;
import souza.vitor.model.SubEstacao;
import souza.vitor.repository.SubEstacaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author vitor
 */

@Service
public class SubEstacaoService {

    private final SubEstacaoRepository repository;

    public SubEstacaoService(SubEstacaoRepository repository) {
        this.repository = repository;
    }

    public List<SubEstacao> listarTodas() {
        return repository.findAll();
    }

    public SubEstacao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SubEstacao salvar(SubEstacao subestacao) {
        return repository.save(subestacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
