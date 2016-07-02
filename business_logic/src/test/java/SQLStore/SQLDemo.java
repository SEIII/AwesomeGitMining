package SQLStore;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class SQLDemo {

	Connection conn = null;
	Statement stmt = null;
	String sql;

	public static void main(String[] args) throws Exception{

		SQLDemo demo = new SQLDemo();
        demo.initDataBase();

//        demo.storeUserInfo();
		demo.closeConnetion();
	}



	public void updateUserCreated() throws SQLException{
		sql = "select u.content from alluserinfo u";
		ResultSet allUserLogin = getList(sql);
		while(allUserLogin.next()){
			String userLogin = allUserLogin.getString(1);

		}
	}


	public void updateTest(){
		 sql = "update repoFullName t set t.info = 'haha' where t.index = 1";
		 System.out.println(excute(sql));
	}


	public void initDataBase() throws SQLException
	{

		String url = "jdbc:mysql://localhost:3306/localgitmining?"
                + "user=root&useUnicode=true&characterEncoding=UTF8";

		 try {

	            Class.forName("com.mysql.jdbc.Driver");


	            System.out.println("成功加载MySQL驱动程序");

	         // 一个Connection代表一个数据库连接
	            conn = DriverManager.getConnection(url);
	            stmt = conn.createStatement();
		 } catch (SQLException e) {
	            System.out.println("MySQL操作错误");
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}


	public void setChange() throws SQLException
	{

		File f = new File("localRepos/users.txt");
		String line = "";
		int index = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((line = br.readLine())!= null){

				sql = "insert into users values" + "(" + index + ",\'" + line +"\')";
				index++;
				System.out.println("Has completed "+ index +" "+excute(sql));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public int excute(String sql)
	{
		int answer = 0;
		try {

		     answer = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return answer;

	}


	public ResultSet getList(String sql)
	{
		ResultSet temp = null;
		try {

			temp = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return temp;
	}



	public void closeConnetion() throws SQLException
	{
		conn.close();
	}


	public void testAnalyseRepoInfo(){
		File f = new File("localRepos/allRepos.txt");
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			content = br.readLine();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		content = content.substring(1, content.length()-1);
		int left_bracket = 0;
		int right_bracket = 0;
		int index = 0;
		String temp = "";

		for(int i=0; i<content.length();i++){

			if(content.charAt(i) == '{')
				left_bracket++;
			if(content.charAt(i) == '}')
				right_bracket++;

			temp += content.charAt(i);


			if(left_bracket == right_bracket&&left_bracket!=0){

				temp = temp.replaceAll("\\\\", "\\\\\\\\");

				sql = "update repoFullName t set t.info =" +"\'" + temp +"\'" + "where t.index =" + index;
//				sql = "update repoFullName t set t.info =" +"\'"+ temp +"\'"+"where t.index = "+index;
//				if(excute(sql) == 0){
//					System.out.println(index);
//					System.out.println(temp);
//				}
//				String[] temp_list = temp.split(":", 2);
//				sql = "update repoFullName t set t.languages =" +"\'"+ temp_list[1] +"\'"+"where t.content = "+ temp_list[0];
//				System.out.println(temp_list[0]+"   "+temp_list[1]);
				System.out.println(index +"   "+ excute(sql));

				index++;
				temp = "";
				left_bracket = 0;
				right_bracket = 0;

				if(i != content.length()-1)
					i++;

			}


		}

		System.out.println(index);
	}



	public void storeLanguageNumber(){
		File f = new File("localRepos/languegeLineNumber.txt");
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((line = br.readLine())!=null){
				String[] list = line.split(":");

				sql = "insert into languageLine values (" +"\'"+list[0]+"\'" +"," +Integer.parseInt(list[1])+")";
				excute(sql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void storeUserInfo(){



		File userLogin = new File("localRepos/users.txt");
		File f = new File("localRepos/allUserInfo.txt");
		List<String> all_user_login = new ArrayList<String>();
		String content = "";
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			content = br.readLine();

			br = new BufferedReader(new FileReader(userLogin));
			while((line = br.readLine())!=null)
				all_user_login.add(line);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		content = content.substring(1, content.length()-1);
		int left_bracket = 0;
		int right_bracket = 0;
		int index = 0;
		String temp = "";

		for(int i=0; i<content.length();i++){

			if(content.charAt(i) == '{')
				left_bracket++;
			if(content.charAt(i) == '}')
				right_bracket++;

			temp += content.charAt(i);


			if(left_bracket == right_bracket&&left_bracket!=0){
				temp = temp.replaceAll("\\\\", "\\\\\\\\");
				sql = "insert into alluserinfo values (" + index +","+"\'" +all_user_login.get(index) +"\'"+","+ "\'" + temp +"\'"+")";

				System.out.println("completed " + index +" " + excute(sql));
				index++;
				temp = "";
				left_bracket = 0;
				right_bracket = 0;

				if(i != content.length()-1)
					i++;

			}


		}

		System.out.println(index);
	}

	public void storeContributors(){
		File f = new File("localRepos/contributors.txt");
		String line = "";
		int index = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((line = br.readLine())!=null){
				String[] temp_list = line.split(":");
				sql = "insert into contributors values (" + index + ",\'" + temp_list[0]+ "\'" +",\'"+temp_list[1]+"\')";
				System.out.println(index + " " + excute(sql));
				index++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void storeCollaborators(){
		File f = new File("localRepos/collaborators.txt");
		String line = "";
		int index = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((line = br.readLine())!=null){
				String[] temp_list = line.split(":");
				sql = "insert into collaborators values (" + index + ",\'" + temp_list[0]+ "\'" +",\'"+temp_list[1]+"\')";
				System.out.println(index + " " + excute(sql));
				index++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
