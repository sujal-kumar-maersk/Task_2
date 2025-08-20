package com.example.dao;

import com.example.model.EmployeeDetail;
import com.example.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailDAO {
    private static final String INSERT_SQL = "INSERT INTO EmployeeDetail (id, age, experienceYears, departmentName, project) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM EmployeeDetail WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE EmployeeDetail SET age = ?, experienceYears = ?, departmentName = ?, project = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM EmployeeDetail WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM EmployeeDetail";

    public void create(EmployeeDetail detail) throws SQLException {
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, detail.getId());
            ps.setInt(2, detail.getAge());
            ps.setInt(3, detail.getExperienceYears());
            ps.setString(4, detail.getDepartmentName());
            ps.setString(5, detail.getProject());
            ps.executeUpdate();
        }
    }

    public EmployeeDetail getById(String id) throws SQLException {
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EmployeeDetail detail = new EmployeeDetail();
                    detail.setId(rs.getString("id"));
                    detail.setAge(rs.getInt("age"));
                    detail.setExperienceYears(rs.getInt("experienceYears"));
                    detail.setDepartmentName(rs.getString("departmentName"));
                    detail.setProject(rs.getString("project"));
                    return detail;
                }
            }
        }
        return null;
    }

    public List<EmployeeDetail> getAll() throws SQLException {
        List<EmployeeDetail> details = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                EmployeeDetail detail = new EmployeeDetail();
                detail.setId(rs.getString("id"));
                detail.setAge(rs.getInt("age"));
                detail.setExperienceYears(rs.getInt("experienceYears"));
                detail.setDepartmentName(rs.getString("departmentName"));
                detail.setProject(rs.getString("project"));
                details.add(detail);
            }
        }
        return details;
    }

    public void update(EmployeeDetail detail) throws SQLException {
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setInt(1, detail.getAge());
            ps.setInt(2, detail.getExperienceYears());
            ps.setString(3, detail.getDepartmentName());
            ps.setString(4, detail.getProject());
            ps.setString(5, detail.getId());
            ps.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        try (Connection conn = DatabaseUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
}
