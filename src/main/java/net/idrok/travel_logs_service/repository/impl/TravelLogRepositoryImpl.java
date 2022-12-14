package net.idrok.travel_logs_service.repository.impl;

import net.idrok.travel_logs_service.domain.TravelLog;
import net.idrok.travel_logs_service.domain.dto.TravelLogCriteria;
import net.idrok.travel_logs_service.domain.dto.TravelsOnDay;
import net.idrok.travel_logs_service.repository.TravelLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TravelLogRepositoryImpl implements TravelLogRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<TravelLog> fullMapper;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TravelLogRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbc).withTableName("travel_log").usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        fullMapper = (rs, rowNum) -> new TravelLog(
                rs.getLong("id"),
                rs.getDate("travel_date").toLocalDate(),
                rs.getString("vehicle_reg_num"),
                rs.getString("vehicle_owner"),
                rs.getInt("starting_odometer"),
                rs.getInt("ending_odometer"),
                rs.getString("travel_route"),
                rs.getString("description")
        );
    }

    @Override
    public List<TravelLog> findAll() {
        String sql = "SELECT * FROM travel_log;";
        return jdbc.query(sql, fullMapper);
    }

    @Override
    public Page<TravelLog> findAll(Pageable pageable) {

        Sort.Order order = !pageable.getSort().isEmpty() ? pageable.getSort().toList().get(0) : Sort.Order.by("id");
        final String sql = "SELECT * FROM travel_log ORDER BY ?  LIMIT ? OFFSET ?";

        List<TravelLog> list = jdbc.query(sql, fullMapper, order.getProperty(), pageable.getPageSize(), pageable.getOffset());

        Long total = jdbc.queryForObject("SELECT count(*) FROM travel_log;", Long.class);
        return new PageImpl<>(list, pageable, total != null ? total : 0);
    }

    @Override
    public Optional<TravelLog> findById(Long id) {
        final String sql = "select * from travel_log where id = ?";
        return Optional.ofNullable(jdbc.queryForObject(sql, fullMapper, id));
    }

    @Override
    public TravelLog create(TravelLog travelLog) {
        Long id = (Long) simpleJdbcInsert.executeAndReturnKey(fromTravelToMap(travelLog));
        return findById(id).orElseThrow(() -> new RuntimeException("could not save or get entity"));
    }

    @Override
    public TravelLog update(TravelLog travelLog) {
        final String sql = "UPDATE travel_log SET " +
                "travel_date = :travel_date ,vehicle_owner = :vehicle_owner, " +
                "travel_route = :travel_route, vehicle_reg_name = :vehicle_reg_name," +
                "starting_odometer = :starting_odometer, " +
                "ending_odometer = :ending_odometer, description = :description" +
                "WHERE id = :id;";


        SqlParameterSource source = new MapSqlParameterSource(fromTravelToMap(travelLog)).addValue("id", travelLog.getId());
        jdbc.update(sql, source);

        return findById(travelLog.getId()).orElseThrow(() -> new RuntimeException("Error occured"));
    }

    @Override
    public void delete(TravelLog travelLog) {
        deleteById(travelLog.getId());
    }

    @Override
    public void deleteById(Long id) {
        final String sql = "DELETE FROM travel_log WHERE id = ?";
        jdbc.update(sql, id);
    }


    private Map<String, Object> fromTravelToMap(TravelLog travelLog) {
        Map<String, Object> param = new HashMap<>();
        param.put("travel_date", travelLog.getTravelDate());
        param.put("vehicle_owner", travelLog.getVehicleOwner());
        param.put("travel_route", travelLog.getTravelRoute());
        param.put("vehicle_reg_num", travelLog.getVehicleRegNum());
        param.put("starting_odometer", travelLog.getStartingOdometer());
        param.put("ending_odometer", travelLog.getEndingOdometer());
        param.put("description", travelLog.getDescription());
        return param;
    }


    @Override
    public List<TravelsOnDay> generateReport(TravelLogCriteria criteria) {
        final StringBuilder sql = new StringBuilder("");

        MapSqlParameterSource source = new MapSqlParameterSource();

        if (criteria.getVehicleRegNum() != null && !criteria.getVehicleRegNum().isEmpty()) {
            sql.append("vehicle_reg_num ILIKE :vehicle_reg_num AND ");
            source.addValue("vehicle_reg_num", "%" + criteria.getVehicleRegNum() + "%");
        }
        if (criteria.getVehicleOwner() != null && !criteria.getVehicleOwner().isEmpty()) {
            sql.append(" vehicle_owner ILIKE :vehicle_owner AND ");
            source.addValue("vehicle_owner", "%" + criteria.getVehicleOwner() + "%");
        }
        if (criteria.getStartDate() != null) {
            source.addValue("start_date", criteria.getStartDate());
            sql.append(" travel_date::date >= :start_date::date AND ");
        }
        if (criteria.getEndDate() != null) {
            source.addValue("end_date", criteria.getEndDate());
            sql.append("travel_date::date <= :end_date::date AND ");
        }

        if(sql.length() > 0){
            // add WHERE keyword before all condition
            sql.insert(0, " WHERE ");
            // remove last AND keyword
            sql.delete(sql.length() - 4, sql.length()-1);
        }
        sql.insert(0,"SELECT *  FROM travel_log " );
        sql.append(" ORDER BY travel_date, starting_odometer;");



        return namedParameterJdbcTemplate.query(sql.toString(), source, fullMapper)
                .stream()
                .collect(Collectors.groupingBy(TravelLog::getTravelDate, Collectors.toList()))
                .entrySet()
                .stream().map(e -> new TravelsOnDay(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

}

