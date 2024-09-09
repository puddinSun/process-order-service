package com.assessment.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationError {

  /**
   * Customer domain error messages
   */
  CUSTOMER_ALREADY_REGISTERED("000001", "customer.customer_already_existed"),
  CUSTOMER_NOT_FOUND("000002", "customer.customer_not_found"),
  OTP_INVALID("000003", "customer.otp_invalid"),
  /**
   * Shop domain error messages
   */
  SHOP_NOT_FOUND("100000", "shop.shop_not_found"),
  /**
   * Queue domain error messages
   */
  ALL_QUEUE_ARE_FULL("200000", "queue.all_queue_are_full"),
  NO_QUEUE_AVAILABLE("200001", "queue.no_queue_available"),
  /**
   * Item domain error messages
   */
  ITEM_NOT_FOUND("300000", "item.item_not_found"),
  /**
   * Order domain error messages
   */
  ORDER_NOT_FOUND("400000", "order.order_not_found"),
  CANNOT_CANCEL_ORDER("400001", "order.cannot_cancel_order"),
  ORDER_NOT_IN_QUEUE("400002", "order.order_not_in_queue_anymore"),
  ORDER_ALREADY_COMPLETED("400002", "order.order_already_completed"),
  ORDER_NOT_READY("400002", "order.order_is_not_ready"),
  /**
   * Common error messages
   */
  VALIDATION_ERROR("000000", "common.validation_error"),
  UNEXPECTED_ERROR("999999", "common.unexpected_error");

  private final String code;
  private final String i18nMsg;
}
