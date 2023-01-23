/**
 * FileName: MenuRepository
 * Author: jane
 * Date: 2023/1/19 16:50
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.repository;

import com.jane.buyherecommon.entity.menu.Menu;
import com.jane.buyherecommon.entity.menu.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    public List<Menu> findByTypeAndEnabledOrderByPositionAsc(MenuType type, boolean enabled);

    @Query("Select m FROM Menu m WHERE m.alias = ?1 AND m.enabled = true")
    public Menu findByAlias(String alias);

}
