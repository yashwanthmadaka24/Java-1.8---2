package com.infomover.training.java8.part1;

public class Ch3App2DefaultsAndSubclassing {

	/**
	 * Put simply: class wins. The motivation for this decision is that default
	 * methods are designed primarily to allow binary compatible API evolution. 
 
	 * <p> 
	 * Suppose we had a custom list implementation called MyCustomList and had
	 * implemented a custom addAll method, and the new List interface provided a
	 * default addAll that delegated to the add method. If the default method
	 * wasn’t guaranteed to be overridden by this addAll method, we could break
	 * the existing implementation.
	 * </p>
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ParentAppImpl app1 = new ParentAppImpl();

		app1.welcome();
		System.out.println(app1.getLastMessage());

		ChildAppImpl app2 = new ChildAppImpl();
		app2.welcome();
		System.out.println(app2.getLastMessage());

		OverridingParentAppImpl app3 = new OverridingParentAppImpl();
		app3.welcome();
		System.out.println(app3.getLastMessage());

		OverridingChildIImpl app4 = new OverridingChildIImpl();
		app4.welcome();
		System.out.println(app4.getLastMessage());
	}
}

interface ParentApp {

	public void message(String body);

	public default void welcome() {
		message("ParentApp : Hi!");
	}

	public String getLastMessage();

}

interface ChildApp extends ParentApp {

	@Override
	default void welcome() {
		message("ChildApp : Hi!");
	}
}

class ParentAppImpl implements ParentApp {

	private String body;

	@Override
	public void message(String body) {

		this.body = body;
	}

	@Override
	public String getLastMessage() {
		return this.body;
	}

}

/**
 * welcome method of ChildApp will be used
 * 
 * @author MuhammedShakir
 *
 */
class ChildAppImpl extends ParentAppImpl implements ChildApp {

}

class OverridingParentAppImpl extends ParentAppImpl {

	@Override
	public void welcome() {

		message("OverridingParentAppImpl: Hi!");
	}

}

class OverridingChildIImpl extends OverridingParentAppImpl implements ChildApp {

}
