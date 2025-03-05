package edu.demosql.first.repository;

import edu.demosql.first.repository.dao.PcDaoImpl;
import edu.demosql.first.repository.dao.ProductDaoImpl;
import edu.demosql.first.repository.domain.Pc;
import edu.demosql.first.repository.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class DemoRun {
    public static void main(String[] args) throws SQLException {

        ProductDaoImpl productDao1 = new ProductDaoImpl();
        productDao1.dropTableProduct();

        ProductDaoImpl productDao2 = new ProductDaoImpl();
        productDao2.createTableProduct();

        Product product3 = new Product("X", 444, "NewPhone1");
        ProductDaoImpl productDao3 = new ProductDaoImpl();
        productDao3.saveProduct(product3);

        Product product4 = new Product("Y", 555, "NewPhone2");
        ProductDaoImpl productDao4 = new ProductDaoImpl();
        productDao4.saveProduct(product4);

        Product product5 = new Product("Z", 777, "NewPhone3");
        ProductDaoImpl productDao5 = new ProductDaoImpl();
        productDao5.saveProduct(product5);

        ProductDaoImpl productDao6 = new ProductDaoImpl();
        productDao6.deleteOneProduct(2L);

        ProductDaoImpl productDao7 = new ProductDaoImpl();
        List<Product> listProducts = productDao7.getAllProduct();
        for (Product prod : listProducts) {
            System.out.println(prod.toString());
        }



        PcDaoImpl pcDao1 = new PcDaoImpl();
        pcDao1.dropTablePc();

        PcDaoImpl pcDao2 = new PcDaoImpl();
        pcDao2.createTablePc();

        Pc pc3 = new Pc(1232,500,64, 5.0F, "12x", 600.00F);
        PcDaoImpl pcDao3 = new PcDaoImpl();
        pcDao3.saveOnePc(pc3);

        Pc pc4 = new Pc(1121,750,128, 14.0F, "40x", 850.00F);
        PcDaoImpl pcDao4 = new PcDaoImpl();
        pcDao4.saveOnePc(pc4);

        Pc pc5 = new Pc(1233,500,64, 5.0F, "12x", 600.00F);
        PcDaoImpl pcDao5 = new PcDaoImpl();
        pcDao5.saveOnePc(pc5);

        PcDaoImpl pcDao6 = new PcDaoImpl();
        pcDao6.deleteOnePc(2L);

        PcDaoImpl pcDao7 = new PcDaoImpl();
        List<Pc> listPcs = pcDao7.getAllPc();
        for (Pc pc : listPcs) {
            System.out.println(pc.toString());
        }
    }
}
