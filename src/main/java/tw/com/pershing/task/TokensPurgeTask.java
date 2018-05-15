package tw.com.pershing.task;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import tw.com.pershing.repository.VerificationTokenRepository;

public class TokensPurgeTask {
	@Autowired
	VerificationTokenRepository tokenRepository;

	@Scheduled(cron = "${purge.cron.expression}")
	public void purgeExpired() {

		Date now = Date.from(Instant.now());

		tokenRepository.deleteAllExpiredSince(now);
	}
}
