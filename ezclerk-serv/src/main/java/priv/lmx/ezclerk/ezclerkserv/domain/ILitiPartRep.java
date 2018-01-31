package priv.lmx.ezclerk.ezclerkserv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.LitiPart;

import java.util.List;
import java.util.Set;

/**
 * @lmx
 */
public interface ILitiPartRep extends JpaRepository<LitiPart, Long> {
    List<LitiPart> findByCaseId(Long caseId);
}
