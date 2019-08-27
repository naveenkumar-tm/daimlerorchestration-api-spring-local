/**
 * This package contain  class is used to call Alias To Entity LinkedHashMap Result Transformer
 */
package org.orchestration.hibernate.transform;
/**
 * To Import Classes to access their functionality
 */
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
/**
 * 
 * This class use to get the Output in the same sequence as the Procedure returns from database and stored in LinkedHashMap
 * 
 * @author Ankita Shrothi
 *
 */
@SuppressWarnings("serial")
public class OrchestrationAliasToEntityLinkedHashMapResultTransformer extends AliasedTupleSubsetResultTransformer {

	public static final OrchestrationAliasToEntityLinkedHashMapResultTransformer INSTANCE = new OrchestrationAliasToEntityLinkedHashMapResultTransformer();

	/**
	 * Disallow instantiation of AliasToEntityMapResultTransformer.
	 */
	private OrchestrationAliasToEntityLinkedHashMapResultTransformer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object transformTuple(Object[] tuple, String[] aliases) {
		@SuppressWarnings("rawtypes")
		Map result = new LinkedHashMap(tuple.length);
		// Map result = new HashMap(tuple.length);
		for (int i = 0; i < tuple.length; i++) {
			String alias = aliases[i];
			if (alias != null) {
				result.put(alias, tuple[i]);
			}
		}
		
	//	System.out.println("transform:- "+result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		return false;
	}

	/**
	 * Serialization hook for ensuring singleton uniqueing.
	 *
	 * @return The singleton instance : {@link #INSTANCE}
	 */
	private Object readResolve() {
		return INSTANCE;
	}

}
