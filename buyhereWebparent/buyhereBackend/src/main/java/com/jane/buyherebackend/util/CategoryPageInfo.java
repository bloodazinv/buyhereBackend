/**
 * FileName: CategoryPageInfo
 * Author: jane
 * Date: 2023/1/10 17:08
 * Description:
 * Version:
 */

package com.jane.buyherebackend.util;

public class CategoryPageInfo {

    private int totalPages;
    private long totalElements;

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
