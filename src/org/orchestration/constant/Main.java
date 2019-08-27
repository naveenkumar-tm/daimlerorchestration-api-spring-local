package org.orchestration.constant;

public class Main {

	public static void main(String[] args) {
		String value = "call gmonstar_is_user_valid(user_name,password,ip)";

		String params = value.substring(value.indexOf("(") + 1, value.indexOf(")"));

		String sqlValue = value.substring(0, value.indexOf("("));
		String key = value.substring(value.indexOf("("));

		StringBuilder builder = new StringBuilder(
				key.replaceAll("[^,()]", "").replace(")", ",)").replace(",", "?,"));
		String sql;
		/*
		 * Check the value of params if empty or not
		 */
		if (params.isEmpty()) {
			sql = sqlValue + "()";
		} else {
			sql = sqlValue + "" + builder.deleteCharAt(builder.lastIndexOf(",")).toString();
		}

		System.out.println("\n Param :-" + params + "==" + sql);
	}
}
