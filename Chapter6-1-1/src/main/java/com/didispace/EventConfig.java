package com.didispace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * 该配置实现了com.didispace.StateMachineConfig类中定义的状态机监听器实现。
 */
@WithStateMachine
public class EventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("用户完成支付，待收货");
    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        logger.info("用户完成支付，待收货: start");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        logger.info("用户完成支付，待收货: end");
    }


    @OnTransitionStart(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receiveStart() {
        logger.info("用户已收货，订单完成:start");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("用户已收货，订单完成");
    }

    @OnTransitionEnd(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receiveEnd() {
        logger.info("用户已收货，订单完成:end");
    }


    @OnTransitionStart(source = "WAITING_FOR_RECEIVE", target = "REFUND_DONE")
    public void refundStart() {
        logger.info("用户退款，退款完成:start");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "REFUND_DONE")
    public void refund() {
        logger.info("用户退款，退款完成");
    }

    @OnTransitionEnd(source = "WAITING_FOR_RECEIVE", target = "REFUND_DONE")
    public void refundEnd() {
        logger.info("用户退款，退款完成:end");
    }

}