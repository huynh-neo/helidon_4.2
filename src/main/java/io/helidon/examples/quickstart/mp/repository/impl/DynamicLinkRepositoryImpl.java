package io.helidon.examples.quickstart.mp.repository.impl;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import io.helidon.examples.quickstart.mp.model.DynamicLink;
import io.helidon.examples.quickstart.mp.repository.DynamicLinkRepository;
import io.helidon.service.registry.Service.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

@ApplicationScoped
public class DynamicLinkRepositoryImpl implements DynamicLinkRepository {
    @Inject
    private DataSource dataSource;

    // @PostConstruct
    // public void init() {
    //     try (Connection conn = dataSource.getConnection();
    //          Statement stmt = conn.createStatement()) {
    //         stmt.executeUpdate("CREATE TABLE IF NOT EXISTS dynamic_links (" +
    //                 "id SERIAL PRIMARY KEY," +
    //                 "url TEXT NOT NULL," +
    //                 "description TEXT)");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public DynamicLink create(DynamicLink link) {
        String sql = "INSERT INTO dynamic_links (url, description) VALUES (?, ?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, link.getUrl());
            ps.setString(2, link.getDescription());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                link.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return link;
    }

    public List<DynamicLink> findAll() {
        List<DynamicLink> links = new ArrayList<>();
        String sql = "SELECT id, url, description FROM dynamic_links";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                links.add(new DynamicLink(
                        rs.getLong("id"),
                        rs.getString("url"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }
}
