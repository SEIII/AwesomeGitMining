package APITest.SpeedAndSroreTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import APITest.ConnectTest.ProjectRelatedFactory;

public class StoreContributorsTable {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		LocalFactory factory = new LocalFactory();

		String[] results = factory.getAllReposString();

		String[] filePaths = { "localRepos/contributors.txt", "localRepos/collaborators.txt", "localRepos/users.txt" };

		for (int i = 0; i < 3; i++)
			writeFile(results[i], filePaths[i]);

	}

	public static class LocalFactory extends ProjectRelatedFactory {

		public String[] getAllReposString() throws JsonParseException, JsonMappingException, IOException {

			long start_time = System.currentTimeMillis();

			List<String> allRepos = getAllRepo();
			StringBuilder contributors_table = new StringBuilder();
			StringBuilder collaborators_table = new StringBuilder();
			Set<String> user_set = new HashSet<String>();

			for (int i = 0; i < allRepos.size(); i++) {

				String fullname = allRepos.get(i);
				String username = fullname.split("/")[0];
				String reponame = fullname.split("/")[1];

				List<String> contributor_list = getContributorsLogin(username, reponame);
				List<String> collaborator_list = getRepoCollaboratorsLogin(username, reponame);

				for (int j = 0; j < contributor_list.size(); j++) {
					contributors_table.append(fullname + ":" + contributor_list.get(j) + "\r\n");
					user_set.add(contributor_list.get(j));

				}

				for (int j = 0; j < collaborator_list.size(); j++) {
					collaborators_table.append(fullname + ":" + collaborator_list.get(j) + "\r\n");
					user_set.add(collaborator_list.get(j));
				}

				System.out.println("已完成第" + i + "个");
			}

			StringBuilder user_table = new StringBuilder();
			for (String user : user_set) {
				user_table.append(user + "\r\n");
			}

			long end_time = System.currentTimeMillis();
			System.out.println(end_time - start_time);

			String[] results = { contributors_table.toString(), collaborators_table.toString(), user_table.toString() };
			return results;
		}
	}

	public static void writeFile(String content, String filePath) {

		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		long start_time = System.currentTimeMillis();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end_time = System.currentTimeMillis();

		System.out.println(end_time - start_time);
	}

}
