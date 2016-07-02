package Application.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Title implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	List<Tuple> titleList = new ArrayList<Tuple>();


	public Title() {
		// TODO Auto-generated constructor stub

		titleList.add(new Tuple(TitleType.repository, TitleKey.languages));
		titleList.add(new Tuple(TitleType.repository, TitleKey.stars));
		titleList.add(new Tuple(TitleType.user, TitleKey.contributed));
	}

	public List<Tuple> getTitleList(){
		return titleList;
	}


	public class Tuple implements Serializable{

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;


		private TitleType titleType;
		private TitleKey  titleKey;

		public Tuple(TitleType type, TitleKey key) {
			// TODO Auto-generated constructor stub

			this.titleType = type;
			this.titleKey  = key;

		}

		public TitleType getTitleType() {
			return titleType;
		}

		public void setTitleType(TitleType titleType) {
			this.titleType = titleType;
		}

		public TitleKey getTitleKey() {
			return titleKey;
		}

		public void setTitleKey(TitleKey titleKey) {
			this.titleKey = titleKey;
		}



	}
}
