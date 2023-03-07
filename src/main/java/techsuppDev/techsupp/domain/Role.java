package techsuppDev.techsupp.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
//implements Serializable
public enum Role implements Serializable {
    ROLE_USER,
    ROLE_ADMIN
}
