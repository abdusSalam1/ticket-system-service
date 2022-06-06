package com.assignment.helper;

import lombok.Getter;

@Getter
public enum Priority {

    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private int description;

    Priority(int description) {
        this.description = description;
    }
}
