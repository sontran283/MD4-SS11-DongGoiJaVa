package com.ra.model.service;

import com.ra.model.dao.EmployeeDAO;
import com.ra.model.dao.EmployeeDAOImpl;
import com.ra.model.entity.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeDAO.findByName(name);
    }

    @Override
    public List<Employee> sortByName(String name) {
        return employeeDAO.sortByName(name);
    }

    @Override
    public Employee findById(Integer integer) {
        return employeeDAO.findById(integer);
    }

    @Override
    public boolean saveOrUpDate(Employee employee, Integer integer) {
        return employeeDAO.saveOrUpDate(employee, integer);
    }

    @Override
    public void delete(Integer integer) {
        employeeDAO.delete(integer);
    }
}
