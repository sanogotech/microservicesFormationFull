package de.fred4jupiter.fredbet.service.admin;

import de.fred4jupiter.fredbet.domain.SessionTracking;
import de.fred4jupiter.fredbet.repository.SessionTrackingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SessionTrackingService {

    private final SessionTrackingRepository sessionTrackingRepository;

    public SessionTrackingService(SessionTrackingRepository sessionTrackingRepository) {
        this.sessionTrackingRepository = sessionTrackingRepository;
    }

    public void registerLogin(String userName, String sessionId) {
        Optional<SessionTracking> sessionTrackingOpt = sessionTrackingRepository.findById(userName);
        SessionTracking sessionTracking = sessionTrackingOpt.orElseGet(SessionTracking::new);

        sessionTracking.setUserName(userName);
        sessionTracking.setSessionId(sessionId);
        sessionTracking.setLastLogin(LocalDateTime.now());
        sessionTrackingRepository.save(sessionTracking);
    }

    public void registerLogout(String sessionId) {
        SessionTracking sessionTracking = sessionTrackingRepository.findBySessionId(sessionId);
        if (sessionTracking == null) {
            return;
        }

        sessionTrackingRepository.delete(sessionTracking);
    }

    public List<SessionTracking> findLoggedInUsers() {
        return sessionTrackingRepository.findAllByOrderByLastLoginDesc();
    }
}
