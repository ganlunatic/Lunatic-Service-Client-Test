package cn.lunatic.service;

import java.util.SortedMap;

import cn.lunatic.base.util.Log;
import cn.lunatic.service.exception.LunaticException;
import cn.lunatic.service.itf.LunaticServiceItf;

public class Service_Test extends LunaticServiceItf {
	private static final long serialVersionUID = 1L;

	@Override
	public void run(SortedMap<Object, Object> arg0) throws LunaticException {
		Log.print("1233434");
	}
}
