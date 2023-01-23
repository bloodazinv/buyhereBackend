/**
 * FileName: BrandRepository
 * Author: jane
 * Date: 2023/1/11 13:34
 * Description:
 * Version:
 */

package com.jane.buyherebackend.repository;

import com.jane.buyherebackend.paging.SearchRepository;
import com.jane.buyherecommon.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends SearchRepository<Brand, Integer> {

    public Long countById(Integer id);

    public Brand findByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
    public Page<Brand> findAll(String keyword, Pageable pageable);

    // NEW -> new anonymous type with only the properties you need
    @Query("SELECT NEW Brand(b.id, b.name) FROM Brand b ORDER BY b.name ASC")
    public List<Brand> findAll();
}
