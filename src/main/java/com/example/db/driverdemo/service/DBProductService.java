package com.example.db.driverdemo.service;

import java.util.UUID;
import java.time.Clock;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBProductService {
    
    @Autowired
    private DBConfiguration dbConfiguration;

    public Product getProductById(String id) {

        Product product = new Product();
        CqlSession cqlSession = getCQlSession();
        ResultSet rs = cqlSession.execute("select id, productname, description, price, created from sample.rest_example_products where id = " + id );
        
        Row row = rs.one();
        
        product.setProductname(row.getString("productname"));
        product.setId(row.getUuid("id").toString());
        product.setDescription(row.getString("description"));
        product.setPrice(row.getBigDecimal("price"));
        product.setCreated(row.getInstant("created"));
        
        cqlSession.close();

        return product;
    }

    public Status deleteProductById(String id) {
        Status status = new Status(Status.SUCCESS);

        try {
            CqlSession cqlSession = getCQlSession();
            ResultSet rs = cqlSession.execute("delete from sample.rest_example_products where id = " + id );
            
            if(!rs.wasApplied())
                status = new Status(Status.FAIL);

            cqlSession.close();

        } catch (Exception e) {
            e.printStackTrace();   
            status = new Status(Status.ERROR);
        }
        
        return status;
    }

    public Status updateProduct(Product product) {
        Status status = new Status(Status.SUCCESS);

        try {

            String query = "UPDATE sample.rest_example_products set "; 

            if(product.getProductname() != null || !"".equals(product.getProductname().trim())) {
                query = query + " productname = '" + product.getProductname() + "'" + " , ";
            }

            if(product.getDescription() != null || !"".equals(product.getDescription().trim())) {
                query = query + " description = '" + product.getDescription() + "'" + " , ";
            }
            
            if(product.getPrice() != null) {
                query = query + " price = '" + product.getPrice() + "'" + " , ";
            }

            query = query.substring(0, query.length() - 3);
            
            query = query + " WHERE id = " + product.getId();

            System.out.println(query);

            CqlSession cqlSession = getCQlSession();
            ResultSet rs = cqlSession.execute(query);

            if(!rs.wasApplied())
                status = new Status(Status.FAIL);
            
            cqlSession.close();

        } catch (Exception e) {
            e.printStackTrace();
            status = new Status(Status.ERROR);    
        }

        
        return status;
    }

    public Key addProduct(Product newProduct) {
        UUID generatedUUID = UUID.randomUUID();
        newProduct.setId(generatedUUID.toString());

        Clock currentTime = Clock.systemDefaultZone();
        newProduct.setCreated(currentTime.instant());
        
        CqlSession cqlSession = getCQlSession();
        PreparedStatement pstmt = cqlSession.prepare("INSERT INTO sample.rest_example_products (id, productname, description, price, created) values(:id, :name, :description, :price , :created);");
        BoundStatement bound = pstmt.bind()
        .setUuid("id", generatedUUID)
        .setString("name", newProduct.getProductname())
        .setString("description", newProduct.getDescription())
        .setBigDecimal("price", newProduct.getPrice())
        .setInstant("created", newProduct.getCreated());

        cqlSession.execute(bound);

        cqlSession.close();

        return new Key(newProduct.getId());
    }

    private CqlSession getCQlSession(){
        return CqlSession.builder()
               .withCloudSecureConnectBundle(dbConfiguration.getSecureConnectBundle().toPath())
               .withAuthCredentials(dbConfiguration.getClientId(), dbConfiguration.getClientSecret())
               .build();
    }
}



