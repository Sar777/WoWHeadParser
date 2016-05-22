package Common;

public enum ParserType {
	PARSER_NONE,
	PARSER_DES_LOOT,
	PARSER_SKINNING_LOOT,
	PARSER_CREATURE_LOOT,
	PARSER_ITEM_LOOT,
	PARSER_CREATES_ITEM,
	PARSER_MILLING_LOOT,
	PARSER_ITEM_UPGRADES,
	PARSER_FISHING_LOOT;
	
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
	        case 4:
	        	return PARSER_CREATES_ITEM;
	        case 5:
	        	return PARSER_MILLING_LOOT;
	        case 6:
	        	return PARSER_ITEM_UPGRADES;
	        case 7:
	        	return PARSER_FISHING_LOOT;
	        default:
	        	return PARSER_NONE;
	     	}
    }
}