package net.idrok.travel_logs_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.repository.TravelLogRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;



@SpringBootTest
class TravelLogsServiceApplicationTests {

	@Autowired
	private TravelLogRepository repository;




	@Test
	@DisplayName("creating full travel log entity")
	public void createTravelLog() {
		TravelLog log = new TravelLog(null, LocalDate.now(), "70Q754KA", "Shonazarov Muminniyoz", 123430,
				124550, "Toshkent-Jizzax", "migrate money");
		assertNotNull(repository.create(log));
	}


	@Test
	void contextLoads() {


	}
	@Test
	@DisplayName("findAll() method should return list with data")
	public void checkFindAll() {
		createTravelLog();
		List<TravelLog> logs = repository.findAll();
		assertTrue(logs != null && logs.size() > 0);
	}

}
