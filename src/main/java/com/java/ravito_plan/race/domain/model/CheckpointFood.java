package com.java.ravito_plan.race.domain.model;

import com.java.ravito_plan.race.domain.exception.CheckpointFoodQuantityIsNegativeException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckpointFood {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "checkpoint_id")
    private Checkpoint checkpoint;

    @Column(nullable = false)
    private Long foodId;

    @Column(nullable = false)
    private int quantity = 1;

    public CheckpointFood(Checkpoint checkpoint, int quantity, Long foodId) {
        this.checkpoint = checkpoint;
        this.setQuantity(quantity);
        this.foodId = foodId;
    }

    public CheckpointFood setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new CheckpointFoodQuantityIsNegativeException(foodId, quantity);
        }
        this.quantity = quantity;
        return this;
    }
}
