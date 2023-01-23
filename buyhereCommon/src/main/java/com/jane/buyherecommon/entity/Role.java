/**
 * FileName: Role
 * Author: jane
 * Date: 2023/1/7 17:02
 * Description:
 * Version:
 */

package com.jane.buyherecommon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role extends IdBasedEntity {
    private static final long serialVersionUID = 1L;
    @Column(length = 40, nullable = false, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String description;
    public Role(Integer id) {this.id = id;}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString(){return this.name;}

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Role other = (Role) obj;
        if(id == null){
            if (other.id != null) return false;
        } else if(!id.equals(other.id)) return false;
        return true;
    }
}
