package Application.gitAPIExtends.githubVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Contribution implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	Author author;

	int total;

	List<WeekContribution> weeks;

	List<Integer> addList;

	List<Integer> deleteList;





	public List<Integer> getAddList(){
		if(addList == null){
			addList = new ArrayList<Integer>();
			deleteList = new ArrayList<Integer>();

			for(WeekContribution wc: weeks){
				addList.add(wc.a);
				deleteList.add(-1*wc.d);
			}

		}


		return addList;
	}

	public List<Integer> getDeleteList(){
		if(deleteList == null){
			addList = new ArrayList<Integer>();
			deleteList = new ArrayList<Integer>();

			for(WeekContribution wc: weeks){
				addList.add(wc.a);
				deleteList.add(-1*wc.d);
			}

		}

		return deleteList;
	}

	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public List<WeekContribution> getWeeks() {
		return weeks;
	}


	public void setWeeks(List<WeekContribution> weeks) {
		this.weeks = weeks;
	}


//	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Author implements Serializable{

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		String login;

		String avatar_url;


		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getAvatar_url() {
			return avatar_url;
		}
		public void setAvatar_url(String avatar_url) {
			this.avatar_url = avatar_url;
		}


	}


	public static class WeekContribution implements Serializable{

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;


		String w;

		int	a;

		int d;

		int c;



		public String getW() {
			return w;
		}
		public void setW(String w) {
			this.w = w;
		}
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getD() {
			return d;
		}
		public void setD(int d) {
			this.d = d;
		}
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}



	}

}
