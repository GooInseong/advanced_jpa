package com.springboot.advanced_jpa.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryQueryDSLTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDSLTest(){
        QProduct qProduct= QProduct.product;

        List<String> productList=jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .fetch();

        for(String product:productList){
            System.out.println("====================");
            System.out.println("Product Name: "+product);
            System.out.println("====================");

        }
    }
}
