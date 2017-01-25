package formbeans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.FundBean;

public class TransitionDayForm {
	private String transitionDate;
	private String transitionDayButton;
	private boolean checkPrice;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Map<Integer, Double> map = new HashMap<Integer, Double>();

	public TransitionDayForm(HttpServletRequest request) {
		String dateInput = request.getParameter("date");
		transitionDayButton = request.getParameter("action");
		if (dateInput != null) {
			transitionDate = dateInput.trim();
		}
		HttpSession session = request.getSession();
		FundBean[] fundBeans = (FundBean[]) session.getAttribute("fundArray");

		for (FundBean fb : fundBeans) {
			String parameter = "fund" + fb.getFundId();
			String price = request.getParameter(parameter);
			boolean isDouble = checkPriceValue(price);
			if (isDouble) {
				map.put(fb.getFundId(), getPriceAsDouble(price));
			} else {
				checkPrice = false;
				break;
			}
		}

		boolean checkDateFormat = checkDateFormat(dateInput);
		if (checkDateFormat) {
			transitionDate = dateInput;
		}
	}

	public String getTransitionDate() {
		return transitionDate;
	}

	public String getTransitionDayButton() {
		return transitionDayButton;
	}

	public Map<Integer, Double> getFundPriceMap() {
		return map;
	}

	public double getPriceAsDouble(String price) {
		double priceAsDouble = Double.parseDouble(price);
		return priceAsDouble;
	}

	public boolean isPresent() {
		return transitionDayButton != null;
	}

	public boolean checkDateFormat(String date) {
		try {
			dateFormat.parse(dateFormat.format(date));
		} catch (ParseException parseEx) {
			return false;
		}
		return true;
	}

	public boolean checkPriceValue(String price) {
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (transitionDate == null || transitionDate.length() == 0) {
			errors.add("Please select a date for the transition day");
		}

		if (!checkPrice) {
			errors.add("Please specify the price in the right format.");
		}

		if (!checkDateFormat(transitionDate)) {
			errors.add("Error in parsing the date");
		}

		if (transitionDayButton == null || !transitionDayButton.equals("Update prices")) {
			errors.add("Invalid button");
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}
}
