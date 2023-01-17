package shop.mtcoding.orange.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//mybatis에서 제공하는 어노테이션
//interface이므로 new가 안됨
//product.xml에서 select를 메서드로 받아들여 하나의 클래스를 만듬
//최소한으로 추상메서드는 만들어줘야함
@Mapper
public interface ProductRepository {
    public List<Product> findAll(); // findAll = select id값

    public Product findOne(int id);

    // -1 DB 에러, 1 변경된 행이 1건, 0 변경된 행이 없다, (프로토콜 약속)
    public int insert(@Param("name") String name, @Param("price") int price,
            @Param("qty") int qty);

    public int delete(@Param("id") int id);

    // 나중에는 클래스를 담아서 클래스로 넘기는게 더 좋다
    public int update(@Param("id") int id, @Param("name") String name, @Param("price") int price,
            @Param("qty") int qty);

}
