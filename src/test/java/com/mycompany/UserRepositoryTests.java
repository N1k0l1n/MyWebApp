package com.mycompany;

import com.mycompany.repository.UserRepository;
import com.mycompany.user.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired private UserRepository repo;

// testing adding new user into db
    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("reni.kumar@gmail.com");
        user.setFirstName("reni123456");
        user.setFirstName("reni");
        user.setLastName("kumar");

       User savedUser = repo.save(user);


       //testing api

        MatcherAssert.assertThat(savedUser , is(IsNull.notNullValue()));
        MatcherAssert.assertThat(savedUser.getId(), greaterThan(0));
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        MatcherAssert.assertThat(users ,iterableWithSize(0));

       for(User user:users){
           System.out.println(user);
       }
    }


    @Test
    public void testUpdate(){
        Integer userId=1;
        Optional<User> optionalUser =repo.findById(userId);
        repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        MatcherAssert.assertThat(updatedUser.getPassword(), equalToIgnoringWhiteSpace("hello2000"));
    }


    @Test
    public void testGet(){
        Integer userId=2;
        Optional<User> optionalUser =repo.findById(userId);
        MatcherAssert.assertThat(optionalUser.isPresent(), anything());
        System.out.println(optionalUser.get());

    }

    @Test
    public void testDelete(){
        Integer userId=2;
        repo.deleteById(userId);


        Optional<User> optionalUser =repo.findById(userId);
        MatcherAssert.assertThat(optionalUser.isEmpty(), anything());


    }

}
