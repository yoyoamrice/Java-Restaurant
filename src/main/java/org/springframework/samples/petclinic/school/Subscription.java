package org.springframework.samples.petclinic.school;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.validation.UniqueDomain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@SQLDelete(sql = "UPDATE subscriptions SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Subscription extends NamedEntity {
@Column(name = "description")
@NotEmpty(message = "Please provide a detailed description for this plan.")
private String description;


@Column(name = "monthly_price")
@NotNull
private int monthlyPrice;


@Column(name = "annual_price")
@NotNull
private int annualPrice;


@Column(name = "created_at", insertable = false, updatable = false)
private LocalDateTime createdAt;


@Column(name = "updated_at", insertable = false, updatable = false)
private LocalDateTime updatedAt;


@Column(name = "deleted_at")
private LocalDateTime deletedAt;


}
