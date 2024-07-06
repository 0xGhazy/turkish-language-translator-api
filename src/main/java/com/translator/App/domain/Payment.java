package com.translator.App.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Payments")
public class Payment {

    @Id
    private String id;
    private String type;
    private String statue;
    private Integer paymentId;
    private Integer paymentProfileId;
    private Boolean isPending;
    private Boolean isSuccess;
    private Integer amountInCents;
    private Integer paidAmountInCents;
    private Integer amountInEGP;
    private String currency;
    private String transactionSourceType;
    private String transactionSourceSubType;
    private Integer integrationId;
    private Integer orderId;
    private LocalDate subscriptionDate;
    private LocalDate subscriptionEndDate;
    private Integer year;
    private User user;
    private long creationTime;
    private long modificationTime;

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", statue='" + statue + '\'' +
                ", paymentId=" + paymentId +
                ", paymentProfileId=" + paymentProfileId +
                ", isPending=" + isPending +
                ", isSuccess=" + isSuccess +
                ", amountInCents=" + amountInCents +
                ", paidAmountInCents=" + paidAmountInCents +
                ", amountInEGP=" + amountInEGP +
                ", currency='" + currency + '\'' +
                ", transactionSourceType='" + transactionSourceType + '\'' +
                ", transactionSourceSubType='" + transactionSourceSubType + '\'' +
                ", integrationId=" + integrationId +
                ", orderId=" + orderId +
                ", subscriptionDate=" + subscriptionDate +
                ", subscriptionEndDate=" + subscriptionEndDate +
                ", year=" + year +
                ", user=" + user +
                ", creationTime='" + creationTime + '\'' +
                ", modificationTime='" + modificationTime + '\'' +
                '}';
    }
}
