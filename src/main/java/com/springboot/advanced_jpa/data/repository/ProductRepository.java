package com.springboot.advanced_jpa.data.repository;


import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 예제 6.7
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*JpaRepository 를 상속받는 것 만으로도 다양한 CRUD 메서드를 제공한다. 하지만 이러한 기본
     * 메서드들은 식별자 기반으로 생성되기 때문에 결국 별도의 메서드를 정의해서 사용하는 경우가 많다.
     * 이때 간단한 쿼리문을 작성하기 위해 사용되는 것이 쿼리메서드 이다.
     * 쿼리메서드는 크게 동작을 결정하는 주제(Subject)와 서술어(Predicate) 로 구분한다.
     * 'find.. By','exists..By' 와 같은 키워드로 쿼리의 주제를 정하며 'by' 는 서술어의 시작을 나타내는
     * 구분자 역할을 한다.*/

    // find by
    Optional<Product> findByName(String name);

    List<Product> findAllByName(String name);

    // exists by
    boolean existsByNumber(Long number);

    // count by
    long countByName(String name);

    // delete by, remove by:리턴타입이 없거나 삭제한 횟수를 리턴
    void deleteByNumber(Long number);

    long removeByName(String name);

    //First<number>, Top<number>: 쿼리를 통해 결과값으 개수를 제한
    List<Product> findFirst5ByName(String name);

    List<Product> findTop10ByName(String name);

    /* 쿼리 메서드의 조건자 키워드 */

    // Is: 값의 일치를 조건으로 사용하는 조건자 키워드. 생략되는 경우가 많으며 Equals 와 동일한 기능
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // (is)Not
    Product findByNumberIsNot(Long number);

    // (is)Null
    List<Product> findByUpdatedAtNull();

    // And,Or
    Product findByNumberAndName(Long number,String name);
    Product findByNumberOrName(long number, String name);

    // (is)GreaterThan,LessThen,Between
    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    // (is)StartsWith,EndingWith,Containing,Like
    List<Product> findByNameLike(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameEndingWith(String name);
}