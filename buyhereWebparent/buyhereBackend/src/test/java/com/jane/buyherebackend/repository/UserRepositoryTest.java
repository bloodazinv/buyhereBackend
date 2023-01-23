/**
 * FileName: UserRepositoryTest
 * Author: jane
 * Date: 2023/1/8 11:23
 * Description:
 * Version:
 */

package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.Role;
import com.jane.buyherecommon.entity.User;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    //@Test
    public void testCreateUser(){

    }

    //@Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userWithOneRole = new User("jane@gmail.com", "123456", "Jane", "A");
        userWithOneRole.addRole(roleAdmin);

        User savedUser = repo.save(userWithOneRole);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    //@Test
    public void testCreateNewUserWithTwoRoles() {
        User userWithTwoRole = new User("iris@gmail.com", "123456", "Iris", "A");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userWithTwoRole.addRole(roleEditor);
        userWithTwoRole.addRole(roleAssistant);

        User savedUser = repo.save(userWithTwoRole);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
