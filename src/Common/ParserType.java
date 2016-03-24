package Common;

public enum ParserType {
	PARSER_NONE,
	PARSER_ITEM_LOOT,
	PARSER_SKINNING_LOOT,
	PARSER_CREATURE_LOOT;
	
    public static ParserType fromInteger(int x) {
        switch (x) {
	        case 0:
	            return PARSER_ITEM_LOOT;
	        case 1:
	            return PARSER_SKINNING_LOOT;
	        case 2:
	            return PARSER_CREATURE_LOOT;
	        default:
	        	return PARSER_NONE;
	     	}
    }
}