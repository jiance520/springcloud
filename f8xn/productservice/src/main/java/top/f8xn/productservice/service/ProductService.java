package top.f8xn.productservice.service;

import top.f8xn.productservice.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);


}
