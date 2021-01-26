package com.phoenix.nirvana.admin.web.api.dto;

import java.io.Serializable;

public class TestDTO implements Serializable {

    private Long id;

    private Long name;

    public TestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }
}
