package com.niit.AuthService.repository;

import com.niit.AuthService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
/*

@Entity
class Product {
@Id
int id;
String name;
}

public interface ProductRepo extends JpaRepository<Product, int>{}*/