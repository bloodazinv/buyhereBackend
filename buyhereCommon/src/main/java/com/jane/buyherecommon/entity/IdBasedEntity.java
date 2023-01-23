/**
 * FileName: IdBasedEntity
 * Author: jane
 * Date: 2023/1/7 17:04
 * Description:
 * Version:
 */

package com.jane.buyherecommon.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class IdBasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
}
