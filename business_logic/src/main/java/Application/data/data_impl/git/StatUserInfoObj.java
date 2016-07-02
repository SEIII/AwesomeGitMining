package Application.data.data_impl.git;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.DTO.UserRelatedRepository;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.gitAPIExtends.githubVO.Contribution;
import Application.gitAPIExtends.githubVO.Contribution.WeekContribution;
import lombok.Setter;

@Component
@Scope("prototype")
public class StatUserInfoObj {

    StatTotalCallback callback;

    @Setter
    StatUserInfoObj selfService;

    private Set<String> sendedSet = new HashSet<>();

    public StatUserInfoObj(StatTotalCallback callback) {
        this.callback = callback;
    }

//    @Async
    public GitUserTotalInfo getGitUserTotalInfo(String login) {

        callback.start();

        SourceDataAuxiliary auxiliary = AppContextSupport
                .getApplicationContext().getBean(SourceDataAuxiliary.class);

        Future<List<Repository>> createdFutureRepo = auxiliary
                .statUserCreatedRepository(login, callback);
        Future<List<Repository>> contributedFutureRepo = auxiliary
                .statUserContributedRepository(login, callback);
        Future<User> userFutureInfo = auxiliary.getSingleUserInfo(login);
        Future<List<Event>> futureEvents = auxiliary.getUserEvent(login);

        while (!userFutureInfo.isDone()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("cancelling the tasks");
                createdFutureRepo.cancel(true);
                contributedFutureRepo.cancel(true);
                futureEvents.cancel(true);
            }
        }

        try {
            callback.setCreatedNum(userFutureInfo.get().getPublicRepos());
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
        }

        Future<List<UserRelatedRepository>> createdFutureUserRealatedRepo = selfService
                .processRepo(createdFutureRepo, login, auxiliary, callback);

        Future<List<UserRelatedRepository>> contributedFutureUserRealatedRepo = selfService
                .processRepo(contributedFutureRepo, login, auxiliary, callback);

