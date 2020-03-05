import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class VoteCounter {

    public static void main(String[] args) {
        VoteCounter voteCounter = new VoteCounter();
    }

    private ArrayList<Vote> votes;
    private ArrayList<String> candidates;
    private HashMap<String, ArrayList<Vote>> runningVotes;

    public VoteCounter() {
        ArrayList<Vote> votes = new ArrayList<>();
        ArrayList<String> candidates = new ArrayList<>();
        HashMap<String, ArrayList<Vote>> runningVotes = new HashMap<>();
        createCandidates(candidates, runningVotes);
        System.out.println(getCandidates(candidates));
        createVotes(votes);
        printVotes(votes);
        calculateVotes(candidates, votes, runningVotes);
    }

    public ArrayList<Vote> getVotes(ArrayList<Vote> votes) {
        return votes;
    }

    public ArrayList<String> getCandidates(ArrayList<String> candidates) {
        return candidates;
    }

    public void printVotes(ArrayList<Vote> votes) {
        for (int i = 0; i < votes.size(); i += 1) {
            votes.get(i).printVote();
        }
    }

    public void createCandidates(ArrayList<String> candidates, HashMap<String, ArrayList<Vote>> runningVotes) {
        System.out.println("Enter number of candidates");
        Scanner intScan = new Scanner(System.in);
        Integer n = intScan.nextInt();
        System.out.println("Add Candidates:");
        for (int i = 0; i < n; i += 1) {
            Scanner candidateScan = new Scanner(System.in);
            String s = candidateScan.next();
            candidates.add(s);
        }

        for (String candidate : candidates) {
            runningVotes.put(candidate, new ArrayList<Vote>());
        }
    }

    public void createVotes(ArrayList<Vote> votes) {
        System.out.println("Enter number of votes:");
        Scanner intScan = new Scanner(System.in);
        Integer n = intScan.nextInt();
        System.out.println("Add Votes:");
        for (int i = 0; i < n; i += 1) {
            Scanner voteScan = new Scanner(System.in);
            String s = voteScan.next();
            votes.add(new Vote(s.split(",")));
        }
    }

    public void calculateVotes(ArrayList<String> candidates, ArrayList<Vote> votes, HashMap<String, ArrayList<Vote>> runningVotes) {
        for (Vote vote : votes) {
            ArrayList<Integer> voteSlip = vote.getVoteSlip();
            int minIndex = voteSlip.indexOf(Collections.min(voteSlip));
            System.out.println(minIndex);
            String candidate = candidates.get(minIndex);
            runningVotes.get(candidate).add(vote);
        }
    }

    static class Vote {

        private ArrayList<Integer> voteSlip;

        public Vote(String[] voteSlip) {
            this.voteSlip = new ArrayList<>(getIntegerArray(voteSlip));
        }

        public ArrayList<Integer> getVoteSlip() {
            return voteSlip;
        }

        public void printVote() {
            System.out.println(voteSlip);
        }

        private ArrayList<Integer> getIntegerArray(String[] stringArray) {
            ArrayList<String> stringArrayList = new ArrayList<String>(Arrays.asList(stringArray));
            ArrayList<Integer> result = new ArrayList<Integer>();
            for (String stringValue : stringArrayList) {
                result.add(Integer.parseInt(stringValue));
            }
            return result;
        }
    }
}