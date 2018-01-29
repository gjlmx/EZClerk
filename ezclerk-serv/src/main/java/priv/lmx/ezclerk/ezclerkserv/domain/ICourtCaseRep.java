package priv.lmx.ezclerk.ezclerkserv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;

import java.util.List;

/**
 * @lmx
 */
public interface ICourtCaseRep extends JpaRepository<CourtCase, Long> {

    CourtCase findByCaseName(String caseName);

}
