package com.asp_dev.naissances.shared.services;

import com.asp_dev.naissances.shared.entities.Status;
import com.asp_dev.naissances.shared.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class StatusService {
    private final StatusRepository statusRepository;

    public Status search(Map<String, Object> searchCriteriaParams) {


        String name = (String) searchCriteriaParams.get("name");
        return this.statusRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Status inconnu"));

    }
}
