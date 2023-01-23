/**
 * FileName: PagingAndSortingParam
 * Author: jane
 * Date: 2023/1/8 13:48
 * Description:
 * Version:
 */
package com.jane.buyherebackend.paging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PagingAndSortingParam {

    public String moduleURL();

    public String listName();
}
