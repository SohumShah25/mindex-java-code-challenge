package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * It will save the Compensation Structure in the database
     * @param compensation Compensation Structure of an Employee
     * @return Compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        if (compensation == null) {
            LOG.debug("Please enter Compensation: ");
            return null;
        }

        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * It is fetching Compensation of an Employee from the database
     * @param id EmployeeID
     * @return Compensation
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            LOG.debug("Compensation cannot be found for EmployeeId: " + id);
        }

        return compensation;
    }
}
