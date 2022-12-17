package com.kousenit.astro.services;

import com.kousenit.astro.dao.CraftAndNumberDao;
import com.kousenit.astro.entities.CraftAndNumber;
import com.kousenit.astro.json.AstroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class AstroService {
    private final CraftAndNumberDao dao;
    private final AstroGateway gateway;

    public AstroService(CraftAndNumberDao dao, AstroGateway gateway) {
        this.dao = dao;
        this.gateway = gateway;
    }

    public Result<List<CraftAndNumber>> dailyUpdate() {
        ResponseEntity<AstroResponse> resultEntity = gateway.getAstronautResponse();
        if (resultEntity.getStatusCode().is2xxSuccessful()) {
                List<CraftAndNumber> craftAndNumbers =
                        extractMap(Objects.requireNonNull(resultEntity.getBody())).entrySet().stream()
                                .map(this::getCraftAndNumber)
                                .map(dao::save)
                                .toList();
                return Result.success(craftAndNumbers);
        } else {
            return Result.failure("Unexpected status code: " + resultEntity.getStatusCode());
        }
    }

    private Map<String, Long> extractMap(AstroResponse data) {
        return data.people()
                .stream()
                .collect(Collectors.groupingBy(
                        AstroResponse.Assignment::craft, Collectors.counting()));
    }

    private CraftAndNumber getCraftAndNumber(Map.Entry<String, Long> entry) {
        return new CraftAndNumber(
                LocalDate.now(),
                entry.getKey(),
                entry.getValue().intValue());
    }

    public CraftAndNumber save(CraftAndNumber craftAndNumber) {
        List<CraftAndNumber> crafts = dao.findByDate(craftAndNumber.getDate());
        if (!crafts.contains(craftAndNumber)) {
            return dao.save(craftAndNumber);
        } else {
            return craftAndNumber;
        }
    }

    public List<CraftAndNumber> saveAll(List<CraftAndNumber> craftAndNumbers) {
        return craftAndNumbers.stream()
                .map(this::save)
                .toList();
    }

    public List<CraftAndNumber> findByDate(String date) {
        return dao.findByDate(LocalDate.parse(date));
    }

    public List<CraftAndNumber> findAll() {
        return dao.findAll();
    }

    public void deleteAll() {
        dao.deleteAll();
    }
}
