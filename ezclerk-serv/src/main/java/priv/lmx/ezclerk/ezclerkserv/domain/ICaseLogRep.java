package priv.lmx.ezclerk.ezclerkserv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CaseLog;

import java.util.List;

/**
 * @lmx
 */
public interface ICaseLogRep extends JpaRepository<CaseLog,Long> {

    List<CaseLog> findByCaseId(Long caseId);
}
