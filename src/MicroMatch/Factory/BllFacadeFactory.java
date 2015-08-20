package MicroMatch.Factory;

import MicroMatch.Facade.BllFacade;
import MicroMatch.Interface.BllInterface;

public class BllFacadeFactory {

	// 单例
	private static BllFacadeFactory objbllFactory = new BllFacadeFactory();

	private BllFacadeFactory() {
	}

	public static BllFacadeFactory getBllFactoryInstance() {

		return objbllFactory;
	}

	// bllinterface调用单例

	public BllInterface getInterface() {
		BllInterface bllInterface = BllFacade.getInstance();

		return bllInterface;
	}
}
