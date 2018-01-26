package priv.lmx.ezclerk.ezclerkserv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.lmx.ezclerk.ezclerkserv.domain.entity.CourtCase;

import java.util.List;

/**
 * @lmx
 */
public interface IEzclerkRep extends JpaRepository<CourtCase, Long> {

//    CourtCase getCaseInfobyNum(String caseNum);
//
//    List<CourtCase> getAllCases(Long userId);
//
//    Boolean saveCourtCase(List<CourtCase> cases);

//    Boolean saveOneCourtCase(CourtCase courtCase);
}
