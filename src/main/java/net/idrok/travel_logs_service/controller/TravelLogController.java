package net.idrok.travel_logs_service.controller;


import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.domain.dto.TravelLogCriteria;
import net.idrok.travel_logs_service.domain.dto.TravelReportInPeriod;
import net.idrok.travel_logs_service.service.TravelLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/travel_log")
@Validated
public class TravelLogController {
    private final TravelLogService service;

    public TravelLogController(TravelLogService service) {
        this.service = service;
    }

    @GetMapping("/report")
    public ResponseEntity<TravelReportInPeriod> report(@Valid TravelLogCriteria criteria){
        return ResponseEntity.ok(service.generateReport(criteria));
    }
    @GetMapping()
    public ResponseEntity<Page<TravelLog>> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TravelLog> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<TravelLog> create(@RequestBody TravelLog travelLog){
        return ResponseEntity.ok(service.create(travelLog));
    }
    @PutMapping
    public ResponseEntity<TravelLog> update(@RequestBody TravelLog travelLog){
        return ResponseEntity.ok(service.update(travelLog));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
