/**
 * FileName: IMenuService
 * Author: jane
 * Date: 2023/1/19 16:49
 * Description:
 * Version:
 */
package com.jane.buyherefrontend.service.impl;

import com.jane.buyherecommon.entity.article.Article;
import com.jane.buyherecommon.entity.menu.Menu;
import com.jane.buyherecommon.exception.MenuItemNotFoundException;


import java.util.List;

public interface IMenuService {
    public List<Menu> getHeaderMenuItems();
    public List<Menu> getFooterMenuItems();
    public Article getArticleBoundToMenu(String menuAlias) throws MenuItemNotFoundException;
}
