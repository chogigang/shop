package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>,
        QuerydslPredicateExecutor<Item>,ItemRepositoryCustom {

    //ItemNm으로 데이터를 조회하시 위해서 By뒤에 필드명인 ItemNM을 메소드의 이름에 붙여줍니다.
List<Item> findByItemNm(String itemNm);

//상품을 상품명과 상품 상세 설명을 OR 조건을 이용하여 조회하는 쿼리 메소드
List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);



//파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 조회하는 쿼리 메소드 입니다.
List<Item>  findByPriceLessThan(Integer price);




//내림차순  OrderBy +속성명+Desc 키둬드를 이용해 데이터의 순서를 처리할수 있다 상품의 가격이 높은 순으로 조회하는 예제
    List<Item>  findByPriceLessThanOrderByPriceDesc(Integer price);



    //@Query 어노테이션을 이용해서 상품 데이터 조회
    //상품 항세설명 을 파라미터로 받아 해당 내용을 상품 상세 설명에 포함하고 있는 데이터를 조회하며 정렬 순서는 가격이 높은 순 조회
    // 파라미터에 @Param 어노테이션을 이용하여 파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정할수 있음
    //현재 itemDetail 변수를 "like % %"사이에 :"itemDetail"로 값이 들어가도록 작성
    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


    //기존의 데이터베이스에서 사용하던 쿼리를 그대로 사용해야 할때@Query의 nativeQuery속성사용
    //단점 데잍어베이스에서 독립적이라는 장점을 잃음
    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);



}


/* <S extends T> save(S entity) //엔티티 저장 및 수정
    void delete(T entity)//엔티티 삭제
    count()//엔티티 총 개수 변환
    Integer<T> findAll()//모든 엔티티 조회
* */