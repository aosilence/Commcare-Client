package commcare.capstone.comcare.biz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnviromentBiz {

	Logger LOG = LoggerFactory.getLogger(EnviromentBiz.class);
	private static EnviromentBiz enBiz = new EnviromentBiz();

    private boolean production = true;
	private boolean test = true;
	private String version = "0.1";
	public static EnviromentBiz getInstance()
	{
		return enBiz;
	}

	private EnviromentBiz()
	{
	}

    public boolean isProduction() {
        return production;
    }

    public String getVersion() {
        return version;
    }

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}
}
