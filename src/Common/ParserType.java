package Common;

public enum ParserType {
	PARSER_NONE,
	PARSER_DES_LOOT,
	PARSER_SKINNING_LOOT,
	PARSER_CREATURE_LOOT,
	PARSER_ITEM_LOOT;
	
    public static ParserType fromInteger(int x) {
        switch (x) {
	        case 0:
	            return PARSER_DES_LOOT;
	        case 1:
	            return PARSER_SKINNING_LOOT;
	        case 2:
	            return PARSER_CREATURE_LOOT;
	        case 3:
	            return PARSER_ITEM_LOOT;
	        default:
	        	return PARSER_NONE;
	     	}
    }
}