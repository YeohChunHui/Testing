package fitnesscompanion.com.A30SDK.command;


import fitnesscompanion.com.A30SDK.BleConst;

public class SetGreetCmd extends ICommand {

	public SetGreetCmd(String str) {
		super();
		cmd = BleConst.SEND_SET_GREET + pack2Cmd(str);
	}

	@Override
	public CmdResponseResult analyseResponse(String resp) {
		return analyse(resp);
	}

	private String pack2Cmd(String str) {
		char[] charArray = str.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < charArray.length; i++) {
			builder.append(String.format("%02X", (int) charArray[i]));
		}

		return builder.toString() + "00";
	}
}
