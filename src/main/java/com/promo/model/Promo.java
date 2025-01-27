package com.promo.model;

import com.promo.validator.ValidEndDate;
import com.promo.validator.ValidStartDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "promo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidStartDate(message = "Invalid voucher start time: Start Date must be after today")
@ValidEndDate(message = "Invalid voucher end time: End Date must be after Start Date")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String title;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate  endDate;

    @NotNull
    @Min(10001)
    private double discount;

    @NotNull
    private String description;
}
