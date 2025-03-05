package edu.demosql.first.repository.repository;

import edu.demosql.first.repository.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setMaker(rs.getString("maker"));
        product.setModel(rs.getInt("model"));
        product.setType(rs.getString("type"));
        return product;
    };

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper, id);
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }



}
