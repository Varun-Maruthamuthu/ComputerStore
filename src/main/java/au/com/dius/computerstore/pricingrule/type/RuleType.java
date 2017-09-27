package au.com.dius.computerstore.pricingrule.type;

public enum RuleType {
	TFT("ThreeForTwoRule"),
	BULK("BulkDiscountRule"),
	FREE("FreeProductRule");

	private String ruleName ;
	
	RuleType (String ruleName) {
		this.ruleName = ruleName;
	}
}
