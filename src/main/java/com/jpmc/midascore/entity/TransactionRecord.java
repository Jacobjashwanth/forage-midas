package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;
    private float incentive;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserRecord sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private UserRecord recipient;

    protected TransactionRecord() {}

    public TransactionRecord(float amount, float incentive, UserRecord sender, UserRecord recipient) {
        this.amount = amount;
        this.incentive = incentive;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Long getId() { return id; }
    public float getAmount() { return amount; }
    public float getIncentive() { return incentive; }
    public UserRecord getSender() { return sender; }
    public UserRecord getRecipient() { return recipient; }
}