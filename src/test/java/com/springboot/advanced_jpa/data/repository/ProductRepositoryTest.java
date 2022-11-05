package com.springboot.advanced_jpa.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;

import com.springboot.advanced_jpa.data.entity.QProduct;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest(){
        Product product1= new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2= new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3=new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1= productRepository.save(product1);
        Product savedProduct2= productRepository.save(product2);
        Product savedProduct3= productRepository.save(product3);


        productRepository.findByName("펜", Sort.by(Sort.Order.asc("price")));
        productRepository.findByName("펜", Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock")));

        //페이징 쿼리를 호출하는 방법
        Page<Product> productPage=productRepository.findByName("펜", PageRequest.of(0,2));
        //page: 페이지 번호.
        //size: 페이지당 데이터 개수.
        System.out.println(productPage.getContent());
    }
    
    /*QueryDSL 사용: pom.xml 에 의존성과 plugin 설정후 compile 함.
    * QueryDSL 은 지금껏 사용하였던 엔티티클레스와 Q도메인(target/generated-sources)이라는
    * 쿼리 타입의 클래스를 자체적으로 생성하여 메타데이터로 사용함*/
    @PersistenceContext
    EntityManager em;

    @Test
    void queryDslTest(){
        /* QueryDSL 은 JpaRepository 가 아닌 EntityManager 를 통해 JPAQuery 객체를 생성해 사용한다.*/
        JPAQuery<Product> query= new JPAQuery<>(em);
        QProduct qProduct= QProduct.product;

        List<Product> productList= query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product:productList){
            System.out.println("=================================");
            System.out.println();
            System.out.println("Product Number:" +product.getNumber());
            System.out.println("Product Name:" +product.getName());
            System.out.println("Product Price:" +product.getPrice());
            System.out.println("Product Stock:" +product.getStock());
            System.out.println();
            System.out.println("=================================");
        }
    }

    /*JPaQueryFactory 를 활용한 QueryDSL*/
    @Test
    void queryDslTest2(){
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        QProduct qProduct=QProduct.product;

        List<Product> productList=jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product: productList){
            System.out.println("=================================");
            System.out.println();
            System.out.println("Product Number:" +product.getNumber());
            System.out.println("Product Name:" +product.getName());
            System.out.println("Product Price:" +product.getPrice());
            System.out.println("Product Stock:" +product.getStock());
            System.out.println();
            System.out.println("=================================");
        }
    }
}
