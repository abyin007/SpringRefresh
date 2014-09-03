package com.apress.springrecipes.court.domain;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*Since the validator class is annotated with the @Component annotation, Spring attempts to
 instantiate the class as a bean in accordance with the class name, in this case reservationValidator.
 In order for this process to work, remember that it’s necessary to activate annotation scanning on the
 package containing such declarations. Therefore the following addition is necessary to the servletconfig.
 xml file:
 <context:component-scan base-package="com.apress.springrecipes.court.domain" />
 An alternative to using the @Component annotation is to manually register the validator class bean
 using the following notation in the servlet-config.xml file:
 <bean id="reservationValidator" class="com.apress.springrecipes.court.domain.ReservationValidator" />
 
 Since validators may create errors during validation, you should define messages for the error codes
 for displaying to the user. If you have ResourceBundleMessageSource defined, you can include the
 following error messages in your resource bundle for the appropriate locale (e.g., messages. properties
 for the default locale):

 */
@Component
public class ReservationValidator implements Validator {

	public boolean supports(Class clazz) {
		return Reservation.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Reservation reservation = (Reservation) target;
		Date date = reservation.getDate();
		int hour = reservation.getHour();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName",
				"required.courtName");
		ValidationUtils.rejectIfEmpty(errors, "date", "required.date");
		ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour");
		if(null != reservation.getPlayer()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name",
					"required.playerName");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.phone",
					"required.playerPhone");
			ValidationUtils.rejectIfEmpty(errors, "sportType",
					"required.sportType");
		}
		
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (hour < 8 || hour > 22) {
					errors.reject("invalid.holidayHour",
							"Invalid holiday hour.");
				}
			} else {
				if (hour < 9 || hour > 21) {
					errors.reject("invalid.weekdayHour",
							"Invalid weekday hour.");
				}
			}
		}
	}
}
