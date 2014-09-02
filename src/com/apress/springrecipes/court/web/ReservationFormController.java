package com.apress.springrecipes.court.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.SportType;
import com.apress.springrecipes.court.service.ReservationService;

/*
 * @SessionAttributes("reservation") annotation—declared at the top of the
 * controller class. Since it’s possible for a form to contain errors, it can be
 * an inconvenience to lose whatever valid data was already provided by a user
 * on every subsequent submission. To solve this problem, the @SessionAttributes
 * is used to save a reservation field to a user’s session, so that any future
 * reference to the reservation field is in fact made on the same reference,
 * whether a form is submitted twice or more times. This is also the reason why
 * only a single Reservation object is created and assigned to the reservation
 * field in the entire controller. Once the empty Reservation object is
 * created—inside the HTTP GET handler method—all actions are made on the same
 * object, since it’s assigned to a user’s session.
 */
@Controller
// @RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {
	private ReservationService reservationService;

	@Autowired
	public ReservationFormController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(value = "/reservationForm", method = RequestMethod.GET)
	public String setupForm(
			@RequestParam(required = false, value = "username") String username,
			Model model) {
		Reservation reservation = new Reservation();
		if (null != username) {
			reservation.setPlayer(new Player(username, null));
		}
		model.addAttribute("reservation", reservation);
		if (null != username) {
			return "reservationFormPlayer";
		} else {
			return "reservationForm";
		}
	}

	@RequestMapping(value = "/reservationForm", method = RequestMethod.POST)
	public String submitForm(
			@RequestParam(required = false, value = "username") String username,
			@ModelAttribute("reservation") Reservation reservation,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			if (null != username) {
				return "reservationFormPlayer";
			} else {
				return "reservationForm";
			}
		}
		reservationService.make(reservation);
		return "redirect:reservationSuccess";
	}

	/*
	 * redirect:reservationSuccess:
	 * 
	 * The redirect: prefix in the view name is used to avoid a problem known as
	 * duplicate form submission
	 * 
	 * When you're doing a redirect to /reservationSuccess, by definition, the
	 * browser will send a new request to the URL /reservationSuccess of your
	 * web app. You will see the complete URL in the address bar of your
	 * browser.
	 * 
	 * If this URL is not mapped to any servlet in your web app, you will
	 * obviously get a 404 error.
	 * 
	 * You need to understand that the point of a redirect is not to dispatch to
	 * a view (a JSP). The point is to make the browser go to another URL in
	 * your web app. The path you put after the redirect: prefix is thus
	 * supposed to be the path of an action of your Spring MVC app. Not the path
	 * of a view.
	 */
	@RequestMapping(value = "/reservationSuccess", method = RequestMethod.GET)
	public String getSuccess() {
		return "reservationSuccess";
	}

	/*
	 * The @ModelAttribute annotation is used to define global model attributes,
	 * available to any returning view used in handler methods. In the same way
	 * a handler method declares a Model object as an input parameter and
	 * assigns attributes that can be accessed in the returning view. Since the
	 * method decorated with the @ModelAttribute("sportTypes") annotation has a
	 * return type of List<SportType> and makes a call to
	 * reservationService.getAllSportTypes(), the hard-coded TENNIS and SOCCER
	 * SportType objects are assigned to the model attribute named sportTypes.
	 * With this last model attribute used in the form view to populate a drop
	 * down list (i.e.<form:select> tag).
	 */
	@ModelAttribute("sportTypes")
	public List<SportType> populateSportTypes() {
		return reservationService.getAllSportTypes();
	}

	/*
	 * Binding Properties of Custom Types
	 * 
	 * When a form is submitted, a controller binds the form field values to
	 * model object’s properties of the same name, in this case a Reservation
	 * object. However, for properties of custom types, a controller is not able
	 * to convert them unless you specify the corresponding property editors for
	 * them. For example, the sport type selection field only submits the
	 * selected sport type ID—as this is the way HTML <select> fields operate.
	 * Therefore, you have to convert this ID into a SportType object with a
	 * property editor. First of all, you require the getSportType() method in
	 * ReservationService to retrieve a SportType object by its ID:
	 * 
	 * Now that you have the supporting SportTypeEditor class required to bind
	 * form properties to a custom class like SportType, you need to associate
	 * it with the controller. For this purpose, Spring MVC relies on custom
	 * classes that implement the WebBindingInitializer class.
	 * 
	 * By creating a custom class that implements WebBindingInitializer,
	 * supporting classes for binding form properties to custom types can be
	 * associated with a controller. This includes the SportTypeEditor class and
	 * other custom types like Date.
	 * 
	 * Though we didn’t mention the date field earlier, it suffers from the same
	 * problem as the sport type selection field. A user introduces date fields
	 * as text values. In order for the controller to assign these text values
	 * to the Reservation object’s date field, this requires the date fields be
	 * associated with a Date object,. Given the Date class is part of the Java
	 * language, it won’t be necessary to create special a class like
	 * SportTypeEditor for this purpose, the Spring framework already includes a
	 * custom class for this purpose.
	 * 
	 * Knowing you need to bind both the SportTypeEditor class and a Date class
	 * to the underlying controller, the following listing illustrates the
	 * ReservationBindingInitializer class that implements
	 * WebBindingInitializer.
	 * 
	 * The only field for this last class corresponds to reservationService,
	 * used to access the application’s ReservationService bean. Note the use of
	 * the @Autowired annotation that injects the bean through the class’s
	 * constructor.
	 * 
	 * Next, you can find the initBinder method used to bind the Date and
	 * SportTypeEditor classes. Prior to binding these classes though, a
	 * SimpleDateFormat object is setup to specify the expected format for the
	 * date field, in addition to indicating a strict matching pattern by
	 * invoking the setLenient(false) method.
	 * 
	 * You can then find two calls to the registerCustomEditor method. This
	 * method belongs to the WebDataBinder object, which is passed as an input
	 * parameter to initBinder method.
	 * 
	 * The first call is used to bind a Date class to the CustomDateEditor
	 * class. The CustomDateEditor class is provided by the Spring framework and
	 * offers the same functionality as the SportTypeEditor class you created,
	 * except for Date objects. Its input parameters are a SimpleDateFormat
	 * object indicating the expected date format and a Boolean value to
	 * indicate if the value is allowed to be empty, in this case true.
	 * 
	 * The second call is used to bind a SportType class to the SportTypeEditor
	 * class. Since you created the SportTypeEditor class, you should be
	 * familiar that its only input parameter is a ReservationService bean.
	 * 
	 * Once you complete the ReservationBindingInitializer class, you have to
	 * register it with the application. To do this, you declare the binding
	 * initializer class as an AnnotationMethodHandlerAdapter property.
	 */
}
