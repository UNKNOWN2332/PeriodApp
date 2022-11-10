package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.Role;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TelegramAccount} entity.
 */
public class TelegramAccountDTO implements Serializable {

    private Long id;

    private Long chatId;

    private String username;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private Role role;

    private Instant createAt;

    private GroupsDTO groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public GroupsDTO getGroups() {
        return groups;
    }

    public void setGroups(GroupsDTO groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TelegramAccountDTO)) {
            return false;
        }

        TelegramAccountDTO telegramAccountDTO = (TelegramAccountDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, telegramAccountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelegramAccountDTO{" +
            "id=" + getId() +
            ", chatId=" + getChatId() +
            ", username='" + getUsername() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", role='" + getRole() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", groups=" + getGroups() +
            "}";
    }
}
