package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Groups.
 */
@Entity
@Table(name = "groups")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 25)
    @Column(name = "group_name", length = 25)
    private String groupName;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "capacity")
    private Integer capacity;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "now_size")
    private Integer nowSize;

    @OneToMany(mappedBy = "groups")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "payIds", "groups", "infoPaids" }, allowSetters = true)
    private Set<TelegramAccount> accs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Groups id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public Groups groupName(String groupName) {
        this.setGroupName(groupName);
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Groups capacity(Integer capacity) {
        this.setCapacity(capacity);
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getNowSize() {
        return this.nowSize;
    }

    public Groups nowSize(Integer nowSize) {
        this.setNowSize(nowSize);
        return this;
    }

    public void setNowSize(Integer nowSize) {
        this.nowSize = nowSize;
    }

    public Set<TelegramAccount> getAccs() {
        return this.accs;
    }

    public void setAccs(Set<TelegramAccount> telegramAccounts) {
        if (this.accs != null) {
            this.accs.forEach(i -> i.setGroups(null));
        }
        if (telegramAccounts != null) {
            telegramAccounts.forEach(i -> i.setGroups(this));
        }
        this.accs = telegramAccounts;
    }

    public Groups accs(Set<TelegramAccount> telegramAccounts) {
        this.setAccs(telegramAccounts);
        return this;
    }

    public Groups addAcc(TelegramAccount telegramAccount) {
        this.accs.add(telegramAccount);
        telegramAccount.setGroups(this);
        return this;
    }

    public Groups removeAcc(TelegramAccount telegramAccount) {
        this.accs.remove(telegramAccount);
        telegramAccount.setGroups(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Groups)) {
            return false;
        }
        return id != null && id.equals(((Groups) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Groups{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            ", capacity=" + getCapacity() +
            ", nowSize=" + getNowSize() +
            "}";
    }
}
