package com.translator.App.service;

import com.translator.App.domain.Payment;
import com.translator.App.domain.User;
import com.translator.App.exception.ResourceNotFound;
import com.translator.App.repository.PaymentRepository;
import com.translator.App.repository.UserRepository;
import com.translator.App.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LogManager.getLogger(PaymentService.class);

    public Response _insertPayment(HashMap<String, Object> paymobResponse) {
        Payment payment = new Payment();
        String method = "_insertPayment";
        String action;

        action = "extract data from Paymob map";
        String type = (String) paymobResponse.get("type");
        payment.setType(type);
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "extract transaction data";
        HashMap<String, Object> paymentObject = (HashMap<String, Object>)  paymobResponse.get("obj");
        Integer paymentId = (Integer) paymentObject.get("id");
        Boolean isPending = (Boolean) paymentObject.get("pending");
        Boolean isSuccess = (Boolean) paymentObject.get("success");
        Integer integrationId = (Integer) paymentObject.get("integration_id");
        Integer profileId = (Integer) paymentObject.get("profile_id");
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "set transaction data";
        payment.setPaymentId(paymentId);
        payment.setIsPending(isPending);
        payment.setIsSuccess(isSuccess);
        if(isSuccess && ! isPending) payment.setStatue("Paid");
        else payment.setStatue("Pending");
        payment.setIntegrationId(integrationId);
        payment.setPaymentProfileId(profileId);
        logger.info("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "extract order data";
        HashMap<String, Object> orderObject = (HashMap<String, Object>) paymentObject.get("order");
        Integer orderId = (Integer) orderObject.get("id");
        Integer amountInCents = (Integer) orderObject.get("amount_cents");
        Integer amountInEGP = ((Integer) orderObject.get("amount_cents")) / 100;
        String currency = (String) orderObject.get("currency");
        Integer paidAmountInCents = (Integer) orderObject.get("paid_amount_cents");
        HashMap<String, Object> sourceData = (HashMap<String, Object>) paymentObject.get("source_data");
        String transactionSourceType = (String) sourceData.get("type");
        String transactionSourceSubType = (String) sourceData.get("sub_type");
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "set order data";
        payment.setOrderId(orderId);
        payment.setAmountInCents(amountInCents);
        payment.setAmountInEGP(amountInEGP);
        payment.setCurrency(currency);
        payment.setPaidAmountInCents(paidAmountInCents);
        payment.setTransactionSourceType(transactionSourceType);
        payment.setTransactionSourceSubType(transactionSourceSubType);
        logger.info("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "extract user data from shipping data";
        HashMap<String, Object> shippingObject = (HashMap<String, Object>) orderObject.get("shipping_data");
        String userId = (String) shippingObject.get("floor");
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFound("User", "id", userId)
        );
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "update user status value";
        if(payment.getStatue().equals("Paid"))
        {
            user.setSubscriptionStatus("Active");
            logger.debug("method={}, action={}, userStatus={}, status={}",
                    method, action, "Active", "SUCCESS");
        }
        else{
            user.setSubscriptionStatus("InActive");
            logger.debug("method={}, action={}, userStatus={}, status={}",
                    method, action, "InActive", "SUCCESS");
        }

        action = "set payment subscription and";
        LocalDate subscriptionStartDate = LocalDate.now();
        payment.setSubscriptionDate(subscriptionStartDate);
        payment.setSubscriptionEndDate(subscriptionStartDate.plusDays(30));
        payment.setYear(subscriptionStartDate.getYear());
        payment.setCreationTime(System.currentTimeMillis());
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "update user subscription date, and subscription end date";
        User updatedUser = userService.updateUserSubscription(userId, user);
        user.setExpirationDate(subscriptionStartDate.plusDays(30));
        user.setSubscriptionDate(subscriptionStartDate);
        payment.setUser(updatedUser);
        logger.debug("method={}, action={}, status={}", method, action, "SUCCESS");

        action = "PaymentRepository.save";
        Payment savedPayment = repository.save(payment);
        logger.info("method={}, action={}, status={}", method, action, "SUCCESS");
        return new Response.ResponseBuilder()
            .data(savedPayment)
            .message("Payment created successfully.")
            .status(HttpStatus.CREATED)
            .build();
    }

    public Response _getPaymentById(String id)
    {
        Payment payment =  repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Payment", "id", id)
        );
        return new Response.ResponseBuilder()
                .data(payment)
                .message("Payment retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getPayments()
    {
        List<Payment> payments =  repository.findAll();
        return new Response.ResponseBuilder()
                .data(payments)
                .message("Payments retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _getPaymentsByYear(Integer year)
    {
        List<Payment> payments =  repository.findByYear(year);
        return new Response.ResponseBuilder()
                .data(payments)
                .message("Payments retrieved successfully.")
                .status(HttpStatus.OK)
                .build();
    }
}
