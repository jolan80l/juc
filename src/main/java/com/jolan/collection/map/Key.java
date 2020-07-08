package com.jolan.collection.map;

import java.util.Objects;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/8 0008 12:44
 */
public class Key {
    private Integer id;
    private String name;

    public Key(Integer id, String name) {
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

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(id, key.id) &&
                Objects.equals(name, key.name);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
