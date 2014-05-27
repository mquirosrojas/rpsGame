package com.mqr.rsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	private static final ThrowSet throwSet = new ThrowSet();
	private GameResult gameResult = new GameResult();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/getThrowSet", method = RequestMethod.GET)
	public @ResponseBody
	ThrowSet getTokenSet(Model model) {
		return throwSet;
	}

	@RequestMapping(value = "/throw", method = RequestMethod.GET)
	public @ResponseBody
	GameResult gameOutcome(@RequestParam("sign") int sign) {

		int machineThrow = getMachineThrow();
		int resultCode = getResultCode(sign, machineThrow);
		gameResult.setMachineItem(new Item(machineThrow,
				throwSet.getItems()[machineThrow].getName()));
		gameResult.setUserItem(new Item(sign, throwSet.getItems()[sign]
				.getName()));
		gameResult.setResultCode(resultCode);
		return gameResult;

	}

	private int getMachineThrow() {
		return (int) (Math.random()*3);
	}

	private int getResultCode(int uInput, int mInput) {
		// It returns 1 if the user won, 2 if the user lost, 3 if they tied, 4
		// if input is invalid.
		boolean uWins = ((uInput == 1 && mInput == 0)
				|| (uInput == 2 && mInput == 1) || (uInput == 0 && mInput == 2));
		boolean mWins = ((uInput == 0 && mInput == 1)
				|| (uInput == 1 && mInput == 2) || (uInput == 2 && mInput == 0));
		boolean nobodyWins = (uInput == 0 || uInput == 1 || uInput == 2)
				&& (uInput == mInput);
		if (uWins) {
			return 1;
		} else if (mWins) {
			return 2;
		} else if (nobodyWins) {
			return 3;
		} else {
			return 4;
		}
	}

}
