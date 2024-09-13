package com.rjil.cmp.root.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rjil.cmp.root.dto.AttachDataPlanRequestDto;

@Component
public class AttachDataPlanRequestDtoValidator implements Validator {
  public boolean supports(Class<?> clazz) {
    return com.rjil.cmp.root.api.validator.AttachDataPlanRequestDtoValidator.class.isAssignableFrom(clazz);
  }
  
  public void validate(Object target, Errors errors) {
    AttachDataPlanRequestDto attachDataPlanRequestDto = (AttachDataPlanRequestDto)target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceId", "api.iccid.or.imsi.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataPlanId", "api.data.plan.id.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "api.comment.required");
    String recurringPurchase = attachDataPlanRequestDto.getRecurringPurchase();
    if (recurringPurchase != null && recurringPurchase.length() > 0 && 
      !"YES".equalsIgnoreCase(recurringPurchase) && !"NO".equalsIgnoreCase(recurringPurchase))
      errors.rejectValue("recurringPurchase", "api.purchase.recurring.purchase.invalid", ""); 
  }
}
