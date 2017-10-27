package fitnesscompanion.com.A30SDK.command;


import fitnesscompanion.com.A30SDK.BleConst;

public class DelHistoryDataCmd extends ICommand {

	public DelHistoryDataCmd() {
		super();
		cmd = BleConst.SEND_DEL_STEP;
	}

	@Override
	public CmdResponseResult analyseResponse(String resp) {
		return analyse(resp);
	}

}
