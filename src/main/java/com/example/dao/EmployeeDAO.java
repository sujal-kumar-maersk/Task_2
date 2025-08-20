package com.example.dao;

import com.example.model.Employee;
import com.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String INSERT_SQL = "INSERT INTO Employee (id, name, role, reportingTo) VALUES (?, ?, ?::role_enum, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Employee WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE Employee SET name = ?, role = ?::role_enum, reportingTo = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Employee WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Employee";

    //Inserting in table
    public void insert(Employee e) throws SQLException {
        try(Connection conn = DatabaseUtil.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL);){
            ps.setString(1,e.getId());
            ps.setString(2,e.getName());
            ps.setString(3, String.valueOf(e.getRole()));
            ps.setString(4,e.getReportingTo());
            ps.executeUpdate();
        }
    }

    //Retrieving data from table using id
    public Employee getById(String id) throws SQLException{
        try(Connection conn = DatabaseUtil.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);){
            ps.setString(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Employee e = new Employee();
                    e.setId(rs.getString("id"));
                    e.setRole(ROLE.valueOf(rs.getString("role")));
                    e.setName(rs.getString("name"));
                    e.setReportingTo(rs.getString("reportingTo"));
                    return e;
                }
            }
        }
        return null;
    }

    //Retriving all data from table
    public List<Employee> getAll(String id) throws SQLException{
        List<Employee> employees = new ArrayList<>();
        try(Connection conn = DatabaseUtil.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = ps.executeQuery();){
                while(rs.next()){
                    Employee e = new Employee();
                    e.setId(rs.getString("id"));
                    e.setRole(ROLE.valueOf(rs.getString("role")));
                    e.setName(rs.getString("name"));
                    e.setReportingTo(rs.getString("reportingTo"));
                    employees.add(e);
                }
            }
        return employees;
    }

    //Updating table
    public void update(Employee employee) throws SQLException{
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, String.valueOf(employee.getRole()));
            ps.setString(3, employee.getReportingTo());
            ps.setString(4, employee.getId());
            ps.executeUpdate();
        }
    }

    //delete table
    public void delete(String id) throws SQLException{
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
}
