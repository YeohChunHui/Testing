package fitnesscompanion.com.A30SDK.command;


import fitnesscompanion.com.A30SDK.BleConst;

public class SetDistanceUnitCmd extends ICommand {

	public SetDistanceUnitCmd(String data) {
		super();
		cmd = BleConst.SEND_SET_DISUNIT + String.format("%02X", Integer.parseInt(data));
	}

	@Override
	public CmdResponseResult analyseResponse(String resp) {
		return analyse(resp);
	}

}
