/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package souza.vitor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import souza.vitor.model.SubEstacao;
import org.springframework.stereotype.Repository;
/**
 *
 * @author vitor
 */

@Repository
public interface SubEstacaoRepository extends JpaRepository<SubEstacao, Long> {
}

