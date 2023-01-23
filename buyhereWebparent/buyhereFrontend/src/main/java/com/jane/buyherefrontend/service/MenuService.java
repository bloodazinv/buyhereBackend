/**
 * FileName: MenuService
 * Author: jane
 * Date: 2023/1/19 16:49
 * Description:
 * Version:
 */

package com.jane.buyherefrontend.service;

import com.jane.buyherecommon.entity.article.Article;
import com.jane.buyherecommon.entity.menu.*;
import com.jane.buyherecommon.entity.menu.Menu;
import com.jane.buyherecommon.exception.MenuItemNotFoundException;
import com.jane.buyherefrontend.repository.MenuRepository;
import com.jane.buyherefrontend.service.impl.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private MenuRepository repo;

    @Override
    public List<Menu> getHeaderMenuItems() {
        // TODO Auto-generated method stub
        return repo.findByTypeAndEnabledOrderByPositionAsc(MenuType.HEADER, true);
    }

    @Override
    public List<Menu> getFooterMenuItems() {
        // TODO Auto-generated method stub
        return repo.findByTypeAndEnabledOrderByPositionAsc(MenuType.FOOTER, true);
    }

    @Override
    public Article getArticleBoundToMenu(String menuAlias) throws MenuItemNotFoundException {
        // TODO Auto-generated method stub
        Menu menu = repo.findByAlias(menuAlias);
        if (menu == null) {
            throw new MenuItemNotFoundException("No menu found with alias " + menuAlias);
        }

        return menu.getArticle();
    }

}

