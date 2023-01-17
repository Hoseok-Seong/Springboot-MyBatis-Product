package shop.mtcoding.orange.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Entity: 테이블과 똑같이 생긴 모델 만들기
public class Product {
    private int id;
    private String name;
    private int price;
    private int qty;
    private Timestamp createdAt;
}
