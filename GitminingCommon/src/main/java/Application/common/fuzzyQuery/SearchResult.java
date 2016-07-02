package Application.common.fuzzyQuery;

public class SearchResult {
	private String originalString;
	private String keyword;
	private int[] matchIndex;
	private int formerIndex = 0;
	private int power;

	public SearchResult(String name, String keyword, int[] matchIndex,
			int formerIndex,int power) {
		this.originalString = name;
		this.keyword = keyword;
		this.matchIndex = matchIndex;
		this.formerIndex = formerIndex;
		this.power = power;
	}

	public String getString() {
		return originalString;
	}

	public String getKeyword() {
		return keyword;
	}

	/**
	 * @return 命中的字符的位置数组
	 */
	public int[] getMatchIndex() {
		return matchIndex;
	}

	/**
	 * @return 该结果在原数组中的位置
	 */
	public int getFormerIndex() {
		return formerIndex;
	}

	public int getPower() {
		return power;
	}

}
