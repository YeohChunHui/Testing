package fitnesscompanion.com.A30SDK.command;


import fitnesscompanion.com.A30SDK.BleConst;

public class SetTempUnitCmd extends ICommand {

	public SetTempUnitCmd(String data) {
		super();
		cmd = BleConst.SEND_SET_TEMPUNIT + String.format("%02X", Integer.parseInt(data));
	}

	@Override
	public CmdResponseResult analyseResponse(String resp) {
		// TODO Auto-generated method stub
		return analyse(resp);
	}

}
