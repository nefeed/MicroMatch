package MicroMatch.Factory;

import MicroMatch.Bll.BllAbstract;

/**
 *	BLL业务层工厂
 */
@SuppressWarnings("unchecked")
public class BllFactory {

	private static BllFactory df = null;

	private BllFactory(){}

	public static BllFactory getBllFactory(){
		if (df == null) {
			df = new BllFactory();
		}
		return df;
	}
	public <T extends BllAbstract> T getInstance(Class<T> c){
		T daoObject = null;
		try {
			daoObject = (T)Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return daoObject;
	}
}