        try {
            while (!(createdFutureUserRealatedRepo.isDone()
                    && contributedFutureUserRealatedRepo.isDone()
                    && futureEvents.isDone())) {

                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("cancelling the tasks");
            createdFutureUserRealatedRepo.cancel(true);
            contributedFutureUserRealatedRepo.cancel(true);
            futureEvents.cancel(true);
        }

        GitUserTotalInfo info = new GitUserTotalInfo();

        try {
            info.setContributedRepos(contributedFutureUserRealatedRepo.get());
            info.setCreatedRepos(createdFutureUserRealatedRepo.get());
            info.setLastestEvent(futureEvents.get());
            info.setUser(userFutureInfo.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        callback.done();
//        return new AsyncResult<GitUserTotalInfo>(info);
        return info;
    }

    @Async
    public Future<List<UserRelatedRepository>> processRepo(
            Future<List<Repository>> futureRepos, String login,
            SourceDataAuxiliary auxiliary, StatTotalCallback callback) {

        List<UserRelatedRepository> result = new ArrayList<UserRelatedRepository>();
        List<Future<Map<String, Long>>> repoFutureLanguageList = new ArrayList<Future<Map<String, Long>>>();
        List<Future<List<Contribution>>> repoFutureContributionList = new ArrayList<Future<List<Contribution>>>();

        List<Map<String, Long>> repoLanguageList = new ArrayList<Map<String, Long>>();
        List<List<Contribution>> repoContributionList = new ArrayList<List<Contribution>>();
        List<Repository> repos = new ArrayList<Repository>();

        // try {

        try {
            while (!futureRepos.isDone()) {
                Thread.sleep(100);
            }
            repos = futureRepos.get();
        } catch (InterruptedException | ExecutionException e1) {
            e1.printStackTrace();
            futureRepos.cancel(true);
            return new AsyncResult<List<UserRelatedRepository>>(null);
        }

        if (repos != null) {
            for (Repository repo : repos) {

                Future<Map<String, Long>> languageFutureMap = auxiliary
                        .getRepoLanguage(repo);
                repoFutureLanguageList.add(languageFutureMap);

                Future<List<Contribution>> contributionFuture = auxiliary
                        .getRepoContribution(repo.getOwner().getLogin(),
                                repo.getName());
                repoFutureContributionList.add(contributionFuture);
            }

            while (true) {
                boolean end = true;

                for (Future<Map<String, Long>> futureLanguage : repoFutureLanguageList) {
                    end = end && futureLanguage.isDone();
                }

                for (int i = 0; i < repoFutureContributionList.size(); i++) {

                    Future<List<Contribution>> contributionList = repoFutureContributionList
                            .get(i);
                    if (contributionList.isDone()) {
                        sendContributedMessage(repos.get(i), callback);
                    }

                    end = end && contributionList.isDone();
                }

                if (end) {
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    cancelAll(repoFutureLanguageList);
                    cancelAll(repoFutureContributionList);
                    return new AsyncResult<List<UserRelatedRepository>>(null);
                }
            }

            for (Future<Map<String, Long>> futureLanguage : repoFutureLanguageList) {
                Map<String, Long> languageMap = new TreeMap<String, Long>();
                try {
                    languageMap = futureLanguage.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                repoLanguageList.add(languageMap);
            }

            for (Future<List<Contribution>> contributionFuture : repoFutureContributionList) {
                try {
                    List<Contribution> repoContribution = contributionFuture
                            .get();
                    repoContributionList.add(repoContribution);
                } catch (Exception e) {
                    repoContributionList.add(null);
                }

            }

            int repoNumber = repos.size();
            for (int i = 0; i < repoNumber; i++) {

                List<Contribution> contributions = repoContributionList.get(i);
                if (contributions == null) {
                    continue;
                } else {
                    UserRelatedRepository userRelatedRepository = new UserRelatedRepository();
                    Contribution contribution = new Contribution();
                    int totalCommitCount = 0;
                    int totalLineNum = 0;
                    int totalContributorNum = 0;

                    for (Contribution singleContribution : contributions) {

                        if (login.equals(
                                singleContribution.getAuthor().getLogin()))
                            contribution = singleContribution;

                        totalContributorNum++;

                        totalCommitCount += singleContribution.getTotal();

                        List<WeekContribution> weekContributions = singleContribution
                                .getWeeks();
                        for (WeekContribution weekContribution : weekContributions) {
                            totalLineNum += weekContribution.getA();
                            totalLineNum -= weekContribution.getD();
                        }

                    }

                    userRelatedRepository.setContribution(contribution);
                    userRelatedRepository
                            .setLanguageList(repoLanguageList.get(i));
                    userRelatedRepository.setRepository(repos.get(i));
                    userRelatedRepository.setTotalCommitCount(totalCommitCount);
                    userRelatedRepository
                            .setTotalContributorNum(totalContributorNum);
                    userRelatedRepository.setTotalLineNum(totalLineNum);

                    result.add(userRelatedRepository);

                }
            }
        } else {
            return new AsyncResult<List<UserRelatedRepository>>(null);
        }

        // }catch (IllegalStateException illegalStateException) {
        // throw illegalStateException;
        // }
        // catch (Exception e) {
        // e.printStackTrace();
        // }

        return new AsyncResult<List<UserRelatedRepository>>(result);
    }
    
    protected <T> void cancelAll(List<Future<T>> futures) {
        
        for(Future<T> oneFuture : futures) {
            oneFuture.cancel(true);
        }
        
    }

    protected void sendContributedMessage(Repository repository,
            StatTotalCallback callback) {

        String fullName = repository.getOwner().getLogin() + "/"
                + repository.getName();

        if (!sendedSet.contains(fullName)) {
            sendedSet.add(fullName);
            callback.getOneRepoContributionInfo(fullName);
        }

    }

}
