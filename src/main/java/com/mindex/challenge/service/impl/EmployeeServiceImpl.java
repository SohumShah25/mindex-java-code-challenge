package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindex.challenge.data.ReportingStructure;

import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Employee cannot be found");
        }

        return employee;
    }

    /**
     * This method is used to update the Employee Structure
     * @param employee is the current Employee
     * @return It is updating the employee in the Employee Database
     */
    @Override
    public Employee update(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Creating a Reporting structure for an employee
     * @param id Employee Id
     * @return Reporting structure
     */
    @Override
    public ReportingStructure report(String id) {

        if (id == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        LOG.debug("Creating reporting structure for employee with id [{}]", id);

        Employee employee = read(id);
        int numberOfReports = getNumberOfReports(employee);

        return new ReportingStructure(employee, numberOfReports);
    }



    /**
     * Method to dynamically calculate the count of immediate subordinates for an employee
     * @param employee Current Employee whose direct reports are to be found
     * @return Number of Reports
     */

    private int getNumberOfReports(Employee employee) {
        Stack<Employee> stack = new Stack<>();
        stack.push(employee);
        int reportSum = 0;

        while (!stack.isEmpty()) {
            Employee currentEmployee = stack.pop();
            List<Employee> currentReports = currentEmployee.getDirectReports();

            if (currentReports != null && !currentReports.isEmpty()) {
                reportSum += currentReports.size();

                for (Employee e : currentReports) {
                    Employee foundEmployee = read(e.getEmployeeId());
                    stack.push(foundEmployee);
                }
            }
        }
        return reportSum;
    }
}
