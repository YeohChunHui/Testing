package fitnesscompanion.com.A30SDK.command;

import fitnesscompanion.com.A30SDK.BleConst;


public class CmdResponseResult {

	public int state;

	public Object data;

	public boolean isBroad;

	public String action = BleConst.SF_ACTION_DEVICE_RETURNDATA;
}
