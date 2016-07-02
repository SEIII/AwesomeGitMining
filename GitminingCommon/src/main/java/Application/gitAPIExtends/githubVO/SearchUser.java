package Application.gitAPIExtends.githubVO;


import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

//"id": "user-1433421",
//"gravatar_id": "",
//"username": "njustesen",
//"login": "njustesen",
//"name": "Niels Justesen",
//"fullname": "Niels Justesen",
//"location": "Copenhagen",
//"language": "Java",
//"type": "user",
//"public_repo_count": 28,
//"repos": 28,
//"followers": 6,
//"followers_count": 6,
//"score": 21.840485,
//"created_at": "2012-02-13T13:55:42Z",
//"created": "2012-02-13T13:55:42Z"

/**
 * @author admin
 * gson cannot prase by setter,only by fields
 */
@Data
@NoArgsConstructor
public class SearchUser {

    String id;
    String login;
    String name;
    String location;
    String language;
    String type;
    String avatar_url;

    int repos;
    int followers;

    Date created_at;

    public String getLogin() {
        //set the avatar_url value
        getAvatar_url();
        return login;
    }

    public String getAvatar_url() {
        String[] splitID = id.split("-");
        this.avatar_url = "https://avatars.githubusercontent.com/u/"+splitID[1]+"?v=3";
        return avatar_url;
    }

}
