package com.chunlei.account.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Account {
    private Long id;

    private String name;
    private Date lastSeen;
    @Valid
    private List<Item> incomes;
    @Valid
    private List<Item> expenses;
    @NotNull
    @Valid
    private Saving saving;
    private String note;
}
