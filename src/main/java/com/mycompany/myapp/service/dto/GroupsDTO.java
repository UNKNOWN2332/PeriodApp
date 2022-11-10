package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Groups} entity.
 */
public class GroupsDTO implements Serializable {

    private Long id;

    @Size(min = 3, max = 25)
    private String groupName;

    @Min(value = 1)
    @Max(value = 100)
    private Integer capacity;

    @Min(value = 1)
    @Max(value = 100)
    private Integer nowSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getNowSize() {
        return nowSize;
    }

    public void setNowSize(Integer nowSize) {
        this.nowSize = nowSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupsDTO)) {
            return false;
        }

        GroupsDTO groupsDTO = (GroupsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, groupsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupsDTO{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", capacity=" + getCapacity() +
            ", nowSize=" + getNowSize() +
            "}";
    }
}
