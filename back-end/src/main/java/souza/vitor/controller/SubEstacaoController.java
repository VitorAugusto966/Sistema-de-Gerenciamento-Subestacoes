/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package souza.vitor.controller;

import souza.vitor.model.RedeMT;
import souza.vitor.model.SubEstacao;
import souza.vitor.service.SubEstacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 *
 * @author vitor
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/subEstacao")
public class SubEstacaoController {

    private final SubEstacaoService service;

    public SubEstacaoController(SubEstacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubEstacao> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public SubEstacao buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public SubEstacao criar(@RequestBody SubEstacao subEstacao) {
        if (subEstacao.getRedesMT() != null) {
            for (RedeMT rede : subEstacao.getRedesMT()) {
                rede.setSubestacao(subEstacao);
            }
        }
        return service.salvar(subEstacao);
    }

    @PutMapping("/{id}")
    public SubEstacao atualizar(@PathVariable Long id, @RequestBody SubEstacao subEstacaoAtualizada) {
        SubEstacao existente = service.buscarPorId(id);
    
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subestação não encontrada");
        }
    
        existente.setCodigo(subEstacaoAtualizada.getCodigo());
        existente.setNome(subEstacaoAtualizada.getNome());
        existente.setLatitude(subEstacaoAtualizada.getLatitude());
        existente.setLongitude(subEstacaoAtualizada.getLongitude());
    
        if (subEstacaoAtualizada.getRedesMT() != null) {
            for (RedeMT rede : subEstacaoAtualizada.getRedesMT()) {
                rede.setSubestacao(existente);
            }
    
            existente.getRedesMT().removeIf(rede -> !subEstacaoAtualizada.getRedesMT().contains(rede));
    
            for (RedeMT rede : subEstacaoAtualizada.getRedesMT()) {
                if (!existente.getRedesMT().contains(rede)) {
                    existente.getRedesMT().add(rede);
                }
            }
        }
    
        return service.salvar(existente);
    }
    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
