package com.apress.springrecipes.court.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.util.WebUtils;

import com.apress.springrecipes.court.domain.PeriodicReservation;
import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.service.ReservationService;

@Controller
@RequestMapping("/periodicReservationForm")
@SessionAttributes("reservation")
public class PeriodicReservationController {

	private ReservationService reservationService;

	@Autowired
	public PeriodicReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		PeriodicReservation reservation = new PeriodicReservation();
		reservation.setPlayer(new Player());
		model.addAttribute("reservation", reservation);
		return "reservationCourtForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("reservation") PeriodicReservation reservation,
			BindingResult result, SessionStatus status,
			@RequestParam("_page") int currentPage, Model model) {
		Map pageForms = new HashMap();
		pageForms.put(0, "reservationCourtForm");
		pageForms.put(1, "reservationTimeForm");
		pageForms.put(2, "reservationPlayerForm");
		if (request.getParameter("_cancel") != null) {
			// Return to current page view, since user clicked cancel
			return (String) pageForms.get(currentPage);
		} else if (request.getParameter("_finish") != null) {
			// User is finished, make reservation
			reservationService.makePeriodic(reservation);
			return "redirect:reservationSuccess";
		} else {
			// User clicked Next or Previous(_target)
			// Extract target page
			int targetPage = WebUtils.getTargetPage(request, "_target",
					currentPage);
			// If targetPage is lesser than current page, user clicked
			// 'Previous'
			if (targetPage < currentPage) {
				return (String) pageForms.get(targetPage);
			}
			// User clicked 'Next', return target page
			return (String) pageForms.get(targetPage);
		}
	}

	@ModelAttribute("periods")
	public Map<Integer, String> periods() {
		Map<Integer, String> periods = new HashMap<Integer, String>();
		periods.put(1, "Daily");
		periods.put(7, "Weekly");
		return periods;
	}

}
