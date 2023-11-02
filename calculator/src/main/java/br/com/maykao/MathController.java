package br.com.maykao;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value  = "/sum/{numberOne}/{numberTwo}", 
					method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo 
	) throws Exception	{	
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}
		return convertToDoubleAndSum(numberOne,numberTwo);
	}

	private Double convertToDoubleAndSum(String strNumberOne, String strNumberTwo) {
		if (strNumberOne == null || strNumberTwo == null) return 0D;
		
		String number1 = strNumberOne.replaceAll(",", ".");
		String number2 = strNumberTwo.replaceAll(",", ".");
		
		if (isNumeric(number1) && isNumeric(number2)) return Double.parseDouble(number1) + Double.parseDouble(number2);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
