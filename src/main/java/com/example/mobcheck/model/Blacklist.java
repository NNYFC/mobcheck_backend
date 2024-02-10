package com.example.mobcheck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "blacklist")
@Data
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String threat;
    private Date add_date;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private Users user_id;

    public Blacklist(String threat, Date add_date, Boolean status, Users user_id) {
        this.threat = threat;
        this.add_date = add_date;
        this.status = status;
        this.user_id = user_id;
    }
}
