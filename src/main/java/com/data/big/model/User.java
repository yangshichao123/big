package com.data.big.model;


import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String name;
}