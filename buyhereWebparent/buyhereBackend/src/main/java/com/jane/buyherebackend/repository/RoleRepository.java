/**
 * FileName: RoleRepository
 * Author: jane
 * Date: 2023/1/7 17:18
 * Description:
 * Version:
 */

package com.jane.buyherebackend.repository;

import com.jane.buyherecommon.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
