package org.tds.sgh.logic;

import java.util.Map;

public class Precondition
{
	public static void fail(String error)
	{
		throw new PreconditionException(error);
	}

	public static void isTrue(boolean condition, String error)
	{
		if (!condition)
		{
			fail(error);
		}
	}

	public static void isFalse(boolean condition, String error)
	{
		isTrue(!condition, error);
	}
	
	public static void isNull(Object object, String error)
	{
		isTrue(object == null, error);
	}
	
	public static void isNotNull(Object object, String error)
	{
		isTrue(object != null, error);
	}
	
	public static <K,V> void contains(Map<K,V> map, K key, String error)
	{
		isTrue(map.containsKey(key), error);
	}
	
	public static <K,V> void notContain(Map<K,V> map, K key, String error)
	{
		isFalse(map.containsKey(key), error);
	}
	
	
	// PreconditionException ==================================================
	
	public static class PreconditionException extends RuntimeException
	{
		private static final long serialVersionUID = -329322383713618288L;

		public PreconditionException(String message)
		{
			super(message);
		}
	}
}
