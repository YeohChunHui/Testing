package fitnesscompanion.com.A30SDK.command;


import fitnesscompanion.com.A30SDK.BleConst;

public class DelStepCmd extends ICommand {

	public DelStepCmd() {
		super();
		cmd = BleConst.SEND_DEL_STEP;
	}

	@Override
	public CmdResponseResult analyseResponse(String resp) {
		return analyse(resp);
	}

}
