/**
 * FileName: CategoryDTO
 * Author: jane
 * Date: 2023/1/10 17:10
 * Description:
 * Version:
 */

package com.jane.buyherebackend.dto;

public class CategoryDTO {
    private Integer id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
